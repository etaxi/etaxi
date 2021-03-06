package lv.etaxi.springMVCControllers.taxi;

import lv.etaxi.MVC.MVCModel;
import lv.etaxi.business.OrderManager;
import lv.etaxi.dto.OrderDTO;
import lv.etaxi.dto.TaxiDTO;
import lv.etaxi.dto.СonvertorDTO;
import lv.etaxi.entity.Order;
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
public class TaxiTakeOrderControllerSpringMVC{

    @Autowired
    OrderManager orderManagerImpl;

    @Autowired
    СonvertorDTO convertorDTO;

    @RequestMapping(value = "/taxi/takeorder", method = {RequestMethod.GET})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @RequestMapping(value = "/taxi/takeorder", method = {RequestMethod.POST})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        if (request.getSession().getAttribute("taxi") != null) {
            String orderId     = request.getParameter("orderId");

            try {
                Order order = orderManagerImpl.findById(Long.parseLong(orderId));
                TaxiDTO taxiDTO = (TaxiDTO) request.getSession().getAttribute("taxi");
                order.setTaxiId(taxiDTO.getTaxiId());
                order.setOrderStatus(Order.OrderStatus.TAKEN);
                orderManagerImpl.update(order);
                OrderDTO orderDTO = convertorDTO.convertOrderToDTO(order);
                request.getSession().setAttribute("order", orderDTO);

                ModelAndView modelAndView = new ModelAndView("/taxi/TaxiTakeOrder", "model", "");
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

}
