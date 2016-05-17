package lv.etaxi.springMVCControllers.taxi;

import lv.etaxi.business.OrderManager;
import lv.etaxi.dto.OrderDTO;
import lv.etaxi.dto.СonvertorDTO;
import lv.etaxi.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@Controller
public class TaxiCompleteOrderControllerSpringMVC{

    @Autowired
    OrderManager orderManagerImpl;

    @Autowired
    СonvertorDTO convertorDTO;

    @RequestMapping(value = "/taxi/completeorder", method = {RequestMethod.GET})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        if (request.getSession().getAttribute("taxi") != null) {

            if (request.getSession().getAttribute("order") == null){
                ModelAndView modelAndView = new ModelAndView("/taxi/TaxiCancelOrder", "model", "");
                return modelAndView;
            }

            OrderDTO orderDTO = (OrderDTO) request.getSession().getAttribute("order");

            try {
                Order order = convertorDTO.convertOrderFromDTO(orderDTO);
                order.setOrderStatus(Order.OrderStatus.DELIVERED);
                orderManagerImpl.update(order);
                request.getSession().removeAttribute("order");

                ModelAndView modelAndView = new ModelAndView("/taxi/TaxiCompleteOrder", "model", "");
                return modelAndView;

            } catch (SQLException e) {
                e.printStackTrace();
                ModelAndView modelAndView = new ModelAndView("/taxi/TaxiMenu", "model", "");
                return modelAndView;
            }

        }
        else {
            ModelAndView modelAndView = new ModelAndView("/taxi/TaxiMenu", "model", "");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/taxi/completeorder", method = {RequestMethod.POST})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

}
