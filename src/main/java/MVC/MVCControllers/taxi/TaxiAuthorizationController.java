package MVC.MVCControllers.taxi;

import MVC.MVCController;
import MVC.MVCModel;
import business.TaxiManager;
import entity.Taxi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Created by Aleks on 01.04.2016.
 */
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
            taxi = new TaxiManager().findTaxiByLogin(login);

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
