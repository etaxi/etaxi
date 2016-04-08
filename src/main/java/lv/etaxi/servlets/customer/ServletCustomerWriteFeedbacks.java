package lv.etaxi.servlets.customer;

import lv.etaxi.business.OrderManagerImpl;
import lv.etaxi.business.ServletHelper;
import lv.etaxi.entity.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by D.Lazorkin on 25.03.2016.
 */
@WebServlet(name = "ServletCustomerWriteFeedbacks", urlPatterns = {"/customer/writeFeedbacks"})
public class ServletCustomerWriteFeedbacks extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("customerId") != null) {

            Timestamp orderedDateTimeBegin = Timestamp.valueOf(request.getParameter("orderedDateTimeBegin"));
            Timestamp orderedDateTimeEnd = Timestamp.valueOf(request.getParameter("orderedDateTimeEnd"));

            try {
                long id = (long) request.getSession().getAttribute("customerId");
                List<Order> listOfOrders = new OrderManagerImpl().getCompletedOrdersOfCustomer(id, orderedDateTimeBegin, orderedDateTimeEnd);
                String htmlTable = ServletHelper.generateHTMLTableForOrders(listOfOrders, false, false, true);

                request.setAttribute("table", htmlTable);
                request.setAttribute("message", "Write feedback to the orders");
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("customerId") != null) {

            request.setAttribute("message", "Write feedback to the orders");
            request.setAttribute("orderedDateTimeBegin", new Timestamp(new java.util.Date().getTime()));
            request.setAttribute("orderedDateTimeEnd", new Timestamp(new java.util.Date().getTime()));
            request.setAttribute("servletToCall", "/customer/writeFeedbacks");

            request.getRequestDispatcher("/customer/CustomerListOrders.jsp").forward(request, response);
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
