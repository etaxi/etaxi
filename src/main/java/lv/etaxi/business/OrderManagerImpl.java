package lv.etaxi.business;

import lv.etaxi.dao.OrderDAO;
import lv.etaxi.dao.hibernate.OrderHibernateDAOImpl;
import lv.etaxi.dao.jdbc.DBConnection;
import lv.etaxi.dao.jdbc.OrderDAOImpl;
import lv.etaxi.entity.Customer;
import lv.etaxi.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/** Проект etaxi
 * Created by D.Lazorkin on 25.03.2016.
 * Интерфейс для реализации функций над заказами
 */
@Service
public class OrderManagerImpl implements OrderManager{

    //@Qualifier("orderHibernateDAOImpl")
    @Autowired
    private OrderDAO orderDAO;

    public OrderManagerImpl() {
        this.orderDAO = (DBConnection.getDatabasePropertyFromFile("db.hibernate").equals("YES")) ?
                         new OrderHibernateDAOImpl() : new OrderDAOImpl();
    }


    @Transactional
    public void createNewOrder(Order order) throws SQLException {

        order.setOrderId(orderDAO.update(order));

    }

    @Transactional
    public void updateOrder(Order order) throws SQLException {

        orderDAO.update(order);

    }

    public Order findOrderById(long Id) throws SQLException {

        return orderDAO.getById(Id);

    }

    @Transactional
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

    @Transactional
    public Order createNewOrderInDataBase(Customer customer, String fromAddress, String toAddress, String orderedDateTime) {

        Order newOrder = new Order((long) 0, customer.getCustomerId(),
                new Timestamp(new Date().getTime()),
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

    @Transactional
    public boolean deleteOrderByIdByCustomer(Customer customer, String orderIdToDelete) {

        Order currentOrder = findOrderById(orderIdToDelete);
        if (currentOrder != null) {
            if (checkOrderChangePossibility(customer, currentOrder)) {
                try {
                    deleteOrder(currentOrder);
                    return true;
                } catch (SQLException e) {}
            }
        }
        return false;
    }

    @Transactional
    public  boolean updateOrderByIdByCustomer(Customer customer, String orderIdToUpdate, String fromAddress,
                                               String toAddress, String orderedDateTime, String feedback) {

        Order currentOrder = findOrderById(orderIdToUpdate);
        if (currentOrder != null) {

            if (checkOrderChangePossibility(customer, currentOrder)) {

                currentOrder.setFromAdress((fromAddress.isEmpty()) ? currentOrder.getFromAdress() : fromAddress);
                currentOrder.setToAdress((toAddress.isEmpty()) ? currentOrder.getToAdress() : toAddress);
                currentOrder.setOrderedDateTime((orderedDateTime.isEmpty()) ? currentOrder.getOrderedDateTime() : Timestamp.valueOf(orderedDateTime));
                currentOrder.setFeedback((feedback.isEmpty()) ? currentOrder.getFeedback() : feedback);

                try {
                    updateOrder(currentOrder);
                    return true;
                } catch (SQLException e) {}
            }
        }

        return false;
    }
}

