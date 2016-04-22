package databaseTests;

import org.junit.Test;
import lv.etaxi.dao.DBConnection;

import java.sql.SQLException;

/** Проект etaxi
 * JUnit тесты для проекта etaxi (design patterns "Object Mother" and "Test Data Builder")
 * */
public class TestForDatabaseCreationJDBC {

    @Test
    public void testСreateDataBase() throws SQLException {

        DBConnection dbConnection = new DBConnection();
        dbConnection.createDataBaseWithJDBC();

    }
}