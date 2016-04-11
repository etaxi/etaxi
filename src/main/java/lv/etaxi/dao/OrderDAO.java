package lv.etaxi.dao;

import lv.etaxi.entity.Order;
import org.hibernate.HibernateException;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/** Проект etaxi
 * Интерфейс для реализации управления объектами класса Order
 */

public interface OrderDAO {

    /** Возвращает объект соответствующий записи с первичным ключом key или null */
    Order getById(long id) throws SQLException, HibernateException;

    /** Сохраняет состояние объекта Order в базе данных (если ID нет, создаем новую запись) */
    long update(Order order) throws SQLException, HibernateException;

    /** Удаляет запись об объекте из базы данных */
    void delete(Order order) throws SQLException, HibernateException;

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    List<Order> getAll() throws SQLException, HibernateException;

    /** Возвращает список открытых заказов */
    List<Order> getOpenOrdersAll() throws SQLException, HibernateException;

    /** Возвращает список открытых заказов */
    List<Order> getOpenOrdersOfCustomer(long customerId, Timestamp begin, Timestamp end) throws SQLException, HibernateException;

    /** Возвращает список открытых заказов */
    List<Order> getCompletedOrdersOfCustomer(long customerId, Timestamp begin, Timestamp end) throws SQLException, HibernateException;

    /** Возвращает список заказов такси*/
    List<Order> getTaxiOrders(long id) throws SQLException, HibernateException;

    /** Возвращает список заказов по клиенту*/
    List<Order> getCustomerOrders(long id, Timestamp begin, Timestamp end) throws SQLException, HibernateException;

    /** Создает таблицу в базе данных для хранения объектов класса Order */
    void createTable() throws SQLException, HibernateException;

}

