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
@WebServlet(name = "ServletCompleteOrder", urlPatterns = {"/taxi/completeorder"})
public class ServletCompleteOrder extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("orderId") == null){
            request.setAttribute("message", "You dont have order to complite");
            request.getRequestDispatcher("/taxi/menuauthorized.jsp").forward(request, response);
        }
        else {
            long orderId = Long.parseLong((String) request.getSession().getAttribute("orderId"));
            DBConnection dbConnection = new DBConnection();
            OrderDAO orderDAO = new OrderDAOImpl(dbConnection.getConnection(), dbConnection.getDatabaseName());

            try {
                Order order = orderDAO.getById(orderId);
                order.setOrderStatus(Order.OrderStatus.DELIVERED);
                orderDAO.update(order);
                request.setAttribute("message", "Completed order Id=" + orderId);
                request.getRequestDispatcher("/taxi/menuauthorized.jsp").forward(request, response);

            } catch (SQLException e) {
                e.printStackTrace();
                request.getRequestDispatcher("/taxi").forward(request, response);
            }
        }
    }
}
