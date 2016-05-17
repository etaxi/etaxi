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
import java.util.List;


@Controller
public class TaxiHistoryControllerSpringMVC {

    @Autowired
    OrderManager orderManagerImpl;

    @Autowired
    СonvertorDTO convertorDTO;

    @RequestMapping(value = "/taxi/history", method = {RequestMethod.GET})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        TaxiDTO taxiDTO = (TaxiDTO)request.getSession().getAttribute("taxi");

        if (taxiDTO != null)
        {
            List<Order> listOfOrders = orderManagerImpl.getTaxiOrders(taxiDTO.getTaxiId());
            List<OrderDTO> listOfOrdersDTO = convertorDTO.convertOrderListToDTOList(listOfOrders);
            ModelAndView modelAndView = new ModelAndView("/taxi/TaxiHistory", "model", listOfOrdersDTO);
            return modelAndView;
        }
        else{
            ModelAndView modelAndView = new ModelAndView("/taxi/TaxiMenu", "model", "Only authorized users can see history");
            return modelAndView;
        }

    }

    @RequestMapping(value = "/taxi/history", method = {RequestMethod.POST})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }


}
