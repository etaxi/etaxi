package lv.etaxi.servlets.customer;


import lv.etaxi.business.managers.CustomerManagerImpl;
import lv.etaxi.entity.Customer;

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
@WebServlet(name = "ServletCustomerAuthorization" , urlPatterns = {"/customer/customerAuthorization"})
public class ServletCustomerAuthorization extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        Customer customer = null;
        try {
            customer = new CustomerManagerImpl().findByLogin(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (customer == null | !customer.getPassword().equals(password)) {
            request.setAttribute("message", "Wrong username or password!");
            request.getRequestDispatcher("/customer/CustomerAuthorization.jsp").forward(request, response);
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // сохраняем логин (телефон) пользователя в сессию, для дальнейшем идентификации клиента в системе
        request.getSession().setAttribute("customerId", customer.getCustomerId());

        request.setAttribute("message", "Authorization successful: " + customer.getName());
        request.getRequestDispatcher("/customer/CustomerMenu.jsp").forward(request, response);
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
}
