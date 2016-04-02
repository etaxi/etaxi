package MVC.MVCControllers.customer;

import MVC.MVCController;
import MVC.MVCModel;
import business.OrderManager;
import entity.Customer;
import entity.Order;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Created by D.Lazorkin on 02.04.2016.
 */
public class CustomerWriteFeedbackController implements MVCController {

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

        return  (changeIsPossible) ? new MVCModel("/customer/CustomerWriteFeedbackToOrder.jsp", orderToChange, "") :  null;

    }

    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) {

        String orderId = request.getParameter("orderId");
        String feedback = request.getParameter("feedback");

        String message = ((feedback == null || feedback.isEmpty()) ? "feedback;" : "");

        Boolean updateSuccessful = false;
        Order updatedOrder = null;

        if (message.isEmpty()) {
            try {
                OrderManager orderManager = new OrderManager();
                updatedOrder = orderManager.findOrderById(Long.parseLong(orderId));
                updatedOrder.setFeedback(feedback);
                orderManager.updateOrder(updatedOrder);
                updateSuccessful = true;
                message = "Order ID: " + updatedOrder.getOrderId() + " was updated!";
            } catch (SQLException e) {
                message = "Order information update failed! Please try again!";
            }

        } else {
            message = "Please, input information in fields: " + message;
        }

        if (updateSuccessful) {
            return new MVCModel("/customer/CustomerWriteFeedbacksToOrders.jsp", null, message);
        } else {
            return new MVCModel("/customer/CustomerWriteFeedbackToOrder.jsp", updatedOrder, message);
        }
    }

}
