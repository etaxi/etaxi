package databaseTests;

import lv.etaxi.config.SpringAppConfig;
import lv.etaxi.dao.TaxiDAO;
import lv.etaxi.entity.Taxi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;


/** Проект etaxi
 * JUnit тесты для проекта etaxi (design patterns "Object Mother" and "Test Data Builder")
 * */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAppConfig.class)

public class TestsForTaxiHibernate {

    @Autowired
    private TaxiDAO taxiDAO;

    @Test
    public void testNewTaxiRecord() throws SQLException {

        TaxiBuilder taxiBuilder = TaxiBuilder.aTaxi()
                .withName("Jenson Alexander Lyons Button")
                .withLogin("Lyons")
                .withPassword("lybutton");

        Taxi taxi = taxiBuilder.build();
        long newTaxiID = taxiDAO.create(taxi);
    }


    @Test
    public void testNewTaxisRecord() throws SQLException {

        TaxiBuilder taxiBuilder = TaxiBuilder.aTaxi()
                .withName("Sebastian Vettel")
                .withLogin("Vettel")
                .withPassword("sebastian12345");

        Taxi taxi = taxiBuilder.build();
        long newTaxiID = taxiDAO.create(taxi);

    }

}