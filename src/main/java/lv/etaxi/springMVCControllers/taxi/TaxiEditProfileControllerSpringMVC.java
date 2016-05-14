package lv.etaxi.springMVCControllers.taxi;

import lv.etaxi.business.TaxiManager;
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

    @RequestMapping(value = "/taxi/editprofile", method = {RequestMethod.GET})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) {

        Taxi currentTaxi = (Taxi) request.getSession().getAttribute("taxi");
        if (currentTaxi == null) {
            ModelAndView modelAndView = new ModelAndView("/taxi/TaxiMenu", "model", "");
            return modelAndView;
        }

        ModelAndView modelAndView = new ModelAndView("/taxi/TaxiEditProfile", "model", "Please, enter your new information!");
        return modelAndView;
    }


    @RequestMapping(value = "/taxi/editprofile", method = {RequestMethod.POST})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        Taxi currentTaxi = (Taxi) request.getSession().getAttribute("taxi");
        if (currentTaxi == null) {
            ModelAndView modelAndView = new ModelAndView("/taxi/TaxiMenu", "model", "");
            return modelAndView;
        }

        currentTaxi.setName(request.getParameter("name"));
        currentTaxi.setCar(request.getParameter("car"));
        currentTaxi.setPhone(request.getParameter("phone"));
        currentTaxi.setLogin(request.getParameter("login"));
        currentTaxi.setPassword(request.getParameter("password"));

        taxiManagerImpl.update(currentTaxi);

            ModelAndView modelAndView = new ModelAndView("/taxi/TaxiMenu", "model", "");
            return modelAndView;
    }

}
