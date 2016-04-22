package lv.etaxi.business;

import lv.etaxi.entity.Customer;
import lv.etaxi.entity.Order;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/** Проект etaxi
 * Created by D.Lazorkin on 25.03.2016.
 * Класс для реализации функций над заказами
 */

public interface OrderManager{

    void create(Order order) throws SQLException;

    void update(Order order) throws SQLException;

    Order findById(long Id) throws SQLException;

    void delete(Order order) throws SQLException;

    List<Order> getOrdersByCustomerId(long id, Timestamp begin, Timestamp end) throws SQLException;

    List<Order> getOpenOrdersOfCustomer(long id, Timestamp begin, Timestamp end) throws SQLException;

    List<Order> getCompletedOrdersOfCustomer(long id, Timestamp begin, Timestamp end) throws SQLException;

    List<Order> getTaxiOrders(long id) throws SQLException;

    List<Order> getOpenOrdersAll() throws SQLException;

    Order createNewInDataBase(Customer customer, String fromAddress, String toAddress, String orderedDateTime, String distance);

    boolean checkChangePossibility(Customer customer, Order order);

    Order findById(String orderId);

    boolean deleteOrderByIdByCustomer(Customer customer, String orderIdToDelete);

    boolean updateOrderByIdByCustomer(Customer customer, String orderIdToUpdate, String fromAddress,
                                              String toAddress, String orderedDateTime, String feedback, Double distance);

    double GetDistance(String addressFrom, String addressTo);

}

