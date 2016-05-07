package lv.etaxi.business.managers;

import databaseTests.CustomerBuilder;
import databaseTests.OrderBuilder;
import lv.etaxi.business.CustomerManager;
import lv.etaxi.business.OrderManager;
import lv.etaxi.config.SpringAppConfig;
import lv.etaxi.entity.Customer;
import lv.etaxi.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by D.Lazorkin on 07.05.2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAppConfig.class)

public class OrderManagerImplTest {

    @Autowired
    private OrderManager orderManagerImpl;

    @Autowired
    CustomerManager customerManagerImpl;

    private Timestamp getCurrentDate() {
        return new Timestamp(new java.util.Date().getTime());
    }

    @Test
    public void createOrderTest() throws SQLException {

        OrderBuilder orderBuilder = databaseTests.OrderBuilder.aOrder()
                .withFromAdress("Brivibas 123")
                .withToAdress("Terbatas 34a")
                .withPrice(12.45)
                .withDistance(8.9)
                .withOrderedDate(getCurrentDate())
                .withDate(getCurrentDate())
                .withOrderStatus(Order.OrderStatus.WAITING);
        Order order = orderBuilder.build();

        orderManagerImpl.create(order);

        Order orderFind = orderManagerImpl.findById(order.getOrderId());

        assertNotNull(orderFind);
    }


    @Test
    public void updateOrderTest() throws SQLException {

        String newFromAdress = "Elizabetes 12";
        String newToAdress = "Strautu 19";
        Double newDistance = 22.00;
        Double newPrice = 6.78;

        OrderBuilder orderBuilder = databaseTests.OrderBuilder.aOrder()
                .withFromAdress("Brivibas 123")
                .withToAdress("Terbatas 34a")
                .withPrice(12.45)
                .withDistance(8.90)
                .withOrderedDate(getCurrentDate())
                .withDate(getCurrentDate())
                .withOrderStatus(Order.OrderStatus.WAITING);
        Order order = orderBuilder.build();

        orderManagerImpl.create(order);

        order.setFromAdress(newFromAdress);
        order.setToAdress(newToAdress);
        order.setPrice(newPrice);
        order.setDistance(newDistance);
        orderManagerImpl.update(order);

        Order orderFind = orderManagerImpl.findById(order.getOrderId());

        assertNotNull(orderFind);
        assertEquals(newFromAdress, orderFind.getFromAdress());
        assertEquals(newToAdress, orderFind.getToAdress());
        assertTrue(newPrice == orderFind.getPrice());
        assertTrue(newDistance == orderFind.getDistance());
     }

    @Test
    public void DeleteOrderTest() throws SQLException {

        OrderBuilder orderBuilder = databaseTests.OrderBuilder.aOrder()
                .withFromAdress("Brivibas 1")
                .withToAdress("Terbatas 7")
                .withPrice(12.45)
                .withDistance(8.9)
                .withOrderedDate(getCurrentDate())
                .withDate(getCurrentDate())
                .withOrderStatus(Order.OrderStatus.WAITING);
        Order order = orderBuilder.build();

        orderManagerImpl.create(order);
        Order orderFind = orderManagerImpl.findById(order.getOrderId());
        assertNotNull(orderFind);

        orderManagerImpl.delete(order);
        orderFind = orderManagerImpl.findById(order.getOrderId());
        assertNull(orderFind);
    }

    @Test
    public void getOpenOrdersAllTest() throws SQLException {

        Order order = databaseTests.OrderBuilder.aOrder().build();
        orderManagerImpl.create(order);

        List<Order> listOfOrders = orderManagerImpl.getOpenOrdersAll();
        assertTrue(listOfOrders.size() > 0);
    }

    @Test
    public void getPriceOfRideTest() throws SQLException {

        double priceOfRide = orderManagerImpl.getPriceOfRide(12);
        assertTrue(priceOfRide == 5.1);
    }

    @Test
    public void getDistanceTest() throws SQLException {

        String addressFrom = "Latvija, Rīga, Āzenes iela, 12";
        String addressTo = "Latvija, Rīga, Kronvalda bulvāris, 2";

        double distance = orderManagerImpl.GetDistance(addressFrom, addressTo);
        assertTrue(distance == 2.6);
    }

    @Test
    public void getOrdersByCustomerIdTest() throws SQLException {

        Timestamp currentDate = getCurrentDate();

        Customer customer = CustomerBuilder.aCustomer().build();
        customerManagerImpl.createNewInDataBase(customer);

        OrderBuilder orderBuilder = databaseTests.OrderBuilder.aOrder()
                .withFromAdress("Brivibas 1")
                .withToAdress("Terbatas 7")
                .withPrice(12.45)
                .withDistance(8.9)
                .withOrderedDate(currentDate)
                .withDate(currentDate)
                .withOrderStatus(Order.OrderStatus.WAITING);
        Order order = orderBuilder.build();
        order.setCustomerId(customer.getCustomerId());

        orderManagerImpl.create(order);

        List<Order> listOfOrders = orderManagerImpl.getOrdersByCustomerId(customer.getCustomerId(), currentDate, currentDate);
        assertTrue(listOfOrders.size() == 1);
    }


