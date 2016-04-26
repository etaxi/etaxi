package lv.etaxi.dao.hibernate;

import lv.etaxi.dao.DBException;
import lv.etaxi.dao.TaxiDAO;
import lv.etaxi.entity.Taxi;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/** Проект etaxi
 * Реализация управления объектами класса Taxi
 * */
@Component("HibTaxiDAO")
@SuppressWarnings("ALL")
@Repository
public class TaxiDAOImpl extends DAOImpl<Taxi> implements TaxiDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public long create(Taxi taxi) throws SQLException {

        Session session = sessionFactory.getCurrentSession();
        long id = (Long) session.save(taxi);
        return id;
    }

    public void update(Taxi taxi) throws SQLException {

        long id = taxi.getTaxiId();
        Session session = sessionFactory.getCurrentSession();
        session.update(taxi);
    }

    public void delete(Taxi taxi) throws SQLException {

        Session session = sessionFactory.getCurrentSession();
            // Query query = session.createQuery("FROM Order o where o.taxiId = " + taxi.getTaxiId());
            // List<Order> orderList = query.list();
            // for (Order order : orderList) {
            //   session.delete(order);
            // }
        session.delete(taxi);
    }


    public List<Taxi> getAll() throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Taxis");
        return  query.list();
    }

    public Taxi getById(long id) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        return (Taxi) session.get(Taxi.class, id);
    }


    public Taxi getByLogin(String login) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Taxi.class);
        return (Taxi) criteria.add(Restrictions.eq("login", login)).uniqueResult();
    }

    public void createTable() throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        //Transaction transaction = session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS taxis (" +
                "  Id bigint(9) NOT NULL auto_increment," +
                "  name varchar(256)," +
                "  phone varchar(256)," +
                "  taxiStatus int(1)," +
                "  location varchar(256)," +
                "  car varchar(256)," +
                "  login varchar(256)," +
                "  password varchar(256)," +
                "  rating double," +
                "  PRIMARY KEY  (Id)" +
                ");").executeUpdate();
        //transaction.commit();
    }

}
