package lv.etaxi.MVC.MVCControllers.customer;

import lv.etaxi.MVC.MVCController;
import lv.etaxi.MVC.MVCModel;
import lv.etaxi.business.managers.OrderManagerImpl;
import lv.etaxi.entity.Customer;
import lv.etaxi.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by D.Lazorkin on 31.03.2016.
 */

@Controller
public class CustomerCreateNewOrderController implements MVCController {

    @Autowired
    OrderManagerImpl orderManagerImpl;

    public CustomerCreateNewOrderController() {
        this.orderManagerImpl = new OrderManagerImpl();
    }

    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) {

        Customer currentCustomer = (Customer) request.getSession().getAttribute("customer");
        if (currentCustomer == null) {
            return new MVCModel("/customer/CustomerMenu.jsp", null, "");
        }

        return new MVCModel("/customer/CustomerNewOrder.jsp", "", "");
    }

    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) {

        Customer currentCustomer = (Customer) request.getSession().getAttribute("customer");
        if (currentCustomer == null) {
            return new MVCModel("/customer/CustomerMenu.jsp", null, "");
        }

        Order newOrder = orderManagerImpl.createNewOrderInDataBase(
                currentCustomer,
                request.getParameter("fromAddress"),
                request.getParameter("toAddress"),
                request.getParameter("orderedDateTime"),
                request.getParameter("distance"));

        String message = (newOrder != null) ?
                          "New order was created (new order ID: " + newOrder.getOrderId() + ")" :
                          "New order creation failed! Please try again!";

        return new MVCModel("/customer/CustomerNewOrder.jsp", newOrder, message);
    }

}
