package business;

import dao.OrderDAO;
import dao.jdbc.DBConnection;
import dao.jdbc.OrderDAOImpl;
import entity.Customer;
import entity.Order;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/** Проект etaxi
 * Created by D.Lazorkin on 25.03.2016.
 * Класс для реализации функций над заказами
 */

public class OrderManager {

    private OrderDAO orderDAO;

    public OrderManager() {

        DBConnection dbService = new DBConnection();
        this.orderDAO = new OrderDAOImpl(dbService.getConnection(), dbService.getDatabaseName());
    }

    public void createNewOrder(Order order) throws SQLException {

        order.setOrderId(orderDAO.update(order));

    }

    public void updateOrder(Order order) throws SQLException {

        orderDAO.update(order);

    }

    public Order findOrderById(long Id) throws SQLException {

        return orderDAO.getById(Id);

    }

    public void deleteOrder(Order order) throws SQLException {

        orderDAO.delete(order);

    }

    public List<Order> getOrdersByCustomerId(long id, Timestamp begin, Timestamp end) throws SQLException {

        return orderDAO.getCustomerOrders(id, begin, end);
    }

    public List<Order> getOpenOrdersOfCustomer(long id, Timestamp begin, Timestamp end) throws SQLException {

        return orderDAO.getOpenOrdersOfCustomer(id, begin, end);
    }

    public List<Order> getCompletedOrdersOfCustomer(long id, Timestamp begin, Timestamp end) throws SQLException {

        return  orderDAO.getCompletedOrdersOfCustomer(id, begin, end);
    }

    public List<Order> getTaxiOrders(long id) throws SQLException {

        return  orderDAO.getTaxiOrders(id);
    }

    public List<Order> getOpenOrdersAll() throws SQLException {

        return  orderDAO.getOpenOrdersAll();
    }

    public Order createNewOrderInDataBase(Customer customer, String fromAddress, String toAddress, String orderedDateTime) {

        Order newOrder = new Order((long) 0, customer.getCustomerId(),
                new Timestamp(new java.util.Date().getTime()),
                Timestamp.valueOf(orderedDateTime),
                Order.OrderStatus.WAITING,
                fromAddress, toAddress, (long) 0, 0, 0, 0, "");
        try {
            createNewOrder(newOrder);
            return newOrder;
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean updateOrderInDataBase(Order order, String fromAddress, String toAddress,
                                         String orderedDateTime, String feedback) {

        order.setFromAdress(fromAddress);
        order.setToAdress(toAddress);
        order.setOrderedDateTime(Timestamp.valueOf(orderedDateTime));
        order.setFeedback(feedback);

        try {
            updateOrder(order);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }


    public boolean checkOrderChangePossibility(Customer customer, Order order) {
        return  (order.getCustomerId() == customer.getCustomerId());
    }

    public Order findOrderById(String orderId) {
        try {
            return findOrderById(Long.valueOf(orderId));
        } catch (SQLException e) {
            return null;
        }
    }


}

