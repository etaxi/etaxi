package databaseTests;

import lv.etaxi.config.SpringAppConfig;
import lv.etaxi.dao.databaseCreation.DatabaseCreation;
import lv.etaxi.dao.databaseCreation.hibernate.DatabaseCreationHibernateImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;

/** Проект etaxi
 * JUnit тесты для проекта etaxi (design patterns "Object Mother" and "Test Data Builder")
 * */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAppConfig.class)

public class TestForDatabaseCreationHibernate {

    @Test
    public void testСreateDataBase() throws SQLException {

        DatabaseCreation databaseCreation = new DatabaseCreationHibernateImpl();

        databaseCreation.createDatabase(true);
        databaseCreation.createTableForCustomers();
        databaseCreation.createTableForAdmins();
        databaseCreation.createTableForTaxi();
        databaseCreation.createTableForOrders();

    }
}