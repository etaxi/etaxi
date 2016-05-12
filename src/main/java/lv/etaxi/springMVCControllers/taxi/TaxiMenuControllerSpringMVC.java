package lv.etaxi.springMVCControllers.taxi;

import lv.etaxi.MVC.MVCController;
import lv.etaxi.MVC.MVCModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Aleks on 01.04.2016.
 */
@Controller
public class TaxiMenuControllerSpringMVC {


    @RequestMapping(value = "/taxi", method = {RequestMethod.GET})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView modelAndView = new ModelAndView("/taxi/TaxiMenu", "model", null);
        return modelAndView;

    }

    @RequestMapping(value = "/taxi", method = {RequestMethod.POST})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("/taxi/TaxiMenu", "model", null);
        return modelAndView;

    }

}

