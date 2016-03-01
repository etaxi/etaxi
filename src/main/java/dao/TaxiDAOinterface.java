package dao;

import dataSets.TaxiDataSet;

import java.sql.SQLException;
import java.util.List;

/** Проект etaxi
 * Интерфейс для реализации управления объектами класса TaxiDataSet
 */
public interface TaxiDAOinterface {

    /** Возвращает объект соответствующий записи с первичным ключом key или null */
    public TaxiDataSet getById(long id) throws SQLException;

    /** Сохраняет состояние объекта Customer в базе данных (если ID нет, создаем новую запись) */
    public void update(TaxiDataSet customer);

    /** Удаляет запись об объекте из базы данных */
    public void delete(TaxiDataSet customer);

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<TaxiDataSet> getAll() throws SQLException;

    /** Создает таблицу в базе данных для хранения объектов класса TaxiDataSet */
    public void createTable() throws SQLException;

}

