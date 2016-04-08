package servlets.taxi;

import business.OrderManagerImpl;
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
//@WebServlet(name = "ServletTaxiTakeOrder", urlPatterns = {"/taxi/takeorder"})
public class ServletTaxiTakeOrder extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("taxiID") != null) {
            String orderId     = request.getParameter("orderId");

            try {
                Order order = new OrderManagerImpl().findOrderById(Long.parseLong(orderId));
                Long taxiId = (long) request.getSession().getAttribute("taxiID");
                order.setTaxiId(taxiId);
                order.setOrderStatus(Order.OrderStatus.TAKEN);
                new OrderManagerImpl().updateOrder(order);
                request.getSession().setAttribute("orderId", orderId);

                request.setAttribute("message", "Taken order Id="+ order.getOrderId());
                request.getRequestDispatcher("/taxi/TaxiMenuAuthorized.jsp").forward(request, response);
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }
}
