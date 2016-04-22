package lv.etaxi.business.managers;

import lv.etaxi.business.TaxiManager;
import lv.etaxi.dao.TaxiDAO;
import lv.etaxi.entity.Taxi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;

/** Проект etaxi
 * Класс для реализации функций над списком такси
 */
@Service
public class TaxiManagerImpl implements TaxiManager {

    //@Qualifier("customerHibernateDAOImpl")
    @Autowired
    private TaxiDAO taxiDAO;

    @Transactional
    public void create(Taxi taxi) throws SQLException {

        taxi.setTaxiId(taxiDAO.update(taxi));
    }

    @Transactional
    public void delete(Taxi taxi) throws SQLException {

        taxiDAO.delete(taxi);
    }

    @Transactional
    public void update(Taxi taxi) throws SQLException {

        taxiDAO.update(taxi);
    }

    @Transactional
    public Taxi findById(long Id) throws SQLException {

        return taxiDAO.getById(Id);
    }

    @Transactional
    public Taxi findByLogin(String login) throws SQLException {

        return taxiDAO.getByLogin(login);
    }
}

