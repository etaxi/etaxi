package lv.etaxi.business;

import lv.etaxi.dao.TaxiDAO;
import lv.etaxi.dao.jdbc.TaxiDAOImpl;
import lv.etaxi.entity.Taxi;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/** Проект etaxi
 * Класс для реализации функций над списком такси
 */
public class TaxiManagerImpl implements TaxiManager {

    private TaxiDAO taxiDAO;

    public TaxiManagerImpl() {

        this.taxiDAO = new TaxiDAOImpl();
    }

    public Taxi findTaxiByLogin(String login) throws SQLException {

        return taxiDAO.getByLogin(login);

    }

    public Taxi findTaxiById(long Id) throws SQLException {

        return taxiDAO.getById(Id);

    }

    public void createNewTaxi(Taxi taxi) throws SQLException {

        taxi.setTaxiId(taxiDAO.update(taxi));

    }

    public void updateTaxi(Taxi taxi) throws SQLException {

        taxiDAO.update(taxi);

    }


}

