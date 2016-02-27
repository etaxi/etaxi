package services;

import dao.*;
import executor.Executor;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Проект etaxi
 * * первоначальное создание базы данных MySQL
 */

public class DBService {

    private final Connection connection;

    public DBService() {
        this.connection = getMysqlConnection();
    }

    public void createDataBaseWithTables() throws SQLException {

        Executor executor = new Executor(connection);
        executor.executeUpdate("CREATE DATABASE IF NOT EXISTS etaxi;");
        executor.executeUpdate("USE etaxi;");

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

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/?").               //port
                    //append("etaxi?").             //db name
                    append("user=root&").           //login
                    append("password=dimok");       //password

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;

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

}
