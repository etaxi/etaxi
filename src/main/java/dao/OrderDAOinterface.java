package dao;

import java.sql.SQLException;

/** Проект etaxi
 * Интерфейс для реализации управления объектами класса OrderDataSet
 */

public interface OrderDAOinterface {

    /** Создает таблицу в базе данных для хранения объектов класса OrderDataSet */
    public void createTable() throws SQLException;

}

