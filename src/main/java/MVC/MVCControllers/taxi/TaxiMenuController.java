package MVC.MVCControllers.taxi;

import MVC.MVCController;
import MVC.MVCModel;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Aleks on 01.04.2016.
 */
@Component
public class TaxiMenuController implements MVCController {

    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) {

        return new MVCModel("/taxi/TaxiMenu.jsp", "");

    }

    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) {

        return new MVCModel("/taxi/TaxiMenu.jsp", "");

    }


}

