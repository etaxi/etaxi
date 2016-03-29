package business;

import dao.TaxiDAO;
import dao.jdbc.TaxiDAOImpl;
import dao.jdbc.DBConnection;
import entity.Taxi;

import java.sql.SQLException;

/** Проект etaxi
 * Класс для реализации функций над списком такси
 */
public class TaxiManager {

    private TaxiDAO taxiDAO;

    public TaxiManager() {
        DBConnection dbService = new DBConnection();
        this.taxiDAO = new TaxiDAOImpl(dbService.getConnection(), dbService.getDatabaseName());
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

