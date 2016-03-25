package servlets.customer;

import dao.CustomerDAO;
import dao.jdbc.CustomerDAOImpl;
import dao.jdbc.DBConnection;
import entity.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by D.Lazorkin on 25.03.2016.
 */
@WebServlet(name = "ServletAuthorization" , urlPatterns = {"/customer/authorization"})
public class ServletAuthorization extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        DBConnection dbService = new DBConnection();
        CustomerDAO customerDAO = new CustomerDAOImpl(dbService.getConnection(), dbService.getDatabaseName());
        Customer customerDataSet = null;
        try {
            customerDataSet = customerDAO.getByLogin(login);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (customerDataSet == null || !customerDataSet.getPassword().equals(password)) {
            request.setAttribute("message", "Wrong username or password!");
            request.getRequestDispatcher("/customer/authorization.jsp").forward(request, response);
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // сохраняем логин (телефон) пользователя в сессию, для дальнейшем идентификации клиента в системе
        request.getSession().setAttribute("customerId", customerDataSet.getCustomerId());

        request.setAttribute("message", "Authorization successful: " + customerDataSet.getName());
        request.getRequestDispatcher("/customer/menuCustomer.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
