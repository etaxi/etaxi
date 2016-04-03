package MVC.MVCControllers.customer;

import MVC.MVCController;
import MVC.MVCModel;
import business.OrderManager;
import entity.Customer;
import entity.Order;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by D.Lazorkin on 31.03.2016.
 */
public class CustomerCreateNewOrderController implements MVCController {

    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) {

        return new MVCModel("/customer/CustomerNewOrder.jsp", "", "");

    }

    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) {

        Customer currentCustomer = (Customer) request.getSession().getAttribute("customer");

        Order newOrder = new OrderManager().createNewOrderInDataBase(
                currentCustomer,
                request.getParameter("fromAddress"),
                request.getParameter("toAddress"),
                request.getParameter("orderedDateTime"));

        String message = (newOrder != null) ?
                          "New order was created (new order ID: " + newOrder.getOrderId() + ")" :
                          "New order creation failed! Please try again!";

        return new MVCModel("/customer/CustomerNewOrder.jsp", newOrder, message);
    }

}
