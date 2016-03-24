package servlets.customer;

import dao.CustomerDAO;
import dao.jdbc.CustomerDAOImpl;
import entity.Customer;
import dao.jdbc.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


public class SessionsServletForCustomer extends HttpServlet {

    //get logged user profile
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("userCustomerId") == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        } else {

            response.setStatus(HttpServletResponse.SC_OK);
            response.sendRedirect("mainMenuForCustomer.html");
        }
    }

    //sign in
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null || password == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        DBConnection dbService = new DBConnection();
        CustomerDAO customerDAO = new CustomerDAOImpl(dbService.getConnection(), dbService.getDatabaseName());
        Customer customerDataSet = null;
        try {
             customerDataSet = customerDAO.getByLogin(login);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (customerDataSet == null || !customerDataSet.getPassword().equals(password)) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // сохраняем логин (телефон) пользователя в сессию, для дальнейшем идентификации клиента в системе
        request.getSession().setAttribute("userCustomerId", customerDataSet.getPhone());

        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect("mainMenuForCustomer.html");
    }

    //sign out
    public void doDelete(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("userCustomerId") == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        } else {
            request.getSession().setAttribute("userCustomerId", null);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Goodbye!");
            response.setStatus(HttpServletResponse.SC_OK);
        }

    }
}
