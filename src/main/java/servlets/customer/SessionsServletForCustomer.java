package servlets.customer;

import dao.CustomerDAO;
import dao.OrderDAO;
import dao.jdbc.CustomerDAOImpl;
import dao.jdbc.OrderDAOImpl;
import entity.Customer;
import dao.jdbc.DBConnection;
import entity.Order;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;


public class SessionsServletForCustomer extends HttpServlet {

    //get logged user profile
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("userCustomerId") == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        } else {

            response.setStatus(HttpServletResponse.SC_OK);
            response.sendRedirect("mainMenuForCustomer.html");
        }
    }

    //sign in
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null || password == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        DBConnection dbConnection = new DBConnection();
        CustomerDAO customerDAO = new CustomerDAOImpl(dbConnection.getConnection(), dbConnection.getDatabaseName());
        Customer customer = null;
        try {
             customer = customerDAO.getByLogin(login);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (customer == null || !customer.getPassword().equals(password)) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // сохраняем логин (телефон) пользователя в сессию, для дальнейшем идентификации клиента в системе
        request.getSession().setAttribute("userCustomerId", customer.getPhone());

        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect("mainMenuForCustomer.html");
    }

    //sign out
    public void doDelete(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("userCustomerId") == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        } else {
            request.getSession().setAttribute("userCustomerId", null);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Goodbye!");
            response.setStatus(HttpServletResponse.SC_OK);
        }

    }

    public static class ServletNewOrder extends HttpServlet {

        public void doGet(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

            Map<String, Object> pageVariables = new HashMap<>();
            pageVariables.put("message", "Please, enter information about new order!");
            pageVariables.put("customerID", "");
            pageVariables.put("fromAddress", "");
            pageVariables.put("toAddress", "");
            pageVariables.put("feedback", "");

            response.getWriter().println(PageGenerator.instance().getPage("order.html", pageVariables));

            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);

        }

        public void doPost(HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException {

            Map<String, Object> pageVariables = new HashMap<>();

            Long customerID     = Long.valueOf(request.getParameter("customerID"));
            String fromAddress  = request.getParameter("fromAddress");
            String toAddress    = request.getParameter("toAddress");
            String feedback     = request.getParameter("feedback");

                    String message = ((customerID == null || customerID == 0) ? "customer ID; " : "") +
                             ((fromAddress == null || fromAddress.isEmpty()) ? "from address; " : "") +
                             ((toAddress == null || toAddress.isEmpty()) ? "to address; " : "");

            if (message.isEmpty()) {

                DBConnection dbConnection = new DBConnection();
                OrderDAO orderDAO = new OrderDAOImpl(dbConnection.getConnection(), dbConnection.getDatabaseName());
                Order newOrder = new Order((long)0, customerID,
                        new Timestamp(new java.util.Date().getTime()),
                        Order.OrderStatus.WAITING,
                        fromAddress, toAddress, (long)0, 0, 0, 0, feedback);
                try {
                    newOrder.setOrderId(orderDAO.update(newOrder));
                    message = "Registration successful (new order ID: " + newOrder.getOrderId() + ")";
                    customerID = (long)0; fromAddress = ""; toAddress = ""; feedback = "";
                } catch (SQLException e) {
                    message = "Registration failed! Please try again!";
                }

            }
            else {
                message = "Please, input information in fields: " + message;
            }

            pageVariables.put("message", message);
            pageVariables.put("customerID", Long.toString(customerID));
            pageVariables.put("fromAddress", fromAddress);
            pageVariables.put("toAddress", toAddress);
            pageVariables.put("feedback", feedback);
            response.getWriter().println(PageGenerator.instance().getPage("order.html", pageVariables));

            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
        }

    }
}
