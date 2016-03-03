package dao;

import dataSets.TaxiDataSet;

import java.sql.SQLException;
import java.util.List;

/** Проект etaxi
 * Интерфейс для реализации управления объектами класса TaxiDataSet
 */
public interface TaxiDAO {

    /** Возвращает объект соответствующий записи с первичным ключом key или null */
    TaxiDataSet getById(long id) throws SQLException;

    /** Сохраняет состояние объекта Customer в базе данных (если ID нет, создаем новую запись) */
    long update(TaxiDataSet customer) throws SQLException;

    /** Удаляет запись об объекте из базы данных */
    void delete(TaxiDataSet customer) throws SQLException;

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    List<TaxiDataSet> getAll() throws SQLException;

    /** Создает таблицу в базе данных для хранения объектов класса TaxiDataSet */
    void createTable() throws SQLException;

}

