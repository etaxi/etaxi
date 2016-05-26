package services;

import lv.etaxi.commands.orders.*;
import lv.etaxi.config.SpringAppConfig;
import lv.etaxi.dto.OrderDTO;
import lv.etaxi.entity.Order;
import lv.etaxi.services.CommandExecutor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Timestamp;

import static org.junit.Assert.*;

/**
 * Created by D.Lazorkin on 25.05.2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAppConfig.class)
@WebAppConfiguration
public class CommandOrderTest {

    @Autowired
    CommandExecutor commandExecutorImpl;

    private Timestamp getCurrentDate() {
        return new Timestamp(new java.util.Date().getTime());
    }

    @Test
    public void createNewOrder() throws Exception {

        String fromAddress  =  "Brivibas 123";
        String toAddress    =  "Terbatas 14";

        CreateOrderCommand createOrderCommand = new CreateOrderCommand(
                getCurrentDate(),
                getCurrentDate(),
                fromAddress,
                toAddress,
                Order.OrderStatus.WAITING);

        OrderOperationResult orderOperationResult = commandExecutorImpl.execute(createOrderCommand);
        OrderDTO newOrder = orderOperationResult.getOrder();

        assertNotNull(newOrder);
        assertFalse(newOrder.getOrderId() == 0);
        assertEquals(fromAddress, newOrder.getFromAdress());
        assertEquals(toAddress, newOrder.getToAdress());
    }


    @Test
    public void getOrderById() throws Exception {

        String fromAddress  =  "Brivibas 323";
        String toAddress    =  "Terbatas 1";

        CreateOrderCommand createOrderCommand = new CreateOrderCommand(
                getCurrentDate(),
                getCurrentDate(),
                fromAddress,
                toAddress,
                Order.OrderStatus.WAITING);

        OrderOperationResult orderOperationResult = commandExecutorImpl.execute(createOrderCommand);
        OrderDTO newOrder = orderOperationResult.getOrder();


        GetOrderCommand getOrderCommand = new GetOrderCommand(newOrder.getOrderId());
        orderOperationResult = commandExecutorImpl.execute(getOrderCommand);
        OrderDTO foundOrder = orderOperationResult.getOrder();

        assertNotNull(foundOrder);
        assertFalse(foundOrder.getOrderId() == 0);
        assertEquals(fromAddress, foundOrder.getFromAdress());
        assertEquals(toAddress, foundOrder.getToAdress());
    }


    @Test
    public void updateOrder() throws Exception {

        String fromAddress  =  "Brivibas 320";
        String toAddress    =  "Elizabetes 61";

        CreateOrderCommand createOrderCommand = new CreateOrderCommand(
                getCurrentDate(),
                getCurrentDate(),
                fromAddress,
                toAddress,
                Order.OrderStatus.WAITING);

        OrderOperationResult orderOperationResult = commandExecutorImpl.execute(createOrderCommand);
        OrderDTO newOrder = orderOperationResult.getOrder();


        String fromAddressNew  =  "Dzerbenes 14";
        String toAddressNew    =  "Stirnu 21";

        UpdateOrderCommand updateOrderCommand = new UpdateOrderCommand(
                newOrder.getOrderId(),
                newOrder.getCustomerId(),
                newOrder.getTaxiId(),
                getCurrentDate(),
                newOrder.getOrderedDateTime(),
                fromAddressNew,
                toAddressNew,
                newOrder.getOrderStatus());

        orderOperationResult = commandExecutorImpl.execute(updateOrderCommand);
        OrderDTO updatedOrder = orderOperationResult.getOrder();

        assertNotNull(updatedOrder);
        assertFalse(updatedOrder.getOrderId() == 0);
        assertEquals(fromAddressNew, updatedOrder.getFromAdress());
        assertEquals(toAddressNew, updatedOrder.getToAdress());
    }


    @Test
    public void deleteOrder() throws Exception {

        String fromAddress  =  "Elizabetes 1";
        String toAddress    =  "Strautu 17";

        CreateOrderCommand createOrderCommand = new CreateOrderCommand(
                getCurrentDate(),
                getCurrentDate(),
                fromAddress,
                toAddress,
                Order.OrderStatus.WAITING);

        OrderOperationResult orderOperationResult = commandExecutorImpl.execute(createOrderCommand);
        OrderDTO newOrder = orderOperationResult.getOrder();

        DeleteOrderCommand deleteOrderCommand = new DeleteOrderCommand(newOrder.getOrderId());
        orderOperationResult = commandExecutorImpl.execute(deleteOrderCommand);

        assertNull(orderOperationResult.getOrder());
    }

}