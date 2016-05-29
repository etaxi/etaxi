package lv.etaxi.dao.databaseCreation.jdbc;

import lv.etaxi.dao.DBConnection;
import lv.etaxi.dao.Executor;
import lv.etaxi.dao.databaseCreation.DatabaseCreation;

import java.sql.SQLException;

/**
 * Created by D.Lazorkin on 30.04.2016.
 */
public class DatabaseCreationJdbcImpl implements DatabaseCreation {

    public void createTableForCustomers() throws SQLException {
        Executor executor = GetExecutor();
        executor.executeUpdate("CREATE TABLE IF NOT EXISTS customers(" +
                "   Id bigint(9) NOT NULL auto_increment," +
                "   name varchar(256)," +
                "   phone varchar(256)," +
                "   password varchar(256)," +
                "   PRIMARY KEY (Id)" +
                "   );");
    }

    public void createTableForAdmins() throws SQLException {
        Executor executor = GetExecutor();
        executor.executeUpdate("CREATE TABLE IF NOT EXISTS admins(" +
                "   Id bigint(9) NOT NULL auto_increment," +
                "   name varchar(256)," +
                "   login varchar(256)," +
                "   password varchar(256)," +
                "   PRIMARY KEY (Id)" +
                "   );");
    }

    public void createTableForOrders() throws SQLException {
        Executor executor = GetExecutor();
        executor.executeUpdate("CREATE TABLE IF NOT EXISTS orders (" +
                "  Id bigint(9) NOT NULL auto_increment," +
                "  customerId bigint(9)," +
                "  datetime datetime," +
                "  ordereddatetime datetime," +
                "  orderStatus text," +
                "  fromAdress text," +
                "  toAdress text," +
                "  taxiId bigint(9)," +
                "  distance double," +
                "  price double," +
                "  rate int(2)," +
                "  feedback text," +
                "  PRIMARY KEY (Id)," +
                "  FOREIGN KEY (customerId) REFERENCES customers(Id)," +
                "  FOREIGN KEY (taxiId) REFERENCES taxis(Id)" +
                ");");
    }

    public void createTableForTaxi() throws SQLException {
        Executor executor = GetExecutor();
        executor.executeUpdate("CREATE TABLE IF NOT EXISTS taxis (" +
                "  Id bigint(9) NOT NULL auto_increment," +
                "  name varchar(256)," +
                "  phone varchar(256)," +
                "  taxiStatus int(1)," +
                "  location varchar(256)," +
                "  car varchar(256)," +
                "  login varchar(256)," +
                "  password varchar(256)," +
                "  rating double," +
                "  PRIMARY KEY  (Id)" +
                ");");
    }

    public void createDatabase(boolean dropDatabase) throws SQLException {

        DBConnection dbConnection = new DBConnection();
        Executor executor = new Executor(dbConnection.getMysqlConnection(), "");
        if (dropDatabase) {
            executor.executeUpdate("DROP DATABASE IF EXISTS " + dbConnection.getDatabasePropertyFromFile("db.database"));
        }
        executor.executeUpdate("CREATE DATABASE IF NOT EXISTS " + dbConnection.getDatabasePropertyFromFile("db.database"));

    }

    private Executor GetExecutor() {

        DBConnection dbService = new DBConnection();
        return (new Executor(dbService.getConnection(), dbService.getDatabaseName()));

    }

}
