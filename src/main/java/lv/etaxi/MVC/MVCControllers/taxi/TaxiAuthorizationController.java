package lv.etaxi.MVC.MVCControllers.taxi;

import lv.etaxi.MVC.MVCController;
import lv.etaxi.MVC.MVCModel;
import lv.etaxi.business.TaxiManager;
import lv.etaxi.dto.TaxiDTO;
import lv.etaxi.dto.СonvertorDTO;
import lv.etaxi.entity.Taxi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Created by Aleks on 01.04.2016.
 */
@Controller
public class TaxiAuthorizationController implements MVCController {

    @Autowired
    TaxiManager taxiManagerImpl;

    @Autowired
    СonvertorDTO convertorDTO;

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
            taxi = taxiManagerImpl.findByLogin(login);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (taxi == null || !taxi.getPassword().equals(password)) {
            return new MVCModel("/taxi/TaxiAuthorization.jsp", "");
        }

        // сохраняем логин (телефон) в сессию, для дальнейшей идентификации

        TaxiDTO currentTaxiDTO = convertorDTO.convertTaxiToDTO(taxi);
        request.getSession().setAttribute("taxi", currentTaxiDTO);

        return new MVCModel("/taxi/TaxiMenu.jsp", "");

    }

}
