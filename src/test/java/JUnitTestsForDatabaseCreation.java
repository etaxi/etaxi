import org.junit.Test;
import dao.jdbc.DBConnection;

import java.sql.SQLException;

/** Проект etaxi
 * JUnit тесты для проекта etaxi (design patterns "Object Mother" and "Test Data Builder")
 * */
public class JUnitTestsForDatabaseCreation {

    @Test
    public void testСreateDataBaseWithTables() throws SQLException {

        DBConnection dbConnection = new DBConnection();
        dbConnection.createDataBaseWithTables();

    }

}
