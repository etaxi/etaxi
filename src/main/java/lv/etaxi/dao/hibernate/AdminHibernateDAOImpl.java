package lv.etaxi.dao.hibernate;

import lv.etaxi.dao.AdminDAO;
import lv.etaxi.dao.jdbc.DBConnection;
import lv.etaxi.entity.Admin;
import lv.etaxi.entity.Order;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Genady Zalesky on 17.04.2016
 */

@SuppressWarnings("ALL")
//@Repository
@Lazy
public class AdminHibernateDAOImpl implements AdminDAO {

    private final SessionFactory sessionFactory;

    public AdminHibernateDAOImpl() {
        DBConnection dbConnection = new DBConnection();
        Configuration configuration = dbConnection.getMySqlConfigurationForHibernate();
        this.sessionFactory = dbConnection.createSessionFactory(configuration);
    }

    public Admin getById(long id) throws SQLException {

        Session session = sessionFactory.openSession();
        return (Admin) session.get(Admin.class, id);
    }


    public Admin getByLogin(String login) throws SQLException {

        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Admin.class);
        return (Admin) criteria.add(Restrictions.eq("login", login)).uniqueResult();
    }

    public long update(Admin admin) throws SQLException {

        long id = admin.getAdminId();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        if (id == 0) {
            id = (Long) session.save(admin);
        }
        else {
            session.update(admin);
        }
        transaction.commit();
        session.close();
        return id;
    }


    public void delete(Admin admin) throws SQLException {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Order o where o.customerId = " + admin.getAdminId());
        List<Order> orderList = query.list();
        for (Order order : orderList) {
            session.delete(order);
        }
        session.delete(admin);
        transaction.commit();
        session.close();
    }


    public List<Admin> getAll() throws SQLException {

        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM Admin");
        return  query.list();
    }
    
    public void createTable() throws SQLException {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS customers(" +
                "   Id bigint(9) NOT NULL auto_increment," +
                "   name varchar(256)," +
                "   login varchar(256)," +
                "   password varchar(256)," +
                "   PRIMARY KEY (Id)" +
                "   );").executeUpdate();
        transaction.commit();
        session.close();
    }
}
