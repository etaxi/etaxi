package servlets;

import dao.OrderDAO;
import dao.jdbc.OrderDAOImpl;
import entity.Order;
import dao.jdbc.DBService;
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

public class ServletNewOrder extends HttpServlet {

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

            DBService dbService = new DBService();
            OrderDAO orderDAO = new OrderDAOImpl(dbService.getConnection(), dbService.getDatabaseName());
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

