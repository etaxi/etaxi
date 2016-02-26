package dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Фабрика объектов для работы с базой данных
**/

public interface DAOFactory {

    /** Возвращает подключение к базе данных */
    public Connection getConnection() throws SQLException;

    /** Возвращает объект для управления персистентным состоянием объекта Taxi */
    public TaxiDAO getTaxiDao(Connection connection);

    /** Возвращает объект для управления персистентным состоянием объекта Customer */
    public CustomerDAO getCustomerDao(Connection connection);

    /** Возвращает объект для управления персистентным состоянием объекта Customer */
    public OrderDAO getOrderDao(Connection connection);

}

