package databaseTests;

import lv.etaxi.dao.OrderDAO;
import lv.etaxi.dao.jdbc.OrderDAOImpl;
import lv.etaxi.entity.Order;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.assertTrue;

/** Проект etaxi
 * JUnit тесты для проекта etaxi (design patterns "Object Mother" and "Test Data Builder")
 * */

public class TestsForOrderJDBC {

    private Timestamp getCurrentDate() {

        return new Timestamp(new java.util.Date().getTime());
    }

    public OrderDAO aOrderDAO() {

        return new OrderDAOImpl();

    }

    @Test
    public void testNewOrderRecord() throws SQLException {

        OrderBuilder orderBuilder = OrderBuilder.aOrder()
                .withFromAdress("Brivibas 123")
                .withToAdress("Terbatas 34a")
                .withPrice(12.45)
                .withDistance(8.9)
                .withOrderedDate(getCurrentDate())
                .withDate(getCurrentDate())
                .withOrderStatus(Order.OrderStatus.DELIVERED);

        Order order = orderBuilder.build();
        long newOrderID = aOrderDAO().create(order);
    }


    @Test
    public void testNewOrdersRecords() throws SQLException {

        OrderDAO orderDAO = aOrderDAO();

        OrderBuilder orderBuilder = OrderBuilder.aOrder()
                .withFromAdress("Dzerbenes 14")
                .withToAdress("Stirnu 78k-3")
                .withPrice(7.6)
                .withDistance(9.9)
                .withDate(getCurrentDate())
                .withOrderedDate(getCurrentDate())
                .withOrderStatus(Order.OrderStatus.WAITING);

        Order order1 = orderBuilder.build();
        long newOrderID1 = orderDAO.create(order1);

        Order order2 = orderBuilder.aOrder().build();  //USE "DEFAULT ORDER"
        long newOrderID2 = orderDAO.create(order2);

    }

    @Test
    public void testUpdateOrderRecord() throws SQLException {

        OrderDAO orderDAO = aOrderDAO();

        OrderBuilder orderBuilder = OrderBuilder.aOrder()
                .withFromAdress("Dzerbenes 89a")
                .withToAdress("Stirnu 18")
                .withPrice(5.0)
                .withDistance(12.0)
                .withDate(getCurrentDate())
                .withOrderedDate(getCurrentDate())
                .withFeedback("Good driver!")
                .withOrderStatus(Order.OrderStatus.WAITING);

        Order order = orderBuilder.build();

        order.setOrderId(orderDAO.create(order));
        order.setOrderStatus(Order.OrderStatus.DELIVERED);
        orderDAO.update(order);
    }

    @Test
    public void testGetOrderByID() throws SQLException {

        OrderDAO orderDAO = aOrderDAO();

        OrderBuilder orderBuilder = OrderBuilder.aOrder()
                .withFromAdress("Dzerbenes 89a")
                .withToAdress("Stirnu 18")
                .withPrice(5.0)
                .withDistance(12.0)
                .withDate(getCurrentDate())
                .withOrderedDate(getCurrentDate())
                .withOrderStatus(Order.OrderStatus.WAITING);

        Order order = orderBuilder.build();
        order.setOrderId(orderDAO.create(order));

        Order orderGetById = orderDAO.getById(order.getOrderId());
        assertTrue(order.getOrderId().equals(orderGetById.getOrderId()));
    }

    @Test
    public void testDeleteOrderByID() throws SQLException {

        OrderDAO orderDAO = aOrderDAO();

        Order order = OrderBuilder.aOrder().build();
        order.setOrderId(orderDAO.create(order));

        int countOfOrdersBeforeDeleteOperation = orderDAO.getAll().size();

        orderDAO.delete(order);

        int countOfOrdersAfterDeleteOperation = orderDAO.getAll().size();

        assertTrue(countOfOrdersBeforeDeleteOperation-1 == countOfOrdersAfterDeleteOperation);

    }

    @Test
    public void testGetListOfAllOrders() throws SQLException {

        OrderDAO orderDAO = aOrderDAO();

        Order order = OrderBuilder.aOrder().build();
        orderDAO.update(order);

        List<Order> listOfOrders = orderDAO.getAll();
        assertTrue(listOfOrders.size()>0);

    }

}
