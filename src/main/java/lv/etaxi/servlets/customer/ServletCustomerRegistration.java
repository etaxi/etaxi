package lv.etaxi.servlets.customer;

import lv.etaxi.business.CustomerManager;
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
@WebServlet(name = "ServletCustomerRegistration", urlPatterns = {"/customer/registration"})
public class ServletCustomerRegistration extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("message", "Please, enter information about new customer!");
        request.getRequestDispatcher("/customer/CustomerRegistration.jsp").forward(request, response);
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name     = request.getParameter("name");
        String phone    = request.getParameter("phone");
        String password = request.getParameter("password");

        String message = ((name == null || name.isEmpty()) ? "name, surname; " : "") +
                         ((phone == null || phone.isEmpty()) ? "phone; " : "") +
                         ((password == null || password.isEmpty()) ? "password; " : "");

        Boolean registrationSuccessful = false;
        CustomerManager customerManager = new CustomerManagerImpl();
        if (message.isEmpty()) {

            try {
                if (customerManager.findByLogin(phone) != null) {
                    message = "You can't use such phone! The customer with such phone already present!";
                }
                else {
                    Customer newCustomer = new Customer((long)0, name, phone, password);
                    customerManager.create(newCustomer);

                    message = "Registration successful: " + newCustomer.getName();
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
            request.getRequestDispatcher("/customer/CustomerMenu.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/customer/CustomerRegistration.jsp").forward(request, response);
        }

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
