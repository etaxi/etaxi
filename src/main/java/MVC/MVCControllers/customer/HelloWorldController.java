package MVC.MVCControllers.customer;

import MVC.MVCController;
import MVC.MVCModel;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by D.Lazorkin on 31.03.2016.
 */
public class HelloWorldController implements MVCController {

    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) {

        return new MVCModel("/customer/Hello.jsp", "Hello MVC");

    }

    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) {

        return new MVCModel("/customer/Hello.jsp", "Hello MVC");

    }

}
