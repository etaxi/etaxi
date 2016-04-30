package lv.etaxi.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
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
        this.databaseName = getDatabasePropertyFromFile("db.database");
    }

    public Connection getConnection() {
        return connection;
    }

    public String getDatabaseName() {
        return databaseName;
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
        Properties property = new Properties();

        try {
            property.load(DBConnection.class.getClassLoader().getResourceAsStream(DB_CONFIG_FILE));

            String host = property.getProperty("db.host");
            String port = property.getProperty("db.port");
            String login = property.getProperty("db.login");
            String password = property.getProperty("db.password");

            StringBuilder url = new StringBuilder();
            url.
                     append("jdbc:mysql://")
                    .append(host)
                    .append(":")
                    .append(port)
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


    public static String getDatabasePropertyFromFile(String propertyName){

        Properties property = new Properties();

        try {
            property.load(DBConnection.class.getClassLoader().getResourceAsStream(DB_CONFIG_FILE));
            return property.getProperty(propertyName);

        } catch (IOException e) {
            System.err.println("Error: properties file not found!");
            return null;
        }
    }

}
