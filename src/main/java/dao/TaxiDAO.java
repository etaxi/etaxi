package dao;

import executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;

/** Проект etaxi
 * Реализация управления объектами класса TaxiDataSet
 * */
public class TaxiDAO implements TaxiDAOinterface {

    private Executor executor;

    public TaxiDAO(Connection connection) {

        this.executor = new Executor(connection);

    }

    public void createTable() throws SQLException {
        executor.executeUpdate("CREATE TABLE IF NOT EXISTS taxis (" +
                "  taxiId bigint(9) NOT NULL auto_increment," +
                "  name text," +
                "  phone text," +
                "  taxiStatus int(1)," +
                "  location text," +
                "  car text," +
                "  login text," +
                "  password text," +
                "  rating double," +
                "  PRIMARY KEY  (taxiId)" +
                ");");
    }

}
