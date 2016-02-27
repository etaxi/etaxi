import org.junit.Test;
import services.DBService;

import java.sql.SQLException;

/** Проект etaxi
 * JUnit тесты для проекта etaxi
 * */
public class JUnitTests {

    @Test
    public void tesеСreateDataBaseWithTables() throws SQLException {

        DBService dbService = new DBService();
        dbService.createDataBaseWithTables();

    }

}
