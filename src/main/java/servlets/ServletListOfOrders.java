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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServletListOfOrders extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

//        if (request.getSession().getAttribute("userId") == null) {
//            response.setContentType("text/html;charset=utf-8");
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        } else {

            Map<String, Object> pageVariables = new HashMap<>();
            pageVariables.put("table", "");

            response.getWriter().println(PageGenerator.instance().getPage("orders.html", pageVariables));
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
        }
    //}

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

//        if (request.getSession().getAttribute("userId") == null) {
//            response.setContentType("text/html;charset=utf-8");
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        } else {

            Map<String, Object> pageVariables = new HashMap<>();

            DBService dbService = new DBService();
            OrderDAO orderDAO = new OrderDAOImpl(dbService.getConnection(), dbService.getDatabaseName());

            String htmlTable = "";

            try {
                List<Order> listOfOrders = orderDAO.getAll();
                htmlTable = generateHTMLTableForOrders(listOfOrders);

            } catch (SQLException e) {
                e.printStackTrace();
            }

            pageVariables.put("table", htmlTable);
            response.getWriter().println(PageGenerator.instance().getPage("orders.html", pageVariables));

            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
        }
    //}

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
                    .append("</tr>");
        }

        htmlString.append("</table>");

        return htmlString.toString();
    }

}

