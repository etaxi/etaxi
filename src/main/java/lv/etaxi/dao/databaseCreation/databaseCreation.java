package lv.etaxi.dao.databaseCreation;

import java.sql.SQLException;

/**
 * Created by D.Lazorkin on 30.04.2016.
 */
public interface DatabaseCreation {

    /** Создает таблицу в базе данных для хранения объектов класса Customer */
    void createTableForCustomers() throws SQLException;

    /** Создает таблицу в базе данных для хранения объектов класса Admin */
    void createTableForAdmins() throws SQLException;

    /** Создает таблицу в базе данных для хранения объектов класса Order */
    void createTableForOrders() throws SQLException;

    /** Создает таблицу в базе данных для хранения объектов класса Taxi */
    void createTableForTaxi() throws SQLException;

    /** Создает саму базу данных */
    void createDatabase(boolean dropDatabase) throws SQLException;

}
