package MVC.MVCControllers.customer;

import MVC.MVCController;
import MVC.MVCModel;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by D.Lazorkin on 31.03.2016.
 */
public class CustomerRegistrationController implements MVCController {

    @Override
    public MVCModel handleRequest(HttpServletRequest request) {

//        if (request.getMethod().equals("GET"))  {
            return new MVCModel("/customer/CustomerRegistration.jsp", "Please, enter information about new customer!");
//        }
//        else {
//            return  new MVCModel("/customer/CustomerRegistration.jsp", "");
//        }

    }
}
