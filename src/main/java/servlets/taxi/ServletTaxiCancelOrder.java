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

/**
 * Created by Aleks on 24.03.2016.
 */
@WebServlet(name = "ServletTaxiCancelOrder", urlPatterns = {"/taxi/cancelorder"})
public class ServletTaxiCancelOrder extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("orderId") == null){
            request.setAttribute("message", "You dont have order to cancel");
            request.getRequestDispatcher("/taxi/menuauthorized.jsp").forward(request, response);
        }
        else {
            long orderId     = Long.parseLong((String) request.getSession().getAttribute("orderId"));
            DBConnection dbConnection = new DBConnection();
            OrderDAO orderDAO = new OrderDAOImpl(dbConnection.getConnection(), dbConnection.getDatabaseName());

            try {
                Order order = orderDAO.getById(orderId);
                order.setTaxiId((long) 0);
                order.setOrderStatus(Order.OrderStatus.WAITING);
                orderDAO.update(order);
                request.getSession().removeAttribute("orderId");
                request.setAttribute("message", "Canceled order Id=" + orderId);
                request.getRequestDispatcher("/taxi/menuauthorized.jsp").forward(request, response);

            } catch (SQLException e) {
                e.printStackTrace();
                request.getRequestDispatcher("/taxi").forward(request, response);
            }
        }

    }
}
