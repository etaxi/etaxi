package lv.etaxi.springMVCControllers.taxi;

import lv.etaxi.MVC.MVCModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;


@Controller
public class TaxiLogoffControllerSpringMVC {

    @RequestMapping(value = "/taxi/logoff", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        if (request.getSession().getAttribute("taxi") != null) {
            request.getSession().removeAttribute("taxi");
            request.getSession().removeAttribute("orderId");
        }

//        ModelAndView modelAndView = new ModelAndView("/index", "model", "");
//        return modelAndView;
        return new ModelAndView("/index", "model", new MVCModel(null, null, ""));
    }

}
