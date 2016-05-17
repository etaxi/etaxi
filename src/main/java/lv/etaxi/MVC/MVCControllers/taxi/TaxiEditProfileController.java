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
public class TaxiEditProfileController implements MVCController {

    @Autowired
    TaxiManager taxiManagerImpl;

    @Autowired
    СonvertorDTO convertorDTO;

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
        TaxiDTO currentTaxiDTO = (TaxiDTO) request.getSession().getAttribute("taxi");
        if (currentTaxiDTO == null) {
            return new MVCModel("/taxi/TaxiMenu.jsp", "");
        }

        currentTaxiDTO.setName(request.getParameter("name"));
        currentTaxiDTO.setCar(request.getParameter("car"));
        currentTaxiDTO.setPhone(request.getParameter("phone"));
        currentTaxiDTO.setLogin(request.getParameter("login"));
        currentTaxiDTO.setPassword(request.getParameter("password"));

        Taxi currentTaxi = convertorDTO.convertTaxiFromDTO(currentTaxiDTO);
        taxiManagerImpl.update(currentTaxi);
        return new MVCModel("/taxi/TaxiMenu.jsp", "");

    }

}
