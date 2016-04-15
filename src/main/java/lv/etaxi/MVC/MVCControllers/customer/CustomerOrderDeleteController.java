package lv.etaxi.MVC.MVCControllers.customer;

import lv.etaxi.MVC.MVCController;
import lv.etaxi.MVC.MVCModel;
import lv.etaxi.business.managers.OrderManagerImpl;
import lv.etaxi.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by D.Lazorkin on 02.04.2016.
 */
@Controller
public class CustomerOrderDeleteController implements MVCController {

    @Autowired
    OrderManagerImpl orderManagerImpl;

    public CustomerOrderDeleteController() {
        this.orderManagerImpl = new OrderManagerImpl();
    }

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

        String message = (orderManagerImpl.deleteOrderByIdByCustomer(currentCustomer, orderIdToDelete)) ?
                         "Your order (ID: " + orderIdToDelete + ") was deleted!" :
                         "Order information delete failed! Please try again!";

        return new MVCModel("/customer/CustomerEditDeleteOrders.jsp", null, message);
    }

}
