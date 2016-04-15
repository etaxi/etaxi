package lv.etaxi.MVC.MVCControllers.taxi;

import lv.etaxi.MVC.MVCController;
import lv.etaxi.MVC.MVCModel;
import lv.etaxi.business.managers.TaxiManagerImpl;
import lv.etaxi.entity.Taxi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Created by Aleks on 01.04.2016.
 */
@Controller
public class TaxiEditProfileController implements MVCController {

    @Autowired
    TaxiManagerImpl taxiManagerImpl;

    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) {
        Taxi currentTaxi = (Taxi) request.getSession().getAttribute("taxi");
        if (currentTaxi == null) {
            return new MVCModel("/taxi/TaxiMenu.jsp", "");
        }

        return new MVCModel("/taxi/TaxiEditProfile.jsp", currentTaxi, "Please, enter your new information!");

    }

    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) throws SQLException {
        Taxi currentTaxi = (Taxi) request.getSession().getAttribute("taxi");
        if (currentTaxi == null) {
            return new MVCModel("/taxi/TaxiMenu.jsp", "");
        }

        currentTaxi.setName(request.getParameter("name"));
        currentTaxi.setCar(request.getParameter("car"));
        currentTaxi.setPhone(request.getParameter("phone"));
        currentTaxi.setLogin(request.getParameter("login"));
        currentTaxi.setPassword(request.getParameter("password"));

        taxiManagerImpl.updateTaxi(currentTaxi);
        return new MVCModel("/taxi/TaxiMenu.jsp", "");

    }

}
