package servlets.taxi;

import business.OrderManager;
import business.ServletHelper;
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
 * Created by Aleks on 24.03.2016.
 */
@WebServlet(name = "ServletTaxiOpenOrders", urlPatterns = {"/taxi/openorders"})
public class ServletTaxiOpenOrders extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("taxiID") != null) {

            try {
                List<Order> listOfOrders = new OrderManager().getOpenOrdersAll();
                String htmlTable = ServletHelper.generateHTMLTableForOrders(listOfOrders, false, false, false);

                request.setAttribute("table", htmlTable);
                request.setAttribute("message", "View all open orders");
                request.getRequestDispatcher("/taxi/TaxiOpenOrders.jsp").forward(request, response);
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        else {
            request.setAttribute("message", "");
            request.getRequestDispatcher("/taxi/TaxiAuthorization.jsp").forward(request, response);
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }


}