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

@Controller
public class TaxiEditProfileControllerSpringMVC {

    @Autowired
    TaxiManager taxiManagerImpl;

    @Autowired
    СonvertorDTO convertorDTO;

    @RequestMapping(value = "/taxi/editprofile", method = {RequestMethod.GET})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) {

        TaxiDTO currentTaxiDTO = (TaxiDTO) request.getSession().getAttribute("taxi");
        if (currentTaxiDTO == null) {
            ModelAndView modelAndView = new ModelAndView("/taxi/TaxiMenu", "model", "");
            return modelAndView;
        }

        ModelAndView modelAndView = new ModelAndView("/taxi/TaxiEditProfile", "model", "Please, enter your new information!");
        return modelAndView;
    }


    @RequestMapping(value = "/taxi/editprofile", method = {RequestMethod.POST})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        TaxiDTO currentTaxiDTO = (TaxiDTO) request.getSession().getAttribute("taxi");
        if (currentTaxiDTO == null) {
            ModelAndView modelAndView = new ModelAndView("/taxi/TaxiMenu", "model", "");
            return modelAndView;
        }

        currentTaxiDTO.setName(request.getParameter("name"));
        currentTaxiDTO.setCar(request.getParameter("car"));
        currentTaxiDTO.setPhone(request.getParameter("phone"));
        currentTaxiDTO.setLogin(request.getParameter("login"));
        currentTaxiDTO.setPassword(request.getParameter("password"));

        Taxi currentTaxi = convertorDTO.convertTaxiFromDTO(currentTaxiDTO);
        taxiManagerImpl.update(currentTaxi);

            ModelAndView modelAndView = new ModelAndView("/taxi/TaxiMenu", "model", "");
            return modelAndView;
    }

}
