package servlets.taxi;

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
@WebServlet(name = "ServletTakeOrder", urlPatterns = {"/taxi/takeorder"})
public class ServletTakeOrder extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId     = request.getParameter("orderId");
        DBConnection dbConnection = new DBConnection();
        OrderDAO orderDAO = new OrderDAOImpl(dbConnection.getConnection(), dbConnection.getDatabaseName());

        try {
            Order order = orderDAO.getById(Long.parseLong(orderId));
            Long taxiId = (long) request.getSession().getAttribute("taxiID");
            order.setTaxiId(taxiId);
            order.setOrderStatus(Order.OrderStatus.TAKEN);
            orderDAO.update(order);
            request.getSession().setAttribute("orderId", orderId);
            request.setAttribute("message", "Taken order Id="+ order.getOrderId());
            request.getRequestDispatcher("/taxi/menuauthorized.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            request.getRequestDispatcher("/taxi").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }
}
