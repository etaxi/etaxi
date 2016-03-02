package dao;

import dataSets.OrderDataSet;

import java.sql.SQLException;
import java.util.List;

/** Проект etaxi
 * Интерфейс для реализации управления объектами класса OrderDataSet
 */

public interface OrderDAOinterface {

    /** Возвращает объект соответствующий записи с первичным ключом key или null */
    OrderDataSet getById(long id) throws SQLException;

    /** Сохраняет состояние объекта Order в базе данных (если ID нет, создаем новую запись) */
    long update(OrderDataSet order) throws SQLException;

    /** Удаляет запись об объекте из базы данных */
    void delete(OrderDataSet order) throws SQLException;

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    List<OrderDataSet> getAll() throws SQLException;

    /** Создает таблицу в базе данных для хранения объектов класса OrderDataSet */
    void createTable() throws SQLException;

}

