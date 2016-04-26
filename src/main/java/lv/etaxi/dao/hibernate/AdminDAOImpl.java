package lv.etaxi.dao.hibernate;

import lv.etaxi.dao.AdminDAO;
import lv.etaxi.dao.DBException;
import lv.etaxi.entity.Admin;
import lv.etaxi.entity.Order;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Genady Zalesky on 17.04.2016
 */

@Component("HibAdminDAO")
@SuppressWarnings("ALL")
@Repository
public class AdminDAOImpl implements AdminDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public long create(Admin admin) throws SQLException {

        Session session = sessionFactory.getCurrentSession();
        long id = (Long) session.save(admin);
        return id;
    }

    public void update(Admin admin) throws SQLException {

        Session session = sessionFactory.getCurrentSession();
        session.update(admin);
    }

    public void delete(Admin admin) throws SQLException {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Order o where o.customerId = " + admin.getAdminId());
        List<Order> orderList = query.list();
        for (Order order : orderList) {
            session.delete(order);
        }
        session.delete(admin);
    }


    public List<Admin> getAll() throws SQLException {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Admin");
        return  query.list();
    }

    public Admin getById(long id) throws SQLException {

        Session session = sessionFactory.getCurrentSession();
        return (Admin) session.get(Admin.class, id);
    }


    public Admin getByLogin(String login) throws SQLException {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Admin.class);
        return (Admin) criteria.add(Restrictions.eq("login", login)).uniqueResult();
    }

    public void createTable() throws SQLException {

        Session session = sessionFactory.getCurrentSession();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS customers(" +
                "   Id bigint(9) NOT NULL auto_increment," +
                "   name varchar(256)," +
                "   login varchar(256)," +
                "   password varchar(256)," +
                "   PRIMARY KEY (Id)" +
                "   );").executeUpdate();
    }
}
