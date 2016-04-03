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

        OrderManager orderManager = new OrderManager();
        Order currentOrder = orderManager.findOrderById(orderIdToDelete);
        Boolean changeIsPossible = orderManager.checkOrderChangePossibility(currentCustomer, currentOrder);

        String message = "";
        if (changeIsPossible) {

            try {
                orderManager.deleteOrder(currentOrder);
                message = "Your order (ID: " + currentOrder.getOrderId() + ") was deleted!";
            } catch (SQLException e) {
                message = "Order information delete failed! Please try again!";
            }
        }

        return new MVCModel("/customer/CustomerEditDeleteOrders.jsp", null, message);
    }

}
