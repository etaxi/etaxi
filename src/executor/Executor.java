package executor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/** Проект etaxi
 * Объект для выполнения запросов к базе данных c помощью драйвера JDBC
 * */
public class Executor {
    private final Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public void executeUpdate(String sqlQueryText) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(sqlQueryText);
        statement.close();
    }

    public <T> T executeQuery(String sqlQueryText, ResultHandler<T> handler) throws SQLException {

        Statement statement = connection.createStatement();
        statement.execute(sqlQueryText);
        ResultSet resultSet = statement.getResultSet();
        T value = handler.handle(resultSet);
        resultSet.close();
        statement.close();

        return value;
    }

}

