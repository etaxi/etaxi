package lv.etaxi.dao;

import lv.etaxi.entity.Order;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/** Проект etaxi
 * Интерфейс для реализации управления объектами класса Order
 */

public interface OrderDAO extends BaseDAO<Order> {

     /** Возвращает список открытых заказов */
    List<Order> getOpenOrdersAll() throws SQLException;

    /** Возвращает список открытых заказов */
    List<Order> getOpenOrdersOfCustomer(long customerId, Timestamp begin, Timestamp end) throws SQLException;

    /** Возвращает список открытых заказов */
    List<Order> getCompletedOrdersOfCustomer(long customerId, Timestamp begin, Timestamp end) throws SQLException;

    /** Возвращает список заказов такси*/
    List<Order> getTaxiOrders(long id) throws SQLException;

    /** Возвращает список заказов по клиенту*/
    List<Order> getCustomerOrders(long id, Timestamp begin, Timestamp end) throws SQLException;

}

