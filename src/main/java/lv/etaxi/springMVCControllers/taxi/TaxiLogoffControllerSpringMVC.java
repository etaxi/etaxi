package lv.etaxi.springMVCControllers.taxi;

import lv.etaxi.MVC.MVCModel;
import lv.etaxi.entity.Order;
import lv.etaxi.entity.Taxi;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;


@Controller
public class TaxiLogoffControllerSpringMVC {

    @RequestMapping(value = "/taxi/logoff", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        if (request.getSession().getAttribute("taxi") != null) {
            request.getSession().removeAttribute("taxi");
            request.getSession().removeAttribute("orderId");
        }

        ModelAndView modelAndView = new ModelAndView("/taxi/TaxiMenu", "model", "");
        return modelAndView;
    }

}
