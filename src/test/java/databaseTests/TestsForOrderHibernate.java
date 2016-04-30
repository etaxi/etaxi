package databaseTests;

import lv.etaxi.config.SpringAppConfig;
import lv.etaxi.dao.CustomerDAO;
import lv.etaxi.dao.OrderDAO;
import lv.etaxi.dao.TaxiDAO;
import lv.etaxi.dao.jdbc.CustomerDAOImpl;
import lv.etaxi.dao.jdbc.TaxiDAOImpl;
import lv.etaxi.entity.Customer;
import lv.etaxi.entity.Order;
import lv.etaxi.entity.Taxi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.assertTrue;

/** Проект etaxi
 * JUnit тесты для проекта etaxi (design patterns "Object Mother" and "Test Data Builder")
 * */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAppConfig.class)

public class TestsForOrderHibernate {

    @Autowired
    private OrderDAO orderDAO;

    private Timestamp getCurrentDate() {

        return new Timestamp(new java.util.Date().getTime());
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
        long newOrderID = orderDAO.create(order);
    }


    @Test
    public void testNewOrdersRecords() throws SQLException {

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

        Order order = OrderBuilder.aOrder().build();
        order.setOrderId(orderDAO.create(order));

        int countOfOrdersBeforeDeleteOperation = orderDAO.getAll().size();

        orderDAO.delete(order);

        int countOfOrdersAfterDeleteOperation = orderDAO.getAll().size();

        assertTrue(countOfOrdersBeforeDeleteOperation-1 == countOfOrdersAfterDeleteOperation);

    }

    @Test
    public void testGetListOfAllOrders() throws SQLException {

        Order order = OrderBuilder.aOrder().build();
        orderDAO.create(order);

        List<Order> listOfOrders = orderDAO.getAll();
        assertTrue(listOfOrders.size()>0);

    }

    @Test
    public void testNewOrderRecordWithNewCustomerAndTaxi() throws SQLException {

        Customer customer = CustomerBuilder.aCustomer().build();
        CustomerDAO customerDAO = new CustomerDAOImpl();
        customer.setCustomerId(customerDAO.create(customer));

        Taxi taxi = TaxiBuilder.aTaxi().build();
        TaxiDAO taxiDAO = new TaxiDAOImpl();
        taxi.setTaxiId(taxiDAO.create(taxi));

        OrderBuilder orderBuilder = OrderBuilder.aOrder()
                .withCustomerID(customer.getCustomerId())
                .withTaxiID(taxi.getTaxiId())
                .withFromAdress("Anninmuizas 6")
                .withToAdress("Kr.Barona 89")
                .withPrice(23.05)
                .withDistance(14.5)
                .withDate(getCurrentDate())
                .withOrderedDate(getCurrentDate())
                .withOrderStatus(Order.OrderStatus.DELIVERED);

        Order order = orderBuilder.build();
        orderDAO.create(order);
    }

}
