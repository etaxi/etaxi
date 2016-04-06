package dao.jdbc;

import dao.*;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.*;
import java.util.Properties;

/** Проект etaxi
 *  подключение к базе
 *  и первоначальное создание базы данных MySQL
 */

public class DBConnection {
    private static final String DB_CONFIG_FILE = "config.properties";
    private final Connection connection;
    private String databaseName;

    public DBConnection() {
        this.connection = getMysqlConnection();
        this.databaseName = getDatabaseNameFromFile();
    }

    public Connection getConnection() {
        return connection;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void createDataBaseWithTables() throws SQLException {

        Executor executor = new Executor(connection, "");
        executor.executeUpdate("CREATE DATABASE IF NOT EXISTS " + databaseName);

        CustomerDAO customerDao = new CustomerDAOImpl(connection, databaseName);
        customerDao.createTable();

        OrderDAO orderDao = new OrderDAOImpl(connection, databaseName);
        orderDao.createTable();

        TaxiDAO taxiDao = new TaxiDAOImpl(connection, databaseName);
        taxiDao.createTable();

        AdminDAO adminDAO = new AdminDAOImpl(connection, databaseName);
        adminDAO.createTable();

    }


    public Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            return DriverManager.getConnection(getDBUrl());

        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void printConnectInfo() {
        try {
            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
            System.out.println("Autocommit: " + connection.getAutoCommit());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getDBUrl(){
        FileInputStream fis;
        Properties property = new Properties();

        try {
            property.load(DBConnection.class.getClassLoader().getResourceAsStream(DB_CONFIG_FILE));

            String host = property.getProperty("db.host");
            String port = property.getProperty("db.port");
            String login = property.getProperty("db.login");
            String password = property.getProperty("db.password");
            //String database = property.getProperty("db.database");

            StringBuilder url = new StringBuilder();
            url.
                     append("jdbc:mysql://")     //db type
                    .append(host)
                    .append(":")
                    .append(port)
                    //.append("/")
                    //.append(database)
                    .append("?")
                    .append("user=")
                    .append(login)
                    .append("&")
                    .append("password=")
                    .append(password);

            return url.toString();

        } catch (IOException e) {
            System.out.println("Exception while reading JDBC configuration from file = " + DB_CONFIG_FILE);
            return null;
        }
    }

    private String getDatabaseNameFromFile(){

        Properties property = new Properties();

        try {
            property.load(DBConnection.class.getClassLoader().getResourceAsStream(DB_CONFIG_FILE));
            return property.getProperty("db.database");

        } catch (IOException e) {
            System.err.println("Error: properties file not found!");
            return null;
        }
    }

}
