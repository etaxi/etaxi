package MVC.MVCControllers.customer;

import MVC.MVCController;
import MVC.MVCModel;
import business.OrderManagerImpl;
import entity.Customer;
import entity.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by D.Lazorkin on 02.04.2016.
 */
@Component
public class CustomerWriteFeedbacksController implements MVCController {

    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) throws SQLException {

        Customer currentCustomer = (Customer) request.getSession().getAttribute("customer");
        if (currentCustomer == null) {
            return new MVCModel("/customer/CustomerMenu.jsp", null, "");
        }

        return new MVCModel("/customer/CustomerWriteFeedbacksToOrders.jsp", null, "");
    }

    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) throws SQLException {

        Customer currentCustomer = (Customer) request.getSession().getAttribute("customer");
        if (currentCustomer == null) {
            return new MVCModel("/customer/CustomerMenu.jsp", null, "");
        }

        Timestamp orderedDateTimeBegin = Timestamp.valueOf(request.getParameter("orderedDateTimeBegin"));
        Timestamp orderedDateTimeEnd = Timestamp.valueOf(request.getParameter("orderedDateTimeEnd"));

        List<Order> listOfOrders = new OrderManagerImpl().getCompletedOrdersOfCustomer(
                currentCustomer.getCustomerId(), orderedDateTimeBegin, orderedDateTimeEnd);
        return new MVCModel("/customer/CustomerWriteFeedbacksToOrders.jsp", listOfOrders, "");
    }

}
