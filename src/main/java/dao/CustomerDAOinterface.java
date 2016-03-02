package dao;

import dataSets.CustomerDataSet;

import java.sql.SQLException;
import java.util.List;

/** Проект etaxi
 * Интерфейс для реализации управления объектами класса CustomerDataSet
 * */
public interface CustomerDAOinterface {

    /** Возвращает объект соответствующий записи с первичным ключом key или null */
    CustomerDataSet getById(long id) throws SQLException;

    /** Сохраняет состояние объекта Customer в базе данных (если ID нет, создаем новую запись) */
    long update(CustomerDataSet customer) throws SQLException;

    /** Удаляет запись об объекте из базы данных */
    void delete(CustomerDataSet customer) throws SQLException;

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    List<CustomerDataSet> getAll() throws SQLException;

    /** Создает таблицу в базе данных для хранения объектов класса CustomerDataSet */
    void createTable() throws SQLException;
}
