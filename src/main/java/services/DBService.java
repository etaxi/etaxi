package services;

import dao.*;
import executor.Executor;

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

public class DBService {

    private final Connection connection;

    public DBService() {
        this.connection = getMysqlConnection();
    }

    public void createDataBaseWithTables() throws SQLException {

        Executor executor = new Executor(connection);
        executor.executeUpdate("CREATE DATABASE IF NOT EXISTS etaxi;");

        CustomerDAO customerDao = new CustomerDAO(connection);
        customerDao.createTable();

        OrderDAO orderDao = new OrderDAO(connection);
        orderDao.createTable();

        TaxiDAO taxiDao = new TaxiDAO(connection);
        taxiDao.createTable();

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

            fis = new FileInputStream("src/main/resources/config.properties");
            property.load(fis);
            String host = property.getProperty("db.host");
            String port = property.getProperty("db.port");
            String login = property.getProperty("db.login");
            String password = property.getProperty("db.password");

            StringBuilder url = new StringBuilder();
            url.
                    append("jdbc:mysql://")     //db type
                    .append(host)
                    .append(":")
                    .append(port)
                    .append("/?")
                    .append("user=")
                    .append(login)
                    .append("&")
                    .append("password=")
                    .append(password);

            return url.toString();

        } catch (IOException e) {
            System.err.println("Error: properties file not found!");
            return null;
        }
    }
}
