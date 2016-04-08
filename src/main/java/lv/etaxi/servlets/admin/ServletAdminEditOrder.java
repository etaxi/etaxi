package lv.etaxi.servlets.admin;

import lv.etaxi.business.CustomerManagerImpl;
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
import java.sql.Timestamp;

/**
 * Created by Genady Zalesky on 29.03.2016
 */

@WebServlet(name = "ServletAdminEditOrder", urlPatterns = {"/admin/editOrderByTaxi"})
public class ServletAdminEditOrder extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("customerId") != null) {

            // Get parameter from request
            String orderIdToEdit = request.getParameter("orderId");
            Boolean changeIsPossible = false;

            Customer currentCustomer = null;
            try {
                currentCustomer = new CustomerManagerImpl().findCustomerById((long) request.getSession().getAttribute("customerId"));
            } catch (SQLException e) {
                e.printStackTrace();
            }

            Order orderToEdit = null;
            try {
                orderToEdit = new OrderManagerImpl().findOrderById(Long.valueOf(orderIdToEdit));
                if (orderToEdit.getCustomerId() == request.getSession().getAttribute("customerId")) {
                    changeIsPossible = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


            if (changeIsPossible) {
                request.setAttribute("customer", currentCustomer.getName());
                request.setAttribute("orderId", orderToEdit.getOrderId());
                request.setAttribute("fromAddress", orderToEdit.getFromAdress());
                request.setAttribute("toAddress", orderToEdit.getToAdress());
                request.setAttribute("orderedDateTime", orderToEdit.getOrderedDateTime());

                request.getRequestDispatcher("/admin/AdminEditOrder.jsp").forward(request, response);
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);
            }

        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("customerId") != null) {

            String orderId = request.getParameter("orderId");
            String fromAddress = request.getParameter("fromAddress");
            String toAddress = request.getParameter("toAddress");
            String orderedDateTime = request.getParameter("orderedDateTime");

            String message = ((fromAddress == null || fromAddress.isEmpty()) ? "from address; " : "") +
                    ((toAddress == null || toAddress.isEmpty()) ? "to address; " : "") +
                    ((orderedDateTime == null || orderedDateTime.isEmpty()) ? "date and time of ride; " : "");

            Boolean updateSuccessful = false;
            Order updatedOrder = null;
            if (message.isEmpty()) {

                updatedOrder = new Order(Long.parseLong(orderId), (long) request.getSession().getAttribute("customerId"),
                        new Timestamp(new java.util.Date().getTime()),
                        Timestamp.valueOf(orderedDateTime),
                        Order.OrderStatus.WAITING,
                        fromAddress, toAddress, (long) 0, 0, 0, 0, "");
                try {
                    new OrderManagerImpl().updateOrder(updatedOrder);
                    updateSuccessful = true;
                    message = "Order ID: " + updatedOrder.getOrderId() + " was changed!";
                } catch (SQLException e) {
                    message = "Order information update failed! Please try again!";
                }

            } else {
                message = "Please, input information in fields: " + message;
            }

            if (updateSuccessful) {
                request.setAttribute("messageAboutOperation", message);
                //request.getRequestDispatcher("/customer/changeOrders").forward(request, response);
                response.sendRedirect("/admin/changeOrders");
            } else {
                request.setAttribute("message", message);
                request.setAttribute("orderId", orderId);
                request.setAttribute("fromAddress", fromAddress);
                request.setAttribute("toAddress", toAddress);
                request.getRequestDispatcher("/admin/AdminEditOrder.jsp").forward(request, response);
            }

            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
        }
        else {
            request.setAttribute("message", "");
            request.getRequestDispatcher("/admin/AdminMenu.jsp").forward(request, response);
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }
}
