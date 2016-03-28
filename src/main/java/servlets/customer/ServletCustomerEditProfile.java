package servlets.customer;

import business.CustomerManager;
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
@WebServlet(name = "ServletCustomerEditProfile", urlPatterns = {"/customer/editProfileCustomer"})
public class ServletCustomerEditProfile extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("customerId") != null) {

            Customer currentCustomer = null;
            try {
                currentCustomer = new CustomerManager().findCustomerById((long) request.getSession().getAttribute("customerId"));
            } catch (SQLException e) {
                e.printStackTrace();
            }

            request.setAttribute("message", "Please, enter new information about customer!");
            request.setAttribute("name", currentCustomer.getName());
            request.setAttribute("phone", currentCustomer.getPhone());
            request.setAttribute("password", currentCustomer.getPassword());

            request.getRequestDispatcher("/customer/CustomerEditProfile.jsp").forward(request, response);
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
        }
        else {
            request.setAttribute("message", "");
            request.getRequestDispatcher("/customer/CustomerAuthorization.jsp").forward(request, response);
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("customerId") != null) {

            CustomerManager customerManager = new CustomerManager();
            Customer currentCustomer = null;
            try {
                currentCustomer = customerManager.findCustomerById((long) request.getSession().getAttribute("customerId"));
            } catch (SQLException e) {
                e.printStackTrace();
            }

            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String password = request.getParameter("password");

            String message = ((name == null || name.isEmpty()) ? "name, surname; " : "") +
                             ((phone == null || phone.isEmpty()) ? "phone; " : "") +
                             ((password == null || password.isEmpty()) ? "password; " : "");

            Boolean registrationSuccessful = false;
            if (message.isEmpty()) {

                currentCustomer.setName(name);
                currentCustomer.setPassword(password);
                currentCustomer.setPhone(phone);

                try {
                    Customer presentCustomerWthSuchLogin = customerManager.findCustomerByLogin(currentCustomer.getPhone());
                    if ((presentCustomerWthSuchLogin != null)
                            && (presentCustomerWthSuchLogin.getCustomerId() != currentCustomer.getCustomerId())) {
                        message = "You can't use such phone! The customer with such phone already present!";
                    }
                    else{
                        customerManager.updateCustomer(currentCustomer);
                        message = "The data change is made (" + currentCustomer.getName() + ")";
                        registrationSuccessful = true;
                    }
                } catch (SQLException e) {
                    message = "Registration failed! Please try again!";
                }

            } else {
                message = "Please, input information in fields: " + message;
            }

            request.setAttribute("message", message);
            request.setAttribute("name", name);
            request.setAttribute("phone", phone);
            request.setAttribute("password", password);

            if (registrationSuccessful) {
                request.getRequestDispatcher("/customer/CustomerMenu.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/customer/CustomerEditProfile.jsp").forward(request, response);
            }
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
        }
        else {
            request.setAttribute("message", "");
            request.getRequestDispatcher("/customer/CustomerAuthorization.jsp").forward(request, response);
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }
}
