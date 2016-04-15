package lv.etaxi.MVC.MVCControllers.customer;

import lv.etaxi.MVC.MVCController;
import lv.etaxi.MVC.MVCModel;
import lv.etaxi.business.managers.OrderManagerImpl;
import lv.etaxi.entity.Customer;
import lv.etaxi.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by D.Lazorkin on 02.04.2016.
 */

@Controller
public class CustomerHistoryOfOrdersController implements MVCController {

    @Autowired
    OrderManagerImpl orderManagerImpl;

    public CustomerHistoryOfOrdersController() {
        this.orderManagerImpl = new OrderManagerImpl();
    }

    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) throws SQLException {
        Customer currentCustomer = (Customer) request.getSession().getAttribute("customer");
        if (currentCustomer == null) {
            return new MVCModel("/customer/CustomerMenu.jsp", null, "");
        }

        return new MVCModel("/customer/CustomerListOrders.jsp", null, "");
    }

    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) throws SQLException {

        Customer currentCustomer = (Customer) request.getSession().getAttribute("customer");
        if (currentCustomer == null) {
            return new MVCModel("/customer/CustomerMenu.jsp", null, "");
        }

        Timestamp orderedDateTimeBegin = Timestamp.valueOf(request.getParameter("orderedDateTimeBegin"));
        Timestamp orderedDateTimeEnd = Timestamp.valueOf(request.getParameter("orderedDateTimeEnd"));

        List<Order> listOfOrders = orderManagerImpl.getOrdersByCustomerId(
                currentCustomer.getCustomerId(), orderedDateTimeBegin, orderedDateTimeEnd);

        return new MVCModel("/customer/CustomerListOrders.jsp", listOfOrders, "");

    }

}
