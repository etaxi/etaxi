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

    public Order findOrderById(long Id) throws SQLException;

    public void deleteOrder(Order order) throws SQLException;

    public List<Order> getOrdersByCustomerId(long id, Timestamp begin, Timestamp end) throws SQLException;

    public List<Order> getOpenOrdersOfCustomer(long id, Timestamp begin, Timestamp end) throws SQLException;

    public List<Order> getCompletedOrdersOfCustomer(long id, Timestamp begin, Timestamp end) throws SQLException;

    public List<Order> getTaxiOrders(long id) throws SQLException;

    public List<Order> getOpenOrdersAll() throws SQLException;

    public Order createNewOrderInDataBase(Customer customer, String fromAddress, String toAddress, String orderedDateTime);

    public boolean checkOrderChangePossibility(Customer customer, Order order);

    public Order findOrderById(String orderId);

    public boolean deleteOrderByIdByCustomer(Customer customer, String orderIdToDelete);

    public  boolean updateOrderByIdByCustomer(Customer customer, String orderIdToUpdate, String fromAddress,
                                              String toAddress, String orderedDateTime, String feedback);
}

