package databaseTests;

import lv.etaxi.builders.TaxiBuilder;
import lv.etaxi.dao.TaxiDAO;
import lv.etaxi.dao.jdbc.TaxiDAOImpl;
import lv.etaxi.entity.Taxi;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/** Проект etaxi
 * JUnit тесты для проекта etaxi (design patterns "Object Mother" and "Test Data Builder")
 * */

public class TestsForTaxiJDBC {

    public TaxiDAO aTaxiDAO() {

        return new TaxiDAOImpl();
    }


    @Test
    public void testNewTaxisRecord() throws SQLException {

        TaxiDAO taxiDAO = aTaxiDAO();

        TaxiBuilder taxiBuilder = TaxiBuilder.aTaxi()
                .withName("Sebastian Vettel")
                .withLogin("Vettel")
                .withPassword("sebastian12345");

        Taxi taxi1 = taxiBuilder.build();
        long newTaxiID1 = taxiDAO.create(taxi1);

        Taxi taxi2 = TaxiBuilder.aTaxi().build();  //USE "DEFAULT USER"
        long newTaxiID2 = taxiDAO.create(taxi2);

    }

    @Test
    public void testUpdateTaxiRecord() throws SQLException {

        TaxiDAO taxiDAO = aTaxiDAO();

        TaxiBuilder taxiBuilder = TaxiBuilder.aTaxi()
                .withId((long) 0)
                .withName("Lewis Carl Davidson Hamilton")
                .withCar("BMW 530 SL")
                .withPhone("(+371) 20100100")
                .withLogin("hamilton")
                .withPassword("lewis123");

        Taxi taxi = taxiBuilder.build();
        taxi.setTaxiId(taxiDAO.create(taxi));
        taxi.setName("Jenson Alexander Lyons Button");
        taxiDAO.update(taxi);

    }

    @Test
    public void testGetTaxiByID() throws SQLException {

        TaxiDAO taxiDAO = aTaxiDAO();

        TaxiBuilder taxiBuilder = TaxiBuilder.aTaxi()
                .withId((long) 0)
                .withName("Kimi-Matias Räikkönen")
                .withPhone("(+370) 777 5555")
                .withLogin("kimi")
                .withPassword("matki");

        Taxi taxi = taxiBuilder.build();
        taxi.setTaxiId(taxiDAO.create(taxi));

        Taxi taxiGetById = taxiDAO.getById(taxi.getTaxiId());
        assertEquals(taxi.getTaxiId(), taxiGetById.getTaxiId());
    }

    @Test
    public void testDeleteTaxiByID() throws SQLException {

        TaxiDAO taxiDAO = aTaxiDAO();

        Taxi taxi = TaxiBuilder.aTaxi().build();
        taxi.setTaxiId(taxiDAO.create(taxi));

        int countOfTaxisBeforeDeleteOperation = taxiDAO.getAll().size();

        taxiDAO.delete(taxi);

        int countOfTaxisAfterDeleteOperation = taxiDAO.getAll().size();

        assertTrue(countOfTaxisBeforeDeleteOperation-1 == countOfTaxisAfterDeleteOperation);

    }




}