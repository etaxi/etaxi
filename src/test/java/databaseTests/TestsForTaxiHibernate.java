package databaseTests;

import lv.etaxi.dao.TaxiDAO;
import lv.etaxi.dao.hibernate.TaxiHibernateDAOImpl;
import lv.etaxi.entity.Taxi;
import org.junit.Test;

import java.sql.SQLException;


/** Проект etaxi
 * JUnit тесты для проекта etaxi (design patterns "Object Mother" and "Test Data Builder")
 * */

public class TestsForTaxiHibernate {

    public TaxiDAO aTaxiDAO() {
        return new TaxiHibernateDAOImpl();
    }

    @Test
    public void testNewTaxiRecord() throws SQLException {

        TaxiBuilder taxiBuilder = TaxiBuilder.aTaxi()
                .withName("Jenson Alexander Lyons Button")
                .withLogin("Lyons")
                .withPassword("lybutton");

        Taxi taxi = taxiBuilder.build();
        long newTaxiID = aTaxiDAO().update(taxi);
    }


    @Test
    public void testNewTaxisRecord() throws SQLException {

        TaxiBuilder taxiBuilder = TaxiBuilder.aTaxi()
                .withName("Sebastian Vettel")
                .withLogin("Vettel")
                .withPassword("sebastian12345");

        Taxi taxi = taxiBuilder.build();
        long newTaxiID = aTaxiDAO().update(taxi);

    }


}