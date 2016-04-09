package lv.etaxi.MVC.MVCControllers.taxi;

import lv.etaxi.MVC.MVCController;
import lv.etaxi.MVC.MVCModel;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Aleks on 01.04.2016.
 */
@Controller
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

