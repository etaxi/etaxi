package lv.etaxi.servlets.customer;

import lv.etaxi.business.CustomerManagerImpl;
import lv.etaxi.business.OrderManager;
import lv.etaxi.business.OrderManagerImpl;
import lv.etaxi.entity.Customer;
import lv.etaxi.entity.Order;

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
@WebServlet(name = "ServletCustomerWriteFeedback", urlPatterns = {"/customer/writeFeedback"})
public class ServletCustomerWriteFeedback extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("customerId") != null) {

            // Get parameter from request
            String orderIdToChange = request.getParameter("orderId");
            Boolean changeIsPossible = false;

            Customer currentCustomer = null;
            try {
                currentCustomer = new CustomerManagerImpl().findCustomerById((long) request.getSession().getAttribute("customerId"));
            } catch (SQLException e) {
                e.printStackTrace();
            }

            Order orderToEdit = null;
            try {
                orderToEdit = new OrderManagerImpl().findOrderById(Long.valueOf(orderIdToChange));
                if (orderToEdit.getCustomerId() == request.getSession().getAttribute("customerId")) {
                    changeIsPossible = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


        if (changeIsPossible) {

            request.setAttribute("customer", currentCustomer.getName());
            request.setAttribute("orderId", orderToEdit.getOrderId());
            request.setAttribute("date", orderToEdit.getDateTime());
            request.setAttribute("orderedDateTime", orderToEdit.getOrderedDateTime());
            request.setAttribute("fromAddress", orderToEdit.getFromAdress());
            request.setAttribute("toAddress", orderToEdit.getToAdress());
            request.setAttribute("feedback", orderToEdit.getFeedback());

            request.getRequestDispatcher("/customer/CustomerWriteFeedbackToOrder.jsp").forward(request, response);
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);

        }

            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
        }
        else {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("customerId") != null) {

            String orderId = request.getParameter("orderId");
            String feedback = request.getParameter("feedback");

            String message = ((feedback == null || feedback.isEmpty()) ? "feedback;" : "");

            Boolean updateSuccessful = false;
            Order updatedOrder = null;

            if (message.isEmpty()) {
                try {
                    OrderManager orderManager = new OrderManagerImpl();
                    updatedOrder = orderManager.findOrderById(Long.parseLong(orderId));
                    updatedOrder.setFeedback(feedback);
                    orderManager.updateOrder(updatedOrder);
                    updateSuccessful = true;
                    message = "Order ID: " + updatedOrder.getOrderId() + " was updated!";
                } catch (SQLException e) {
                    message = "Order information update failed! Please try again!";
                }

            } else {
                message = "Please, input information in fields: " + message;
            }

            if (updateSuccessful) {
                request.setAttribute("messageAboutOperation", message);
                //request.getRequestDispatcher("/customer/writeFeedbacks").forward(request, response);
                response.sendRedirect("/customer/writeFeedbacks");
            } else {
                Customer currentCustomer = null;
                try {
                    currentCustomer = new CustomerManagerImpl().findCustomerById((long) request.getSession().getAttribute("customerId"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                request.setAttribute("message", message);
                request.setAttribute("customer", currentCustomer.getName());
                request.setAttribute("orderId", updatedOrder.getOrderId());
                request.setAttribute("date", updatedOrder.getDateTime());
                request.setAttribute("orderedDateTime", updatedOrder.getOrderedDateTime());
                request.setAttribute("fromAddress", updatedOrder.getFromAdress());
                request.setAttribute("toAddress", updatedOrder.getToAdress());
                request.setAttribute("feedback", updatedOrder.getFeedback());
                request.getRequestDispatcher("/customer/CustomerWriteFeedbackToOrder.jsp").forward(request, response);
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
