package MVC.MVCControllers.taxi;

import MVC.MVCController;
import MVC.MVCModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
