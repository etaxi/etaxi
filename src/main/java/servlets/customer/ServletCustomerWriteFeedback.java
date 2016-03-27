package servlets.customer;

import dao.CustomerDAO;
import dao.OrderDAO;
import dao.jdbc.CustomerDAOImpl;
import dao.jdbc.DBConnection;
import dao.jdbc.OrderDAOImpl;
import entity.Customer;
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
@WebServlet(name = "ServletCustomerWriteFeedback", urlPatterns = {"/customer/writeFeedback"})
public class ServletCustomerWriteFeedback extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("customerId") != null) {

            // Get parameter from request
            String orderIdToChange = request.getParameter("orderId");
            Boolean changeIsPossible = false;

            DBConnection dbService = new DBConnection();
            CustomerDAO customerDAO = new CustomerDAOImpl(dbService.getConnection(), dbService.getDatabaseName());
            OrderDAO orderDAO = new OrderDAOImpl(dbService.getConnection(), dbService.getDatabaseName());

            Customer currentCustomer = null;
            try {
                currentCustomer = customerDAO.getById((long) request.getSession().getAttribute("customerId"));
            } catch (SQLException e) {
                e.printStackTrace();
            }

            Order orderToEdit = null;
            try {
                orderToEdit = orderDAO.getById(Long.valueOf(orderIdToChange));
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
            request.setAttribute("fromAddress", orderToEdit.getFromAdress());
            request.setAttribute("toAddress", orderToEdit.getToAdress());
            request.setAttribute("feedback", orderToEdit.getFeedback());

            request.getRequestDispatcher("/customer/editOrderFeedback.jsp").forward(request, response);

            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);

        }

        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("customerId") != null) {

            String orderId = request.getParameter("orderId");
            String feedback = request.getParameter("feedback");

            String message = ((feedback == null || feedback.isEmpty()) ? "feedback;" : "");

            Boolean updateSuccessful = false;
            Order updatedOrder = null;

            DBConnection dbService = new DBConnection();

            if (message.isEmpty()) {
                OrderDAO orderDAO = new OrderDAOImpl(dbService.getConnection(), dbService.getDatabaseName());
                try {
                    updatedOrder = orderDAO.getById(Long.parseLong(orderId));
                    updatedOrder.setFeedback(feedback);
                    orderDAO.update(updatedOrder);
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
                request.getRequestDispatcher("/customer/writeFeedbacks").forward(request, response);
            } else {
                Customer currentCustomer = null;
                CustomerDAO customerDAO = new CustomerDAOImpl(dbService.getConnection(), dbService.getDatabaseName());
                try {
                    currentCustomer = customerDAO.getById((long) request.getSession().getAttribute("customerId"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                request.setAttribute("message", message);
                request.setAttribute("customer", currentCustomer.getName());
                request.setAttribute("orderId", updatedOrder.getOrderId());
                request.setAttribute("date", updatedOrder.getDateTime());
                request.setAttribute("fromAddress", updatedOrder.getFromAdress());
                request.setAttribute("toAddress", updatedOrder.getToAdress());
                request.setAttribute("feedback", updatedOrder.getFeedback());
                request.getRequestDispatcher("/customer/editOrderFeedback.jsp").forward(request, response);
            }

            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);

        }

    }
}
