package databaseTests;

import lv.etaxi.dao.databaseCreation.DatabaseCreation;
import lv.etaxi.dao.databaseCreation.jdbc.DatabaseCreationJDBCImpl;
import org.junit.Test;

import java.sql.SQLException;

/** Проект etaxi
 * JUnit тесты для проекта etaxi (создание базы данных и таблиц в базе данных)
 * */
public class TestForDatabaseCreationJDBC {

    @Test
    public void testСreateDataBase() throws SQLException {

        DatabaseCreation databaseCreation = new DatabaseCreationJDBCImpl();

        databaseCreation.createDatabase(true);
        databaseCreation.createTableForCustomers();
        databaseCreation.createTableForAdmins();
        databaseCreation.createTableForTaxi();
        databaseCreation.createTableForOrders();

    }

}