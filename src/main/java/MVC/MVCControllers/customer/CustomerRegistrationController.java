package MVC.MVCControllers.customer;

import MVC.MVCController;
import MVC.MVCModel;
import business.CustomerManager;
import entity.Customer;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Created by D.Lazorkin on 31.03.2016.
 */
public class CustomerRegistrationController implements MVCController {

    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) {

        return new MVCModel("/customer/CustomerRegistration.jsp", null, "Please, enter information about new customer!");

    }

    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) {

        String name     = request.getParameter("name");
        String phone    = request.getParameter("phone");
        String password = request.getParameter("password");

        String message = ((name == null || name.isEmpty()) ? "name, surname; " : "") +
                ((phone == null || phone.isEmpty()) ? "phone; " : "") +
                ((password == null || password.isEmpty()) ? "password; " : "");

        Boolean registrationSuccessful = false;
        CustomerManager customerManager = new CustomerManager();
        if (message.isEmpty()) {

            try {
                if (customerManager.findCustomerByLogin(phone) != null) {
                    message = "You can't use such phone! The customer with such phone already present!";
                }
                else {
                    Customer newCustomer = new Customer((long)0, name, phone, password);
                    customerManager.createNewCustomer(newCustomer);

                    message = "Registration successful: " + newCustomer.getName();
                    registrationSuccessful = true;

                    // сохраняем пользователя в сессию, для дальнейшей идентификации клиента в системе
                    request.getSession().setAttribute("customer", newCustomer);
                }
            } catch (SQLException e) {
                message = "Registration failed! Please try again!";
            }

        }
        else {
            message = "Please, input information in fields: " + message;
        }

        return new MVCModel("/customer/CustomerMenu.jsp", null, message);

    }

}
