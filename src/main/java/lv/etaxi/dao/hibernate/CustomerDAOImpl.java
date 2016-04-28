package lv.etaxi.dao.hibernate;

import lv.etaxi.dao.BaseDAO;
import lv.etaxi.dao.CustomerDAO;
import lv.etaxi.dao.DBException;
import lv.etaxi.entity.Customer;
import lv.etaxi.entity.Order;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/** Проект etaxi
 * Реализация управления объектами класса Customer
 * */
@Component("HibCustomerDAO")
@SuppressWarnings("ALL")
@Repository
public class CustomerDAOImpl extends DAOImpl<Customer> implements CustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Customer getByLogin(String phone) throws SQLException {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Customer.class);
        return (Customer) criteria.add(Restrictions.eq("phone", phone)).uniqueResult();
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
