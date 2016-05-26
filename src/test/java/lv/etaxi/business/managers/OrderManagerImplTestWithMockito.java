package lv.etaxi.business.managers;

import lv.etaxi.builders.CustomerBuilder;
import lv.etaxi.builders.OrderBuilder;
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
import org.springframework.test.context.web.WebAppConfiguration;

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
@WebAppConfiguration
public class OrderManagerImplTestWithMockito {

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
    public void getDistanceTest() throws SQLException {

        String addressFrom = "Latvija, Rīga, Āzenes iela, 12";
        String addressTo = "Latvija, Rīga, Kronvalda bulvāris, 2";

        OrderManagerImpl orderManagerImpl = mock(OrderManagerImpl.class);
        when(orderManagerImpl.GetDistance(addressFrom, addressTo)).thenReturn(5.6);

        double distance = orderManagerImpl.GetDistance(addressFrom, addressTo);
        assertTrue(distance == 5.6);
    }

    @Test
    public void getPriceOfRideTest() throws SQLException {

        OrderManagerImpl orderManagerImpl = mock(OrderManagerImpl.class);
        when(orderManagerImpl.getPriceOfRide(12)).thenReturn(6.10);

        double priceOfRide = orderManagerImpl.getPriceOfRide(12);
        assertTrue(priceOfRide == 6.10);
    }

}