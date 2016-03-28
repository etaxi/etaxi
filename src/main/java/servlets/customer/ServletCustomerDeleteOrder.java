package servlets.customer;

import business.OrderManager;
import entity.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by D.Lazorkin on 25.03.2016.
 */
@WebServlet(name = "ServletCustomerDeleteOrder", urlPatterns = {"/customer/deleteOrderByCustomer"})
public class ServletCustomerDeleteOrder extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("customerId") != null) {

            // Get parameter from request
            String orderIdToDelete = request.getParameter("orderId");

            Order orderToDelete = null;
            OrderManager orderManager = new OrderManager();
            try {
                orderToDelete = orderManager.findOrderById(Long.valueOf(orderIdToDelete));
                if (orderToDelete.getCustomerId() == request.getSession().getAttribute("customerId")) {
                    orderManager.deleteOrder(orderToDelete);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            request.setAttribute("messageAboutOperation", "Your order (ID: " + orderToDelete.getOrderId() + ") was deleted!");
            request.getRequestDispatcher("/customer/changeOrders").forward(request, response);
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
        }
        else {
            request.setAttribute("message", "");
            request.getRequestDispatcher("/customer/CustomerAuthorization.jsp").forward(request, response);
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
}

