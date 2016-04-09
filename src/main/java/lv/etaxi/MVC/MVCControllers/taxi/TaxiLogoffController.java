package lv.etaxi.MVC.MVCControllers.taxi;

import lv.etaxi.MVC.MVCController;
import lv.etaxi.MVC.MVCModel;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Aleks on 01.04.2016.
 */

public class TaxiLogoffController implements MVCController {
    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) {

        if (request.getSession().getAttribute("taxi") != null) {
            request.getSession().removeAttribute("taxi");
            request.getSession().removeAttribute("orderId");
        }

        return new MVCModel("/taxi/TaxiMenu.jsp", "");

    }

    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) {

        if (request.getSession().getAttribute("taxi") != null) {
            request.getSession().removeAttribute("taxi");
            request.getSession().removeAttribute("orderId");
        }

        return new MVCModel("/taxi/TaxiMenu.jsp", "");

    }
}
