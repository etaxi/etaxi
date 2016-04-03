package MVC.MVCControllers.customer;

import MVC.MVCController;
import MVC.MVCModel;
import business.CustomerManager;
import entity.Customer;

import javax.servlet.http.HttpServletRequest;

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

        Customer newCustomer = new Customer(
                (long)0,
                request.getParameter("name"),
                request.getParameter("phone"),
                request.getParameter("password"));

        String message = "";
        Boolean registrationSuccessful = false;

        CustomerManager customerManager = new CustomerManager();
        if (!customerManager.checkCustomerByLogin(newCustomer)) {
            message = "You can't use such phone! The customer with such phone already present!";
        } else {
            registrationSuccessful = customerManager.createNewCustomer(newCustomer);
            message = (registrationSuccessful) ?
                    "Registration successful: " + newCustomer.getName() :
                    "Registration failed! Please try again!";
        }

        if (registrationSuccessful) {
            request.getSession().setAttribute("customer", newCustomer);
        }

        return new MVCModel("/customer/CustomerMenu.jsp", newCustomer, message);
    }

}
