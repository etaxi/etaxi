package MVC.MVCControllers.customer;

import MVC.MVCController;
import MVC.MVCModel;
import business.OrderManagerImpl;
import entity.Customer;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by D.Lazorkin on 02.04.2016.
 */
public class CustomerOrderDeleteController implements MVCController {

    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) {

        return new MVCModel("/customer/CustomerMenu.jsp", null, "");

    }

    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) {

        Customer currentCustomer = (Customer) request.getSession().getAttribute("customer");
        if (currentCustomer == null) {
            return new MVCModel("/customer/CustomerMenu.jsp", null, "");
        }

        String orderIdToDelete = request.getParameter("orderId");

        String message = (new OrderManagerImpl().deleteOrderByIdByCustomer(currentCustomer, orderIdToDelete)) ?
                         "Your order (ID: " + orderIdToDelete + ") was deleted!" :
                         "Order information delete failed! Please try again!";

        return new MVCModel("/customer/CustomerEditDeleteOrders.jsp", null, message);
    }

}
