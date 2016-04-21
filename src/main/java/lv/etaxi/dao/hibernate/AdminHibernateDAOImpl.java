package lv.etaxi.dao.hibernate;

import lv.etaxi.dao.AdminDAO;
import lv.etaxi.entity.Admin;
import lv.etaxi.entity.Order;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Genady Zalesky on 17.04.2016
 */
@SuppressWarnings("ALL")
@Repository
public class AdminHibernateDAOImpl implements AdminDAO {

    @Autowired
    private SessionFactory sessionFactory;


    public Admin getById(long id) throws SQLException {

        Session session = sessionFactory.getCurrentSession();
        return (Admin) session.get(Admin.class, id);
    }


    public Admin getByLogin(String login) throws SQLException {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Admin.class);
        return (Admin) criteria.add(Restrictions.eq("login", login)).uniqueResult();
    }

    public long update(Admin admin) throws SQLException {

        long id = admin.getAdminId();
        Session session = sessionFactory.getCurrentSession();
        //Transaction transaction = session.beginTransaction();
        if (id == 0) {
            id = (Long) session.save(admin);
        }
        else {
            session.update(admin);
        }
        //transaction.commit();
        return id;
    }


    public void delete(Admin admin) throws SQLException {

        Session session = sessionFactory.getCurrentSession();
        //Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Order o where o.customerId = " + admin.getAdminId());
        List<Order> orderList = query.list();
        for (Order order : orderList) {
            session.delete(order);
        }
        session.delete(admin);
        //transaction.commit();
    }


    public List<Admin> getAll() throws SQLException {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Admin");
        return  query.list();
    }
    
    public void createTable() throws SQLException {

        Session session = sessionFactory.getCurrentSession();
        //Transaction transaction = session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS customers(" +
                "   Id bigint(9) NOT NULL auto_increment," +
                "   name varchar(256)," +
                "   login varchar(256)," +
                "   password varchar(256)," +
                "   PRIMARY KEY (Id)" +
                "   );").executeUpdate();
        //transaction.commit();
    }
}
