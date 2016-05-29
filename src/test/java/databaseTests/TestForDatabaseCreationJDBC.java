package databaseTests;

import lv.etaxi.dao.databaseCreation.DatabaseCreation;
import lv.etaxi.dao.databaseCreation.jdbc.DatabaseCreationJdbcImpl;
import org.junit.Test;

import java.sql.SQLException;

/** Проект etaxi
 * JUnit тесты для проекта etaxi (создание базы данных и таблиц в базе данных)
 * */
public class TestForDatabaseCreationJDBC {

    @Test
    public void testСreateDataBase() throws SQLException {

        DatabaseCreation databaseCreation = new DatabaseCreationJdbcImpl();

        databaseCreation.createDatabase(true);
        databaseCreation.createTableForCustomers();
        databaseCreation.createTableForAdmins();
        databaseCreation.createTableForTaxi();
        databaseCreation.createTableForOrders();

    }

}