package MVC.MVCControllers.customer;

import MVC.MVCController;
import MVC.MVCModel;
import business.OrderManager;
import entity.Customer;
import entity.Order;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by D.Lazorkin on 02.04.2016.
 */
public class CustomerWriteFeedbacksController implements MVCController {

    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) throws SQLException {
           return new MVCModel("/customer/CustomerWriteFeedbacksToOrders.jsp", null, "");
    }

    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) throws SQLException {

        Customer CurrentCustomer = (Customer) request.getSession().getAttribute("customer");
        if (CurrentCustomer != null) {

            Timestamp orderedDateTimeBegin = Timestamp.valueOf(request.getParameter("orderedDateTimeBegin"));
            Timestamp orderedDateTimeEnd   = Timestamp.valueOf(request.getParameter("orderedDateTimeEnd"));

            List<Order> listOfOrders = new OrderManager().getCompletedOrdersOfCustomer(
                                           CurrentCustomer.getCustomerId(), orderedDateTimeBegin, orderedDateTimeEnd);
            return new MVCModel("/customer/CustomerWriteFeedbacksToOrders.jsp", listOfOrders, "");
        }
        else{
            return new MVCModel("/customer/CustomerMenu.jsp", null, "");
        }

    }

}
