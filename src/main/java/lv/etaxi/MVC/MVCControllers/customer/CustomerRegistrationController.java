package lv.etaxi.MVC.MVCControllers.customer;

import lv.etaxi.MVC.MVCController;
import lv.etaxi.MVC.MVCModel;
import lv.etaxi.business.CustomerManagerImpl;
import lv.etaxi.entity.Customer;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by D.Lazorkin on 31.03.2016.
 */

@Component
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

        String errorMessage = new CustomerManagerImpl().createNewCustomer(newCustomer);

        if (errorMessage.isEmpty()) {
            request.getSession().setAttribute("customer", newCustomer);
            errorMessage = "Registration successful: " + newCustomer.getName();
        }

        return new MVCModel("/customer/CustomerMenu.jsp", newCustomer, errorMessage);
    }

}