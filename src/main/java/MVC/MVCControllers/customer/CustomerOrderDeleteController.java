package MVC.MVCControllers.customer;

import MVC.MVCController;
import MVC.MVCModel;
import business.OrderManager;
import entity.Customer;
import entity.Order;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Created by D.Lazorkin on 02.04.2016.
 */
public class CustomerOrderDeleteController implements MVCController {

    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) {
        return null;
    }

    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) {

        Customer currentCustomer = (Customer) request.getSession().getAttribute("customer");
        String orderIdToDelete = request.getParameter("orderId");

        String message  = "";
        Order orderToDelete = null;
        try {
            OrderManager orderManager = new OrderManager();
            orderToDelete = orderManager.findOrderById(Long.valueOf(orderIdToDelete));
            if (orderToDelete.getCustomerId() == currentCustomer.getCustomerId()) {
                orderManager.deleteOrder(orderToDelete);
                message = "Your order (ID: " + orderToDelete.getOrderId() + ") was deleted!";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new MVCModel("/customer/CustomerEditDeleteOrders.jsp", null, message);
    }

}
