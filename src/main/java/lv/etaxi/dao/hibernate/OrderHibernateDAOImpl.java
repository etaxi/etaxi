package lv.etaxi.dao.hibernate;

import lv.etaxi.dao.OrderDAO;
import lv.etaxi.entity.Order;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/** Проект etaxi
 * Реализация управления объектами класса Order
 * */
@SuppressWarnings("ALL")
@Repository
public class OrderHibernateDAOImpl extends DAOImpl<Order> implements OrderDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Order> getOpenOrdersAll() throws SQLException {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Order o where o.orderStatus = '"+ Order.OrderStatus.WAITING +"' order by orderedDateTime ASC");
        return  query.list();
    }

    public List<Order> getOpenOrdersOfCustomer(long customerId, Timestamp begin, Timestamp end) throws SQLException {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Order o where o.orderStatus = '"+ Order.OrderStatus.WAITING +"'"+
                ((customerId != 0) ? " and o.customerId = " + customerId : "") + " " +
                "AND (o.orderedDateTime  between '" + begin + "' AND '" + end + "') " +
                " order by orderedDateTime ASC");
        return  query.list();
    }

    public List<Order> getCompletedOrdersOfCustomer(long customerId, Timestamp begin, Timestamp end) throws SQLException {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Order o where o.orderStatus = '"+ Order.OrderStatus.DELIVERED + "'" +
                ((customerId != 0) ? " and o.customerId = " + customerId : "") + " " +
                "AND (o.orderedDateTime  between '" + begin + "' AND '" + end + "') " +
                " order by orderedDateTime ASC");
        return  query.list();
    }

    public List<Order> getTaxiOrders(long id) throws SQLException {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Order o where o.taxiId = "+ id + " order by orderedDateTime ASC");
        return  query.list();
    }


    public List<Order> getCustomerOrders(long id, Timestamp begin, Timestamp end) throws SQLException {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Order o where o.customerId = " + id  +
                " AND (o.orderedDateTime  between '" + begin + "' AND '" + end + "') " +
                " order by orderedDateTime ASC");
        return  query.list();
    }

    @Override
    public Order getByLogin(String login) throws SQLException {
        return null;
    }
}
