package lv.etaxi.servlets.taxi;

import lv.etaxi.business.managers.OrderManagerImpl;
import lv.etaxi.entity.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Aleks on 24.03.2016.
 */
//@WebServlet(name = "ServletTaxiCancelOrder", urlPatterns = {"/taxi/cancelorder"})
public class ServletTaxiCancelOrder extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("taxiID") != null) {

            if (request.getSession().getAttribute("orderId") == null){
                request.setAttribute("message", "You dont have order to cancel");
                request.getRequestDispatcher("/taxi/TaxiMenuAuthorized.jsp").forward(request, response);
            }

            long orderId = Long.parseLong((String) request.getSession().getAttribute("orderId"));
            try {
                Order order = new OrderManagerImpl().findOrderById(orderId);
                order.setTaxiId((long) 0);
                order.setOrderStatus(Order.OrderStatus.WAITING);
                new OrderManagerImpl().updateOrder(order);
                request.getSession().removeAttribute("orderId");

                request.setAttribute("message", "Canceled order Id=" + orderId);
                request.getRequestDispatcher("/taxi/taxiMenuAuthorized.jsp").forward(request, response);
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);

            } catch (SQLException e) {
                e.printStackTrace();
                request.getRequestDispatcher("/taxi").forward(request, response);
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
