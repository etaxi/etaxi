package business;

import dao.OrderDAO;
import dao.jdbc.DBConnection;
import dao.jdbc.OrderDAOImpl;
import entity.Order;

import java.sql.SQLException;
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

    public List<Order> getOrdersByCustomerId(long id) throws SQLException {

        return orderDAO.getCustomerOrders(id);
    }

    public List<Order> getOpenOrdersOfCustomer(long id) throws SQLException {

        return orderDAO.getOpenOrdersOfCustomer(id);
    }

    public List<Order> getCompletedOrdersOfCustomer(long id) throws SQLException {

        return  orderDAO.getCompletedOrdersOfCustomer(id);
    }
}

