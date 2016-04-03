package MVC.MVCControllers.customer;

import MVC.MVCController;
import MVC.MVCModel;
import business.OrderManager;
import entity.Customer;
import entity.Order;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by D.Lazorkin on 02.04.2016.
 */
public class CustomerWriteFeedbackController implements MVCController {

    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) {

        Customer currentCustomer = (Customer) request.getSession().getAttribute("customer");
        String orderId = request.getParameter("orderId");

        OrderManager orderManager = new OrderManager();
        Order currentOrder = orderManager.findOrderById(orderId);

        Boolean changeIsPossible = orderManager.checkOrderChangePossibility(currentCustomer, currentOrder);
        return  changeIsPossible ? new MVCModel("/customer/CustomerWriteFeedbackToOrder.jsp", currentOrder, "") :  null;

    }

    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) {

        Customer currentCustomer = (Customer) request.getSession().getAttribute("customer");

        OrderManager orderManager = new OrderManager();
        Order currentOrder = orderManager.findOrderById(request.getParameter("orderId"));
        Boolean changeIsPossible = orderManager.checkOrderChangePossibility(currentCustomer, currentOrder);

        if (changeIsPossible) {

            Boolean updateSuccessful = orderManager.updateOrderInDataBase(
                    currentOrder,
                    currentOrder.getFromAdress(),
                    currentOrder.getToAdress(),
                    String.valueOf(currentOrder.getOrderedDateTime()),
                    request.getParameter("feedback"));

            String message = (updateSuccessful) ?
                    "Order ID: " + currentOrder.getOrderId() + " was changed!" :
                    "Order information update failed! Please try again!";

            if (updateSuccessful) {
                return new MVCModel("/customer/CustomerWriteFeedbacksToOrders.jsp", null, message);
            } else {
                return new MVCModel("/customer/CustomerWriteFeedbackToOrder.jsp", currentOrder, message);
            }

        } else {
            return new MVCModel("/customer/CustomerWriteFeedbacksToOrders.jsp", null, "");
        }

    }

}
