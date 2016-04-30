package lv.etaxi.MVC.MVCControllers.customer;

import lv.etaxi.MVC.MVCController;
import lv.etaxi.MVC.MVCModel;
import lv.etaxi.business.OrderManager;
import lv.etaxi.entity.Customer;
import lv.etaxi.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

/**
 * Created by D.Lazorkin on 02.04.2016.
 */

@Controller
public class CustomerOrderGetDistanceController implements MVCController {

    @Autowired
    OrderManager orderManagerImpl;


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

        Order currentOrder = null;
        try {
            currentOrder = orderManagerImpl.findById(request.getParameter("orderId"));
        } catch (Exception e) {
            currentOrder = new Order();
            currentOrder.setFromAdress(request.getParameter("fromAddress"));
            currentOrder.setToAdress(request.getParameter("toAddress"));
            currentOrder.setOrderedDateTime(Timestamp.valueOf(request.getParameter("orderedDateTime")));
        }

        currentOrder.setDistance(orderManagerImpl.GetDistance(request.getParameter("fromAddress"), request.getParameter("toAddress")));
        currentOrder.setPrice(orderManagerImpl.getPriceOfRide(currentOrder.getDistance()));

        return new MVCModel(request.getParameter("returnPage"), currentOrder, "");
    }
}
