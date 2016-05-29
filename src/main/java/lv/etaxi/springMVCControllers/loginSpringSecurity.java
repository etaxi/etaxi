package lv.etaxi.springMVCControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginSpringSecurity {

    @RequestMapping(value = "/loginSpringSecurity", method = {RequestMethod.GET})
    public ModelAndView processRequest(HttpServletRequest request) {

        return new ModelAndView("/loginSpringSecurity", "model", null);
    }
}