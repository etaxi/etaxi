package lv.etaxi.MVC.MVCControllers.customer;

import lv.etaxi.MVC.MVCController;
import lv.etaxi.MVC.MVCModel;
import lv.etaxi.business.OrderManager;
import lv.etaxi.entity.Customer;
import lv.etaxi.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by D.Lazorkin on 02.04.2016.
 */

@Controller
public class CustomerWriteFeedbackController implements MVCController {

    @Autowired
    OrderManager orderManagerImpl;

    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) {

        Customer currentCustomer = (Customer) request.getSession().getAttribute("customer");
        if (currentCustomer == null) {
            return new MVCModel("/customer/CustomerMenu.jsp", null, "");
        }

        String orderId = request.getParameter("orderId");

        Order currentOrder = orderManagerImpl.findById(orderId);

        return  (orderManagerImpl.checkChangePossibility(currentCustomer, currentOrder)) ?
                new MVCModel("/customer/CustomerWriteFeedbackToOrder.jsp", currentOrder, "") :
                new MVCModel("/customer/CustomerMenu.jsp", null, "");

    }

    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) {

        Customer currentCustomer = (Customer) request.getSession().getAttribute("customer");
        if (currentCustomer == null) {
            return new MVCModel("/customer/CustomerMenu.jsp", null, "");
        }

        Boolean updateSuccessful = orderManagerImpl.updateOrderByIdByCustomer(
                currentCustomer,
                request.getParameter("orderId"),
                "", "", "",
                request.getParameter("feedback"),
                0.00, 0.00);

        String message = (updateSuccessful) ?
                "Order ID: " + request.getParameter("orderId") + " was changed!" :
                "Order information update failed! Please try again!";

        if (updateSuccessful) {
            return new MVCModel("/customer/CustomerWriteFeedbacksToOrders.jsp", null, message);
        } else {
            Order currentOrder = orderManagerImpl.findById(request.getParameter("orderId"));
            return new MVCModel("/customer/CustomerWriteFeedbackToOrder.jsp", currentOrder, message);
        }
    }

}
