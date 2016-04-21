package lv.etaxi.dao.jdbc;

import lv.etaxi.dao.hibernate.CustomerHibernateDAOImpl;
import lv.etaxi.dao.hibernate.OrderHibernateDAOImpl;
import lv.etaxi.dao.hibernate.TaxiHibernateDAOImpl;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

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

    public void createDataBaseWithJDBC() throws SQLException {

        Executor executor = new Executor(connection, "");
        executor.executeUpdate("CREATE DATABASE IF NOT EXISTS " + databaseName);

        new CustomerDAOImpl().createTable();

        new OrderDAOImpl().createTable();

        new TaxiDAOImpl().createTable();

        new AdminDAOImpl().createTable();

    }

    public void createDataBaseWithHibernate() throws SQLException {

        Executor executor = new Executor(connection, "");
        executor.executeUpdate("CREATE DATABASE IF NOT EXISTS " + databaseName);

        new CustomerHibernateDAOImpl().createTable();

        new OrderHibernateDAOImpl().createTable();

        new TaxiHibernateDAOImpl().createTable();

        //new AdminHibernateDAOImpl().createTable();

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

    public Configuration getMySqlConfigurationForHibernate() {

        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(lv.etaxi.entity.Customer.class);
        configuration.addAnnotatedClass(lv.etaxi.entity.Order.class);
        configuration.addAnnotatedClass(lv.etaxi.entity.Taxi.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://" +
                getDatabasePropertyFromFile("db.host") + ":"+
                getDatabasePropertyFromFile("db.port") + "/" +
                getDatabasePropertyFromFile("db.database"));
        configuration.setProperty("hibernate.connection.username", getDatabasePropertyFromFile("db.login"));
        configuration.setProperty("hibernate.connection.password", getDatabasePropertyFromFile("db.password"));
        configuration.setProperty("hibernate.show_sql", "true");
        //configuration.setProperty("hibernate.hbm2ddl.auto", "validate");

        return configuration;
    }

    public SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
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
