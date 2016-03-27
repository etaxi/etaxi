package servlets.customer;

import dao.OrderDAO;
import dao.jdbc.DBConnection;
import dao.jdbc.OrderDAOImpl;
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
@WebServlet(name = "ServletCustomerWriteFeedbacks", urlPatterns = {"/customer/writeFeedbacks"})
public class ServletCustomerWriteFeedbacks extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
              doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("customerId") != null) {

            DBConnection dbConnection = new DBConnection();
            OrderDAO orderDAO = new OrderDAOImpl(dbConnection.getConnection(), dbConnection.getDatabaseName());

            try {
                long id = (long) request.getSession().getAttribute("customerId");
                List<Order> listOfOrders = orderDAO.getCompletedOrdersOfCustomer(id);
                String htmlTable = generateHTMLTableForOrders(listOfOrders);

                request.setAttribute("table", htmlTable);
                request.setAttribute("message", "Write feedback to the orders");
                request.getRequestDispatcher("/customer/history.jsp").forward(request, response);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private String generateHTMLTableForOrders(List<Order> listOfOrders) {

        StringBuilder htmlString = new StringBuilder("<table border = 1 width=\"100%\">");
        htmlString.append("<tr>")
                .append("<th> ID </th>")
                .append("<th> Customer ID </th>")
                .append("<th> Date&Time </th>")
                .append("<th> Status </th>")
                .append("<th> From address </th>")
                .append("<th> To address </th>")
                .append("<th> Taxi ID </th>")
                .append("<th> Distance </th>")
                .append("<th> Price </th>")
                .append("<th> Rate </th>")
                .append("<th> Feedback </th>")
                .append("<th> Write feedback </th>")
                .append("</tr>");

        for (Order item : listOfOrders) {
            htmlString.append("<tr>")
                    .append("<td>").append(item.getOrderId())
                    .append("<td>").append(item.getCustomerId())
                    .append("<td>").append(item.getDateTime())
                    .append("<td>").append(item.getOrderStatus())
                    .append("<td>").append(item.getFromAdress())
                    .append("<td>").append(item.getToAdress())
                    .append("<td>").append(item.getTaxiId())
                    .append("<td>").append(item.getDistance())
                    .append("<td>").append(item.getPrice())
                    .append("<td>").append(item.getRate())
                    .append("<td>").append(item.getFeedback())
                    .append("<td><form action='/customer/writeFeedback' method='get'>" +
                            "<input type='hidden' name='orderId' value='" + item.getOrderId() + "'/>" +
                            "<input type='submit' value='Write feedback'/></form>")
                    .append("</tr>");
        }

        htmlString.append("</table>");

        return htmlString.toString();
    }

}
