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
public class CustomerSignOutControllerSpringMVC {

    @RequestMapping(value = "/customer/signOut", method = {RequestMethod.GET})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) {

        return signOutUser(request);
    }

    @RequestMapping(value = "/customer/signOut", method = {RequestMethod.POST})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) {

        return signOutUser(request);
    }

    private ModelAndView signOutUser(HttpServletRequest request) {

        if (request.getSession().getAttribute("customerDTO") != null) {
            request.getSession().removeAttribute("customerDTO");
        }
        return new ModelAndView("/customer/CustomerMenu", "model", new MVCModel(null, null, ""));
    }
}
