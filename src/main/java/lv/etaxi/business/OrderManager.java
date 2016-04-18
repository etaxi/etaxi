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

    void createNewOrder(Order order) throws SQLException;

    void updateOrder(Order order) throws SQLException;

    Order findOrderById(long Id) throws SQLException;

    void deleteOrder(Order order) throws SQLException;

    List<Order> getOrdersByCustomerId(long id, Timestamp begin, Timestamp end) throws SQLException;

    List<Order> getOpenOrdersOfCustomer(long id, Timestamp begin, Timestamp end) throws SQLException;

    List<Order> getCompletedOrdersOfCustomer(long id, Timestamp begin, Timestamp end) throws SQLException;

    List<Order> getTaxiOrders(long id) throws SQLException;

    List<Order> getOpenOrdersAll() throws SQLException;

    Order createNewOrderInDataBase(Customer customer, String fromAddress, String toAddress, String orderedDateTime, String distance);

    boolean checkOrderChangePossibility(Customer customer, Order order);

    Order findOrderById(String orderId);

    boolean deleteOrderByIdByCustomer(Customer customer, String orderIdToDelete);

    boolean updateOrderByIdByCustomer(Customer customer, String orderIdToUpdate, String fromAddress,
                                              String toAddress, String orderedDateTime, String feedback, Double distance);

    double GetDistance(String addressFrom, String addressTo);

}

