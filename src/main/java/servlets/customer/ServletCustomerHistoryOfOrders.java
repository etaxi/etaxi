package servlets.customer;

import business.OrderManager;
import business.ServletHelper;
import entity.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by D.Lazorkin on 25.03.2016.
 */
@WebServlet(name = "ServletCustomerHistoryOfOrders", urlPatterns = {"/customer/historyOfOrders"})
public class ServletCustomerHistoryOfOrders extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("customerId") != null) {

            try {
                long id = (long) request.getSession().getAttribute("customerId");
                List<Order> listOfOrders = new OrderManager().getOrdersByCustomerId(id);
                String htmlTable = ServletHelper.generateHTMLTableForOrders(listOfOrders, false, false, false);

                request.setAttribute("table", htmlTable);
                request.setAttribute("message", "View the history of your orders");
                request.getRequestDispatcher("/customer/CustomerListOrders.jsp").forward(request, response);

            } catch (SQLException e) {
                e.printStackTrace();
            }

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

}
