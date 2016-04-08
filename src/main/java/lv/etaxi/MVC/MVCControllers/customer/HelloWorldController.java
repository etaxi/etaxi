package lv.etaxi.MVC.MVCControllers.customer;


import lv.etaxi.MVC.MVCController;
import lv.etaxi.MVC.MVCModel;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@Component
public class HelloWorldController implements MVCController {

    public MVCModel processRequest(HttpServletRequest req) {
        return new MVCModel("/customer/hello.jsp", "Hello MVC");
    }

    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) throws SQLException {
        return processRequest(request);
    }

    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) throws SQLException {
        return processRequest(request);
    }
}
