package lv.etaxi.springMVCControllers.taxi;

import lv.etaxi.business.TaxiManager;
import lv.etaxi.dto.TaxiDTO;
import lv.etaxi.dto.СonvertorDTO;
import lv.etaxi.entity.Taxi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Created by Aleks on 01.04.2016.
 */
@Controller
public class TaxiAuthorizationControllerSpringMVC {

    @Autowired
    TaxiManager taxiManagerImpl;

    @Autowired
    СonvertorDTO convertorDTO;

    @RequestMapping(value = "/taxi/authorization", method = {RequestMethod.GET})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView modelAndView = new ModelAndView("/taxi/TaxiAuthorization", "model", null);
        return modelAndView;

    }

    @RequestMapping(value = "/taxi/authorization", method = {RequestMethod.POST})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null || password == null) {
            ModelAndView modelAndView = new ModelAndView("/taxi/TaxiAuthorization", "model", null);
            return modelAndView;
        }

        Taxi taxi = null;
        try {
            taxi = taxiManagerImpl.findByLogin(login);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (taxi == null || !taxi.getPassword().equals(password)) {
            ModelAndView modelAndView = new ModelAndView("/taxi/TaxiAuthorization", "model", null);
            return modelAndView;
        }

        // сохраняем логин в сессию, для дальнейшей идентификации
        TaxiDTO currentTaxiDTO = convertorDTO.convertTaxiToDTO(taxi);
        request.getSession().setAttribute("taxi", currentTaxiDTO);

        ModelAndView modelAndView = new ModelAndView("/taxi/TaxiMenu", "model", null);
        return modelAndView;

    }

}
