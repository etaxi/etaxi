package lv.etaxi.dao.hibernate;

import lv.etaxi.dao.CustomerDAO;
import lv.etaxi.entity.Customer;
import lv.etaxi.entity.Order;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/** Проект etaxi
 * Реализация управления объектами класса Customer
 * */
@SuppressWarnings("ALL")
@Repository
public class CustomerHibernateDAOImpl implements CustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Customer getById(long id) throws SQLException {

        Session session = sessionFactory.getCurrentSession();
        return (Customer) session.get(Customer.class, id);
    }

    public Customer getByLogin(String phone) throws SQLException {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Customer.class);
        return (Customer) criteria.add(Restrictions.eq("phone", phone)).uniqueResult();
    }

    public long update(Customer customer) throws SQLException {

        long id = customer.getCustomerId();
        Session session = sessionFactory.getCurrentSession();
        //Transaction transaction = session.beginTransaction();
        if (id == 0) {
            id = (Long) session.save(customer);
        }
        else {
            session.update(customer);
        }
        //transaction.commit();
        return id;
    }

    public void delete(Customer customer) throws SQLException {

        Session session = sessionFactory.getCurrentSession();
        //Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Order o where o.customerId = " + customer.getCustomerId());
        List<Order> orderList = query.list();
        for (Order order : orderList) {
            session.delete(order);
        }
        session.delete(customer);
        //transaction.commit();
    }

    public List<Customer> getAll() throws SQLException {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Customer");
        return  query.list();
    }

    public void createTable() throws SQLException {

        Session session = sessionFactory.getCurrentSession();
        //Transaction transaction = session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS customers(" +
                            "   Id bigint(9) NOT NULL auto_increment," +
                            "   name varchar(256)," +
                            "   phone varchar(256)," +
                            "   password varchar(256)," +
                            "   PRIMARY KEY (Id)" +
                            "   );").executeUpdate();
        //transaction.commit();
    }

}
