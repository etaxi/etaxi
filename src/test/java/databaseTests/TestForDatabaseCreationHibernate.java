package databaseTests;

import lv.etaxi.config.SpringAppConfig;
import lv.etaxi.dao.databaseCreation.DatabaseCreation;
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

public class TestForDatabaseCreationHibernate {

    @Autowired
    private DatabaseCreation databaseCreation;

    @Test
    public void testСreateDataBase() throws SQLException {

        databaseCreation.createDatabase(true);
        databaseCreation.createTableForCustomers();
        databaseCreation.createTableForAdmins();
        databaseCreation.createTableForTaxi();
        databaseCreation.createTableForOrders();

    }
}