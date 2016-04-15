package lv.etaxi.servlets.taxi;

import lv.etaxi.business.managers.OrderManagerImpl;
import lv.etaxi.servlets.ServletHelper;
import lv.etaxi.entity.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Aleks on 24.03.2016.
 */
//@WebServlet(name = "ServletTaxiHistory", urlPatterns = {"/taxi/history"})
public class ServletTaxiHistory extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("taxiID") != null) {

            try {
                long id = (long) request.getSession().getAttribute("taxiID");
                List<Order> listOfOrders = new OrderManagerImpl().getTaxiOrders(id);
                String htmlTable = ServletHelper.generateHTMLTableForOrders(listOfOrders, false, false, false);

                request.setAttribute("table", htmlTable);
                request.setAttribute("message", "View the history of your orders");
                request.getRequestDispatcher("/taxi/TaxiHistory.jsp").forward(request, response);
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);

            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
        else {
            request.setAttribute("message", " ");
            request.getRequestDispatcher("/taxi/TaxiAuthorization.jsp").forward(request, response);
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }


}