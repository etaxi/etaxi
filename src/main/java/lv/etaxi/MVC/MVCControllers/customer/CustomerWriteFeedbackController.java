package lv.etaxi.MVC.MVCControllers.customer;

import lv.etaxi.MVC.MVCController;
import lv.etaxi.MVC.MVCModel;
import lv.etaxi.business.OrderManager;
import lv.etaxi.business.OrderManagerImpl;
import lv.etaxi.entity.Customer;
import lv.etaxi.entity.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by D.Lazorkin on 02.04.2016.
 */

@Component
public class CustomerWriteFeedbackController implements MVCController {

    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) {

        Customer currentCustomer = (Customer) request.getSession().getAttribute("customer");
        if (currentCustomer == null) {
            return new MVCModel("/customer/CustomerMenu.jsp", null, "");
        }

        String orderId = request.getParameter("orderId");

        OrderManager orderManager = new OrderManagerImpl();
        Order currentOrder = orderManager.findOrderById(orderId);

        return  (orderManager.checkOrderChangePossibility(currentCustomer, currentOrder)) ?
                new MVCModel("/customer/CustomerWriteFeedbackToOrder.jsp", currentOrder, "") :
                new MVCModel("/customer/CustomerMenu.jsp", null, "");

    }

    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) {

        Customer currentCustomer = (Customer) request.getSession().getAttribute("customer");
        if (currentCustomer == null) {
            return new MVCModel("/customer/CustomerMenu.jsp", null, "");
        }

        OrderManager orderManager = new OrderManagerImpl();
        Boolean updateSuccessful = orderManager.updateOrderByIdByCustomer(
                currentCustomer,
                request.getParameter("orderId"),
                "", "", "",
                request.getParameter("feedback"));

        String message = (updateSuccessful) ?
                "Order ID: " + request.getParameter("orderId") + " was changed!" :
                "Order information update failed! Please try again!";

        if (updateSuccessful) {
            return new MVCModel("/customer/CustomerWriteFeedbacksToOrders.jsp", null, message);
        } else {
            Order currentOrder = orderManager.findOrderById(request.getParameter("orderId"));
            return new MVCModel("/customer/CustomerWriteFeedbackToOrder.jsp", currentOrder, message);
        }
    }

}
