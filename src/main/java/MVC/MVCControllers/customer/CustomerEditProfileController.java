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
public class CustomerEditProfileController implements MVCController {

    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) {

        Customer currentCustomer = (Customer) request.getSession().getAttribute("customer");
        return new MVCModel("/customer/CustomerEditProfile.jsp", currentCustomer ,"Please, enter your new information!");

    }

    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) {

        Customer currentCustomer = (Customer) request.getSession().getAttribute("customer");

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
                CustomerManager customerManager = new CustomerManager();
                Customer presentCustomerWthSuchLogin = customerManager.findCustomerByLogin(currentCustomer.getPhone());
                if ((presentCustomerWthSuchLogin != null)
                        && (presentCustomerWthSuchLogin.getCustomerId() != currentCustomer.getCustomerId())) {
                    message = "You can't use such phone! The customer with such phone already present!";
                }
                else{
                    customerManager.updateCustomer(currentCustomer);
                    message = "The data change has made (" + currentCustomer.getName() + ")";
                    registrationSuccessful = true;
                }
            } catch (SQLException e) {
                message = "Registration failed! Please try again!";
            }

        } else {
            message = "Please, input information in fields: " + message;
        }

        if (registrationSuccessful) {
            return new MVCModel("/customer/CustomerMenu.jsp", message);
        } else {
            return new MVCModel("/customer/CustomerEditProfile.jsp", null, message);
        }

    }

}
