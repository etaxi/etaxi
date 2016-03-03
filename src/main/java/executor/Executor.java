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
    private final String databaseName;

    public Executor(Connection connection, String databaseName) {
        this.connection = connection;
        this.databaseName = databaseName;
    }

    public Connection getConnection() {
        return connection;
    }

    public long executeUpdate(String sqlQueryText) throws SQLException {

        Statement statement = connection.createStatement();

        if (!databaseName.isEmpty()) {
            statement.execute("USE " + databaseName);
        }

        statement.execute(sqlQueryText, Statement.RETURN_GENERATED_KEYS);

        long  lastGeneratedKey = 0;
        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next()) {
            lastGeneratedKey = resultSet.getInt(1);
        }
        resultSet.close();
        statement.close();

        return lastGeneratedKey;
    }


    public <T> T executeQuery(String sqlQueryText, ResultHandler<T> handler) throws SQLException {

        Statement statement = connection.createStatement();

        if (!databaseName.isEmpty()) {
            statement.execute("USE " + databaseName);
        }

        statement.execute(sqlQueryText);

        ResultSet resultSet = statement.getResultSet();
        T value = handler.handle(resultSet);
        resultSet.close();
        statement.close();

        return value;
    }

}
