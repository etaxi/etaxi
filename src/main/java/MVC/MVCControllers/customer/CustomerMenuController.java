package MVC.MVCControllers.customer;

import MVC.MVCController;
import MVC.MVCModel;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by D.Lazorkin on 31.03.2016.
 */
@Component
public class CustomerMenuController implements MVCController {

    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) {

        return new MVCModel("/customer/CustomerMenu.jsp", null, "");

    }

    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) {

        return new MVCModel("/customer/CustomerMenu.jsp", null, "");

    }

}
