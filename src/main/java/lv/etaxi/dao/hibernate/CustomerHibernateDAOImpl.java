package lv.etaxi.dao.hibernate;

import lv.etaxi.dao.CustomerDAO;
import lv.etaxi.entity.Customer;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

/** Проект etaxi
 * Реализация управления объектами класса Customer
 * */
@SuppressWarnings("ALL")
@Repository
public class CustomerHibernateDAOImpl extends DAOImpl<Customer> implements CustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Customer getByLogin(String phone) throws SQLException {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Customer.class);
        return (Customer) criteria.add(Restrictions.eq("phone", phone)).uniqueResult();
    }
}
