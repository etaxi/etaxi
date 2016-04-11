package lv.etaxi.dao.hibernate;

import lv.etaxi.dao.CustomerDAO;
import lv.etaxi.dao.jdbc.DBConnection;
import lv.etaxi.entity.Customer;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/** Проект etaxi
 * Реализация управления объектами класса Customer
 * */
@SuppressWarnings("ALL")
@Repository
public class CustomerHibernateDAOImpl implements CustomerDAO {

    private final SessionFactory sessionFactory;

    public CustomerHibernateDAOImpl() {
        DBConnection dbConnection = new DBConnection(); 
        Configuration configuration = dbConnection.getMySqlConfigurationForHibernate();
        this.sessionFactory = dbConnection.createSessionFactory(configuration);
    }

    public Customer getById(long id) throws HibernateException {

        Session session = sessionFactory.openSession();
        return (Customer) session.get(Customer.class, id);
    }

    public Customer getByLogin(String phone) throws HibernateException {

        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Customer.class);
        return (Customer) criteria.add(Restrictions.eq("phone", phone)).uniqueResult();
    }

    public long update(Customer customer) throws HibernateException {

        long id = customer.getCustomerId();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        if (id == 0) {
            id = (Long) session.save(customer);
        }
        else {
            session.update(customer);
        }
        transaction.commit();
        session.close();
        return id;
    }

    public void delete(Customer customer) throws HibernateException {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(customer);
        transaction.commit();
        session.close();

            //connection.setAutoCommit(false);
//            executor.executeUpdate("DELETE FROM customers WHERE Id=" + customer.getCustomerId());
//            executor.executeUpdate("DELETE FROM orders WHERE orders.customerId=" + customer.getCustomerId());
            //connection.commit();
    }

    public List<Customer> getAll() throws HibernateException {

        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM Customer");
        return  query.list();
    }

    public void createTable() throws SQLException {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS customers(" +
                            "   Id bigint(9) NOT NULL auto_increment," +
                            "   name varchar(256)," +
                            "   phone varchar(256)," +
                            "   password varchar(256)," +
                            "   PRIMARY KEY (Id)" +
                            "   );").executeUpdate();
        transaction.commit();
        session.close();
    }

}
