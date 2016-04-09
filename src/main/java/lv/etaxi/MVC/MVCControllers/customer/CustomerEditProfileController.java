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

        String errorMessage = new CustomerManagerImpl().updateCustomer(currentCustomer);

        if (errorMessage.isEmpty()) {
            errorMessage = "The data change was made (" + currentCustomer.getName() + ")";
            return new MVCModel("/customer/CustomerMenu.jsp", currentCustomer, errorMessage);
        } else {
            return new MVCModel("/customer/CustomerEditProfile.jsp", null, errorMessage);
        }

    }

}