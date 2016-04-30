package lv.etaxi.dao.hibernate;

import lv.etaxi.dao.TaxiDAO;
import lv.etaxi.entity.Taxi;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

/** Проект etaxi
 * Реализация управления объектами класса Taxi
 * */
@SuppressWarnings("ALL")
@Repository
public class TaxiHibernateDAOImpl extends DAOImpl<Taxi> implements TaxiDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Taxi getByLogin(String login) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Taxi.class);
        return (Taxi) criteria.add(Restrictions.eq("login", login)).uniqueResult();
    }

}