    @Test
    public void getOpenOrdersOfCustomerTest() throws SQLException {

        Timestamp currentDate = getCurrentDate();

        Customer customer = CustomerBuilder.aCustomer().build();
        customerManagerImpl.createNewInDataBase(customer);

        OrderBuilder orderBuilder = databaseTests.OrderBuilder.aOrder()
                .withOrderedDate(currentDate)
                .withDate(currentDate)
                .withOrderStatus(Order.OrderStatus.WAITING);
        Order order1 = orderBuilder.build();
        order1.setCustomerId(customer.getCustomerId());

        Order order2 = orderBuilder.build();
        order2.setOrderStatus(Order.OrderStatus.DELIVERED);
        order2.setCustomerId(customer.getCustomerId());

        orderManagerImpl.create(order1);
        orderManagerImpl.create(order2);

        List<Order> listOfOrders = orderManagerImpl.getOpenOrdersOfCustomer(customer.getCustomerId(), currentDate, currentDate);
        assertTrue(listOfOrders.size() == 1);
    }

    @Test
    public void getCompletedOrdersOfCustomerTest() throws SQLException {

        Timestamp currentDate = getCurrentDate();

        Customer customer = CustomerBuilder.aCustomer().build();
        customerManagerImpl.createNewInDataBase(customer);

        OrderBuilder orderBuilder = databaseTests.OrderBuilder.aOrder()
                .withFromAdress("Brivibas 1")
                .withToAdress("Terbatas 7")
                .withPrice(12.45)
                .withDistance(8.9)
                .withOrderedDate(currentDate)
                .withDate(currentDate)
                .withOrderStatus(Order.OrderStatus.DELIVERED);
        Order order = orderBuilder.build();
        order.setCustomerId(customer.getCustomerId());

        orderManagerImpl.create(order);

        List<Order> listOfOrders = orderManagerImpl.getCompletedOrdersOfCustomer(customer.getCustomerId(), currentDate, currentDate);
        assertTrue(listOfOrders.size() == 1);
    }


    @Test
    public void createNewInDataBaseTest() throws SQLException {

        Customer customer = CustomerBuilder.aCustomer().build();
        customerManagerImpl.createNewInDataBase(customer);

        Order order = orderManagerImpl.createNewInDataBase(
                customer,
                "Brivibas 1",
                "Brivibas 124",
                getCurrentDate().toString(),
                "12.34");

        assertNotNull(order);
    }

    @Test
    public void deleteOrderByIdByCustomerTest() throws SQLException {

        Customer customer = CustomerBuilder.aCustomer().build();
        customerManagerImpl.createNewInDataBase(customer);

        Order order = orderManagerImpl.createNewInDataBase(
                customer,
                "Brivibas 1",
                "Brivibas 124",
                getCurrentDate().toString(),
                "12.34");

        orderManagerImpl.deleteOrderByIdByCustomer(customer, order.getOrderId().toString());

        Order orderFind = orderManagerImpl.findById(order.getOrderId());
        assertNull(orderFind);

    }

    @Test
    public void updateOrderByIdByCustomer() throws SQLException {

        String newFromAdress = "Elizabetes 12";
        String newToAdress = "Strautu 19";
        Double newDistance = 22.00;
        Double newPrice = 6.78;
        String newFeedback = "Good ride!";

        Customer customer = CustomerBuilder.aCustomer().build();
        customerManagerImpl.createNewInDataBase(customer);

        Order order = orderManagerImpl.createNewInDataBase(
                customer,
                "Brivibas 1",
                "Brivibas 124",
                getCurrentDate().toString(),
                "12.34");

        orderManagerImpl.updateOrderByIdByCustomer(customer, order.getOrderId().toString(), newFromAdress,
                newToAdress, getCurrentDate().toString(), newFeedback, newDistance, newPrice);

        Order orderFind = orderManagerImpl.findById(order.getOrderId());

        assertNotNull(orderFind);
        assertEquals(newFromAdress, orderFind.getFromAdress());
        assertEquals(newToAdress, orderFind.getToAdress());
        assertEquals(newFeedback, orderFind.getFeedback());
        assertTrue(newPrice == orderFind.getPrice());
        assertTrue(newDistance == orderFind.getDistance());
    }

}