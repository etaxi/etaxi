package MVC.MVCControllers.customer;

import MVC.MVCController;
import MVC.MVCModel;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by D.Lazorkin on 31.03.2016.
 */
public class StartAsCustomerController implements MVCController {

    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) {

        if (request.getSession().getAttribute("customerId") == null) {
            return new MVCModel("/customer/CustomerAuthorization.jsp", "");
        } else {
            return new MVCModel("/customer/CustomerMenu.jsp", "");
        }

    }


    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) {

        if (request.getSession().getAttribute("customerId") == null) {
            return new MVCModel("/customer/CustomerAuthorization.jsp", "");
        } else {
            return new MVCModel("/customer/CustomerMenu.jsp", "");
        }

    }

}
