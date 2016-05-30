package lv.etaxi.springMVCControllers.taxi;

import lv.etaxi.business.TaxiManager;
import lv.etaxi.dto.TaxiDTO;
import lv.etaxi.dto.СonvertorDTO;
import lv.etaxi.entity.Taxi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
public class TaxiMenuControllerSpringMVC {

    @Autowired
    TaxiManager taxiManagerImpl;

    @Autowired
    СonvertorDTO convertorDTO;

    @RequestMapping(value = "/taxi", method = {RequestMethod.GET})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        Object userDetailsImpl = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = ((User) userDetailsImpl).getUsername();

        Taxi currentTaxi = taxiManagerImpl.findByLogin(userName);

        if (currentTaxi != null) {
            TaxiDTO currentTaxiDTO = convertorDTO.convertTaxiToDTO(currentTaxi);
            request.getSession().setAttribute("taxiDTO", currentTaxiDTO);
        }

        ModelAndView modelAndView = new ModelAndView("/taxi/TaxiMenu", "model", null);
        return modelAndView;
    }

    @RequestMapping(value = "/taxi", method = {RequestMethod.POST})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView modelAndView = new ModelAndView("/taxi/TaxiMenu", "model", null);
        return modelAndView;
    }

}

