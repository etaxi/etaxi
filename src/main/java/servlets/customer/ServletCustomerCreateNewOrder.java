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
import java.sql.Timestamp;

/**
 * Created by D.Lazorkin on 25.03.2016.
 */
@WebServlet(name = "ServletCustomerCreateNewOrder", urlPatterns = {"/customer/createNewOrder"})
public class ServletCustomerCreateNewOrder extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("customerId") != null) {

            DBConnection dbService = new DBConnection();
            CustomerDAO customerDAO = new CustomerDAOImpl(dbService.getConnection(), dbService.getDatabaseName());
            Customer customer = null;
            try {
                customer = customerDAO.getById((long) request.getSession().getAttribute("customerId"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.setAttribute("customer", customer.getName());
            request.getRequestDispatcher("/customer/newOrder.jsp").forward(request, response);
        }

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("customerId") != null) {

            String fromAddress = request.getParameter("fromAddress");
            String toAddress = request.getParameter("toAddress");

            String message = ((fromAddress == null || fromAddress.isEmpty()) ? "from address; " : "") +
                    ((toAddress == null || toAddress.isEmpty()) ? "to address; " : "");

            Boolean registrationSuccessful = false;
            if (message.isEmpty()) {

                DBConnection dbService = new DBConnection();
                OrderDAO orderDAO = new OrderDAOImpl(dbService.getConnection(), dbService.getDatabaseName());
                Order newOrder = new Order((long) 0, (long) request.getSession().getAttribute("customerId"),
                        new Timestamp(new java.util.Date().getTime()),
                        Order.OrderStatus.WAITING,
                        fromAddress, toAddress, (long) 0, 0, 0, 0, "");
                        registrationSuccessful = true;
                try {
                    newOrder.setOrderId(orderDAO.update(newOrder));
                    message = "New order was created (new order ID: " + newOrder.getOrderId() + ")";
                    fromAddress = "";  toAddress = "";
                    request.setAttribute("orderId", newOrder.getOrderId());
                } catch (SQLException e) {
                    message = "New order creation failed! Please try again!";
                }

            } else {
                message = "Please, input information in fields: " + message;
            }

            request.setAttribute("message", message);
            if (registrationSuccessful) {
                request.getRequestDispatcher("/customer/menuCustomer.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/customer/newOrder.jsp").forward(request, response);
            }

            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);

        }
    }

}

