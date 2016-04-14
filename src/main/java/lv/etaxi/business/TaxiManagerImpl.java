package lv.etaxi.business;

import lv.etaxi.dao.CustomerDAO;
import lv.etaxi.dao.TaxiDAO;
import lv.etaxi.dao.hibernate.CustomerHibernateDAOImpl;
import lv.etaxi.dao.hibernate.TaxiHibernateDAOImpl;
import lv.etaxi.dao.jdbc.CustomerDAOImpl;
import lv.etaxi.dao.jdbc.DBConnection;
import lv.etaxi.dao.jdbc.TaxiDAOImpl;
import lv.etaxi.entity.Taxi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

/** Проект etaxi
 * Класс для реализации функций над списком такси
 */
@Service
public class TaxiManagerImpl implements TaxiManager {

    //@Qualifier("customerHibernateDAOImpl")
    @Autowired
    private TaxiDAO taxiDAO;

    public TaxiManagerImpl() {

        this.taxiDAO = (DBConnection.getDatabasePropertyFromFile("db.hibernate").equals("YES")) ?
                new TaxiHibernateDAOImpl() : new TaxiDAOImpl();

    }

    public Taxi findTaxiByLogin(String login) throws SQLException {

        return taxiDAO.getByLogin(login);

    }

    public Taxi findTaxiById(long Id) throws SQLException {

        return taxiDAO.getById(Id);

    }

    @Transactional
    public void createNewTaxi(Taxi taxi) throws SQLException {

        taxi.setTaxiId(taxiDAO.update(taxi));

    }

    @Transactional
    public void updateTaxi(Taxi taxi) throws SQLException {

        taxiDAO.update(taxi);

    }

}

