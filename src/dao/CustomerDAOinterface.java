package dao;

import dataSets.CustomerDataSet;
import java.sql.SQLException;
import java.util.List;

/** Проект etaxi
 * Интерфейс для реализации управления объектами класса CustomerDataSet
 * */
public interface CustomerDAOinterface {

    /** Возвращает объект соответствующий записи с первичным ключом key или null */
    public CustomerDataSet getById(long id) throws SQLException;

    /** Сохраняет состояние объекта Customer в базе данных (если ID нет, создаем новую запись) */
    public void update(CustomerDataSet customer);

    /** Удаляет запись об объекте из базы данных */
    public void delete(CustomerDataSet customer);

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<CustomerDataSet> getAll() throws SQLException;

    /** Создает таблицу в базе данных для хранения объектов класса CustomerDataSet */
    public void createTable() throws SQLException;
}
