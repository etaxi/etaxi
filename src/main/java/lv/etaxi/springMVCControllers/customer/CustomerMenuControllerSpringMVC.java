package lv.etaxi.springMVCControllers.customer;

import lv.etaxi.MVC.MVCModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by D.Lazorkin on 11.05.2016.
 */

@Controller
public class CustomerMenuControllerSpringMVC  {

    @RequestMapping(value = "/customer", method = {RequestMethod.GET})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) {

        return new ModelAndView("/customer/CustomerMenu", "model", new MVCModel(null, null, ""));
    }

    @RequestMapping(value = "/customer", method = {RequestMethod.POST})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response)  {

        return new ModelAndView("/customer/CustomerMenu", "model", new MVCModel(null, null, ""));
    }

}
