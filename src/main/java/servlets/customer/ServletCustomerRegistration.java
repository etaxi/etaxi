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
import java.util.HashMap;
import java.util.Map;

/**
 * Created by D.Lazorkin on 25.03.2016.
 */
@WebServlet(name = "ServletCustomerRegistration", urlPatterns = {"/customer/registration"})
public class ServletCustomerRegistration extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("message", "Please, enter information about new customer!");
        request.getRequestDispatcher("/customer/registration.jsp").forward(request, response);

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> pageVariables = new HashMap<>();

        String name     = request.getParameter("name");
        String phone    = request.getParameter("phone");
        String password = request.getParameter("password");

        String message = ((name == null || name.isEmpty()) ? "name, surname; " : "") +
                ((phone == null || phone.isEmpty()) ? "phone; " : "") +
                ((password == null || password.isEmpty()) ? "password; " : "");

        Boolean registrationSuccessful = false;
        if (message.isEmpty()) {

            DBConnection dbConnection = new DBConnection();
            CustomerDAO customerDAO = new CustomerDAOImpl(dbConnection.getConnection(), dbConnection.getDatabaseName());
            Customer newCustomer = new Customer((long)0, name, phone, password);

            try {
                if (customerDAO.getByLogin(newCustomer.getPhone()) != null) {
                    message = "You can't use such phone! The customer with such phone already present!";
                }
                else {
                    newCustomer.setCustomerId(customerDAO.update(newCustomer));
                    message = "Registration successful: " + newCustomer.getName();

                    name = ""; phone = ""; password = "";
                    registrationSuccessful = true;

                    // сохраняем логин (телефон) пользователя в сессию, для дальнейшем идентификации клиента в системе
                    request.getSession().setAttribute("customerId", newCustomer.getCustomerId());
                }
            } catch (SQLException e) {
                message = "Registration failed! Please try again!";
            }

        }
        else {
            message = "Please, input information in fields: " + message;
        }

        request.setAttribute("message", message);
        request.setAttribute("name", name);
        request.setAttribute("phone", phone);
        request.setAttribute("password", password);

        if (registrationSuccessful) {
            request.getRequestDispatcher("/customer/menuCustomer.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/customer/registration.jsp").forward(request, response);
        }

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
