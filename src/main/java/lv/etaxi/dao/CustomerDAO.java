package lv.etaxi.dao;

import lv.etaxi.entity.Customer;
import org.hibernate.HibernateException;

import java.sql.SQLException;
import java.util.List;

/** Проект etaxi
 * Интерфейс для реализации управления объектами класса Customer
 * */
public interface CustomerDAO {

    /** Возвращает объект соответствующий записи с первичным ключом key или null */
    Customer getById(long id) throws SQLException, HibernateException;

    /** Возвращает объект соответствующий записи с таким логином (он же номер телефона для данной таблицы) */
    Customer getByLogin(String phone) throws SQLException, HibernateException;

    /** Сохраняет состояние объекта Customer в базе данных (если ID нет, создаем новую запись) */
    long update(Customer customer) throws SQLException, HibernateException;

    /** Удаляет запись об объекте из базы данных */
    void delete(Customer customer) throws SQLException, HibernateException;

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    List<Customer> getAll() throws SQLException, HibernateException;

    /** Создает таблицу в базе данных для хранения объектов класса Customer */
    void createTable() throws SQLException, HibernateException;
}
