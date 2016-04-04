package MVC.MVCControllers.customer;

import MVC.MVCController;
import MVC.MVCModel;
import business.CustomerManager;
import entity.Customer;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by D.Lazorkin on 31.03.2016.
 */
public class CustomerEditProfileController implements MVCController {

    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) {

        Customer currentCustomer = (Customer) request.getSession().getAttribute("customer");
        if (currentCustomer == null) {
            return new MVCModel("/customer/CustomerMenu.jsp", null, "");
        }

        return new MVCModel("/customer/CustomerEditProfile.jsp", currentCustomer, "Please, enter your new information!");
    }

    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) {

        Customer currentCustomer = (Customer) request.getSession().getAttribute("customer");
        if (currentCustomer == null) {
            return new MVCModel("/customer/CustomerMenu.jsp", null, "");
        }

        currentCustomer.setName(request.getParameter("name"));
        currentCustomer.setPhone(request.getParameter("phone"));
        currentCustomer.setPassword(request.getParameter("password"));

        String message = "";
        Boolean updateSuccessful = false;

        CustomerManager customerManager = new CustomerManager();
        if (!customerManager.checkCustomerByLogin(currentCustomer)) {
            message = "You can't use such phone! The customer with such phone already present!";
        } else {
            updateSuccessful = customerManager.updateCustomer(currentCustomer);
            message =  (updateSuccessful) ?
                       "The data change was made (" + currentCustomer.getName() + ")" :
                       "Registration failed! Please try again!";
        }

        if (updateSuccessful) {
            return new MVCModel("/customer/CustomerMenu.jsp", currentCustomer, message);
        } else {
            return new MVCModel("/customer/CustomerEditProfile.jsp", null, message);
        }

    }

}
