package MVC.MVCControllers.customer;

import MVC.MVCController;
import MVC.MVCModel;
import business.OrderManager;
import entity.Customer;
import entity.Order;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Created by D.Lazorkin on 02.04.2016.
 */
public class CustomerOrderEditController implements MVCController {

    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) {

        Customer currentCustomer = (Customer) request.getSession().getAttribute("customer");
        String orderIdToChange = request.getParameter("orderId");

        Boolean changeIsPossible = false;
        Order orderToChange = null;
        try {
            orderToChange = new OrderManager().findOrderById(Long.valueOf(orderIdToChange));
            if (orderToChange.getCustomerId() == currentCustomer.getCustomerId()) {
                changeIsPossible = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  (changeIsPossible) ? new MVCModel("/customer/CustomerEditOrder.jsp", orderToChange, "") :  null;

    }

    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) {

        Customer currentCustomer = (Customer) request.getSession().getAttribute("customer");

        String orderId = request.getParameter("orderId");
        String fromAddress = request.getParameter("fromAddress");
        String toAddress = request.getParameter("toAddress");
        String orderedDateTime = request.getParameter("orderedDateTime");

        String message = ((fromAddress == null || fromAddress.isEmpty()) ? "from address; " : "") +
                         ((toAddress == null || toAddress.isEmpty()) ? "to address; " : "") +
                         ((orderedDateTime == null || orderedDateTime.isEmpty()) ? "date and time of ride; " : "");

        Boolean updateSuccessful = false;
        Order updatedOrder = null;
        if (message.isEmpty()) {

            updatedOrder = new Order(Long.parseLong(orderId), currentCustomer.getCustomerId(),
                    new Timestamp(new java.util.Date().getTime()),
                    Timestamp.valueOf(orderedDateTime),
                    Order.OrderStatus.WAITING,
                    fromAddress, toAddress, (long) 0, 0, 0, 0, "");
            try {
                new OrderManager().updateOrder(updatedOrder);
                updateSuccessful = true;
                message = "Order ID: " + updatedOrder.getOrderId() + " was changed!";
            } catch (SQLException e) {
                message = "Order information update failed! Please try again!";
            }

        } else {
            message = "Please, input information in fields: " + message;
        }

        if (updateSuccessful) {
            return new MVCModel("/customer/CustomerEditDeleteOrders.jsp", null, message);
        } else {
            return new MVCModel("/customer/CustomerEditOrder.jsp", updatedOrder, message);
        }
    }

}
