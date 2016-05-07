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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by D.Lazorkin on 07.05.2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAppConfig.class)

public class OrderManagerTestWithMockito {

    @Autowired
    private OrderManager orderManagerImpl;

    @Autowired
    private CustomerManager customerManagerImpl;

    private Timestamp getCurrentDate() {
        return new Timestamp(new java.util.Date().getTime());
    }

    private long createNewCustomerInDataBase() throws SQLException {
        Customer newCustomer = CustomerBuilder.aCustomer().build();
        customerManagerImpl.createNewInDataBase(newCustomer);
        return newCustomer.getCustomerId();
    }

    @Test
    public void getOrdersByCustomerIdTest() throws SQLException {

        Timestamp currentDate = getCurrentDate();

        Customer customer = mock(Customer.class);
        when(customer.getCustomerId()).thenReturn(createNewCustomerInDataBase());

        OrderBuilder orderBuilder = OrderBuilder.aOrder()
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

        Customer customer = mock(Customer.class);
        when(customer.getCustomerId()).thenReturn(createNewCustomerInDataBase());

        OrderBuilder orderBuilder = OrderBuilder.aOrder()
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

        Customer customer = mock(Customer.class);
        when(customer.getCustomerId()).thenReturn(createNewCustomerInDataBase());

        OrderBuilder orderBuilder = OrderBuilder.aOrder()
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

        Customer customer = mock(Customer.class);
        when(customer.getCustomerId()).thenReturn(createNewCustomerInDataBase());

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

        Customer customer = mock(Customer.class);
        when(customer.getCustomerId()).thenReturn(createNewCustomerInDataBase());

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

        Customer customer = mock(Customer.class);
        when(customer.getCustomerId()).thenReturn(createNewCustomerInDataBase());

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