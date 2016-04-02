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
 * Created by D.Lazorkin on 31.03.2016.
 */
public class CustomerCreateNewOrderController implements MVCController {

    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) {

        return new MVCModel("/customer/CustomerNewOrder.jsp", "");

    }

    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) {

        String fromAddress = request.getParameter("fromAddress");
        String toAddress = request.getParameter("toAddress");
        String orderedDateTime = request.getParameter("orderedDateTime");

        String message = ((fromAddress == null || fromAddress.isEmpty()) ? "ride from address; " : "") +
                         ((toAddress == null || toAddress.isEmpty()) ? "ride to the address; " : "") +
                         ((orderedDateTime == null || orderedDateTime.isEmpty()) ? "date and time of taxi ride; " : "");

        Boolean orderCreationSuccessful = false;
        if (message.isEmpty()) {
            Customer currentCustomer = (Customer) request.getSession().getAttribute("customer");
            Order newOrder = new Order((long) 0, currentCustomer.getCustomerId(),
                    new Timestamp(new java.util.Date().getTime()),
                    Timestamp.valueOf(orderedDateTime),
                    Order.OrderStatus.WAITING,
                    fromAddress, toAddress, (long) 0, 0, 0, 0, "");
            try {
                new OrderManager().createNewOrder(newOrder);
                message = "New order was created (new order ID: " + newOrder.getOrderId() + ")";
                orderCreationSuccessful = true;
            } catch (SQLException e) {
                message = "New order creation failed! Please try again!";
            }

        } else {
            message = "Please, input information in fields: " + message;
        }

        if (orderCreationSuccessful) {
            return new MVCModel("/customer/CustomerMenu.jsp", message);
        } else {
            return new MVCModel("/customer/CustomerNewOrder.jsp", message);
        }

    }

}
