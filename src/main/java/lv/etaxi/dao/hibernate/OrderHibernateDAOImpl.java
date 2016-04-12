package lv.etaxi.dao.hibernate;

import lv.etaxi.dao.OrderDAO;
import lv.etaxi.dao.jdbc.DBConnection;
import lv.etaxi.entity.Order;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Lazy;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/** Проект etaxi
 * Реализация управления объектами класса Order
 * */
@SuppressWarnings("ALL")
//@Repository
@Lazy
public class OrderHibernateDAOImpl implements OrderDAO {

    private final SessionFactory sessionFactory;

    public OrderHibernateDAOImpl() {
        DBConnection dbConnection = new DBConnection();
        Configuration configuration = dbConnection.getMySqlConfigurationForHibernate();
        this.sessionFactory = dbConnection.createSessionFactory(configuration);
    }

    /**
     * Возвращает объект соответствующий записи с первичным ключом key или null
     */
    public Order getById(long id) throws SQLException {

        Session session = sessionFactory.openSession();
        return (Order) session.get(Order.class, id);
    }

    /**
     * Сохраняет состояние объекта Order в базе данных (если ID нет, создаем новую запись)
     */
    public long update(Order order) throws SQLException {

        long id = order.getOrderId();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        if (id == 0) {
            id = (Long) session.save(order);
        }
        else {
            session.update(order);
        }
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * Удаляет запись об объекте из базы данных
     */
    public void delete(Order order) throws SQLException {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(order);
        transaction.commit();
        session.close();
    }

    /**
     * Возвращает список объектов соответствующих всем записям в базе данных
     */
    public List<Order> getAll() throws SQLException {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM Order order by orderedDateTime ASC");
        return  query.list();
    }

    public List<Order> getOpenOrdersAll() throws SQLException {

        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM Order o where o.orderStatus = '"+ Order.OrderStatus.WAITING +"' order by orderedDateTime ASC");
        return  query.list();
    }

    public List<Order> getOpenOrdersOfCustomer(long customerId, Timestamp begin, Timestamp end) throws SQLException {

        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM Order o where o.orderStatus = '"+ Order.OrderStatus.WAITING +"'"+
                ((customerId != 0) ? " and o.customerId = " + customerId : "") + " " +
                "AND (o.orderedDateTime  between '" + begin + "' AND '" + end + "') " +
                " order by orderedDateTime ASC");
        return  query.list();
    }

    public List<Order> getCompletedOrdersOfCustomer(long customerId, Timestamp begin, Timestamp end) throws SQLException {

        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM Order o where o.orderStatus = '"+ Order.OrderStatus.WAITING + "'" +   //DELIVERED
                ((customerId != 0) ? " and o.customerId = " + customerId : "") + " " +
                "AND (o.orderedDateTime  between '" + begin + "' AND '" + end + "') " +
                " order by orderedDateTime ASC");
        return  query.list();
    }

    public List<Order> getTaxiOrders(long id) throws SQLException {

        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM Order o where o.taxiId = "+ id + " order by orderedDateTime ASC");
        return  query.list();
    }


    public List<Order> getCustomerOrders(long id, Timestamp begin, Timestamp end) throws SQLException {

        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM Order o where o.customerId = " + id  +
                " AND (o.orderedDateTime  between '" + begin + "' AND '" + end + "') " +
                " order by orderedDateTime ASC");
        return  query.list();
    }

    public void createTable() throws SQLException {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS orders (" +
                "  Id bigint(9) NOT NULL auto_increment," +
                "  customerId bigint(9)," +
                "  datetime datetime," +
                "  ordereddatetime datetime," +
                "  orderStatus text," +
                "  fromAdress text," +
                "  toAdress text," +
                "  taxiId bigint(9)," +
                "  distance double," +
                "  price double," +
                "  rate int(2)," +
                "  feedback text," +
                "  PRIMARY KEY (Id)" +
                ");").executeUpdate();
        transaction.commit();
        session.close();
    }

}
