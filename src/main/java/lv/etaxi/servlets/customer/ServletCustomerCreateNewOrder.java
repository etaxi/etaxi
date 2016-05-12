package lv.etaxi.servlets.customer;

import lv.etaxi.business.managers.CustomerManagerImpl;
import lv.etaxi.business.managers.OrderManagerImpl;
import lv.etaxi.entity.Customer;
import lv.etaxi.entity.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Created by D.Lazorkin on 25.03.2016.
 */
//@WebServlet(name = "ServletCustomerCreateNewOrder", urlPatterns = {"/customer/createNewOrder"})
public class ServletCustomerCreateNewOrder extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("customerId") != null) {

            Customer CurrentCustomer = null;
            try {
                CurrentCustomer = new CustomerManagerImpl().findById((long) request.getSession().getAttribute("customerId"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.setAttribute("customer", CurrentCustomer.getName());
            request.getRequestDispatcher("/customer/CustomerNewOrder.jsp").forward(request, response);
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

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("customerId") != null) {

            String fromAddress = request.getParameter("fromAddress");
            String toAddress = request.getParameter("toAddress");
            String orderedDateTime = request.getParameter("orderedDateTime");

            String message = ((fromAddress == null || fromAddress.isEmpty()) ? "ride from address; " : "") +
                             ((toAddress == null || toAddress.isEmpty()) ? "ride to the address; " : "") +
                             ((orderedDateTime == null || orderedDateTime.isEmpty()) ? "date and time of taxi ride; " : "");

            Boolean orderCreationSuccessful = false;
            if (message.isEmpty()) {

                Order newOrder = new Order((long) 0, (long) request.getSession().getAttribute("customerId"),
                        new Timestamp(new java.util.Date().getTime()),
                        Timestamp.valueOf(orderedDateTime),
                        Order.OrderStatus.WAITING,
                        fromAddress, toAddress, (long) 0, 0, 0, 0, "");
                try {
                    new OrderManagerImpl().create(newOrder);
                    message = "New order was created (new order ID: " + newOrder.getOrderId() + ")";
                    orderCreationSuccessful = true;
                } catch (SQLException e) {
                    message = "New order creation failed! Please try again!";
                }

            } else {
                message = "Please, input information in fields: " + message;
            }

            request.setAttribute("message", message);
            if (orderCreationSuccessful) {
                request.getRequestDispatcher("/customer/CustomerMenu.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/customer/CustomerNewOrder.jsp").forward(request, response);
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

