package lv.etaxi.dao.hibernate;

import lv.etaxi.dao.AdminDAO;
import lv.etaxi.entity.Admin;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@SuppressWarnings("ALL")
@Repository
public class AdminHibernateDAOImpl extends DAOImpl<Admin> implements AdminDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Admin getByLogin(String login) throws SQLException {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Admin.class);
        return (Admin) criteria.add(Restrictions.eq("login", login)).uniqueResult();
    }

}
