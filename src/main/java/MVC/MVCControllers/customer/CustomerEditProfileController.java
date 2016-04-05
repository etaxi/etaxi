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

        String errorMessage = new CustomerManager().updateCustomer(currentCustomer);

        if (errorMessage.isEmpty()) {
            errorMessage = "The data change was made (" + currentCustomer.getName() + ")";
            return new MVCModel("/customer/CustomerMenu.jsp", currentCustomer, errorMessage);
        } else {
            return new MVCModel("/customer/CustomerEditProfile.jsp", null, errorMessage);
        }

    }

}
