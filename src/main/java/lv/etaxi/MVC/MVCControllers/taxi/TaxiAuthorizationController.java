package lv.etaxi.MVC.MVCControllers.taxi;

import lv.etaxi.MVC.MVCController;
import lv.etaxi.MVC.MVCModel;
import lv.etaxi.business.TaxiManagerImpl;
import lv.etaxi.entity.Taxi;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Created by Aleks on 01.04.2016.
 */
@Component
public class TaxiAuthorizationController implements MVCController {

    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) {
        return new MVCModel("/taxi/TaxiAuthorization.jsp", "");
    }


    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null || password == null) {
            return new MVCModel("/taxi/TaxiAuthorization.jsp", "");
        }

        Taxi taxi = null;
        try {
            taxi = new TaxiManagerImpl().findTaxiByLogin(login);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (taxi == null || !taxi.getPassword().equals(password)) {
            return new MVCModel("/taxi/TaxiAuthorization.jsp", "");
        }

        // сохраняем логин (телефон) в сессию, для дальнейшей идентификации
        request.getSession().setAttribute("taxi", taxi);

        return new MVCModel("/taxi/TaxiMenu.jsp", "");

    }

}
