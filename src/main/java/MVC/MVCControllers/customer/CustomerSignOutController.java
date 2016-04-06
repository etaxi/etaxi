package MVC.MVCControllers.customer;

import MVC.MVCController;
import MVC.MVCModel;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by D.Lazorkin on 31.03.2016.
 */
public class CustomerSignOutController implements MVCController {

    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) {

        return signOutUser(request);
    }

    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) {

        return signOutUser(request);
    }

    private MVCModel signOutUser(HttpServletRequest request) {

        if (request.getSession().getAttribute("customer") != null) {
            request.getSession().removeAttribute("customer");
        }
        return new MVCModel("/customer/CustomerMenu.jsp", null, "");
    }
}