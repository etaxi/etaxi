package lv.etaxi.dao;

import lv.etaxi.entity.Customer;

import java.sql.SQLException;
import java.util.List;

/** Проект etaxi
 * Интерфейс для реализации управления объектами класса Customer
 * */
public interface CustomerDAO {

    /** Возвращает объект соответствующий записи с первичным ключом key или null */
    Customer getById(long id) throws SQLException;

    /** Возвращает объект соответствующий записи с таким логином (он же номер телефона для данной таблицы) */
    Customer getByLogin(String phone) throws SQLException;

    /** Сохраняет состояние объекта Customer в базе данных (если ID нет, создаем новую запись) */
    long update(Customer customer) throws SQLException;

    /** Удаляет запись об объекте из базы данных */
    void delete(Customer customer) throws SQLException;

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    List<Customer> getAll() throws SQLException;

    /** Создает таблицу в базе данных для хранения объектов класса Customer */
    void createTable() throws SQLException;
}
