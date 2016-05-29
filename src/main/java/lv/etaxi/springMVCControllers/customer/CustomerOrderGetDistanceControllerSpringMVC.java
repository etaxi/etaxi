package lv.etaxi.springMVCControllers.customer;

import lv.etaxi.MVC.MVCModel;
import lv.etaxi.business.OrderManager;
import lv.etaxi.dto.CustomerDTO;
import lv.etaxi.dto.OrderDTO;
import lv.etaxi.dto.СonvertorDTO;
import lv.etaxi.entity.Order;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

/**
 * Created by D.Lazorkin on 11.05.2016.
 */

@Controller
public class CustomerOrderGetDistanceControllerSpringMVC {

    @Autowired
    OrderManager orderManagerImpl;

    @Autowired
    СonvertorDTO convertorDTO;

    @RequestMapping(value = "/customer/getDistanceForOrder", method = {RequestMethod.GET})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) {

        return new ModelAndView("/customer/CustomerMenu", "model", new MVCModel(null, null,""));
    }

    @RequestMapping(value = "/customer/getDistanceForOrder", method = {RequestMethod.POST})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) throws JSONException {

        CustomerDTO currentCustomerDTO = (CustomerDTO) request.getSession().getAttribute("customerDTO");
        if (currentCustomerDTO == null) {
            return new ModelAndView("/customer/CustomerMenu", "model", new MVCModel(null, null,""));
        }

        Order currentOrder = null;
        try {
            currentOrder = orderManagerImpl.findById(request.getParameter("orderId"));
        } catch (Exception e) {
            currentOrder = new Order();
            currentOrder.setFromAdress(request.getParameter("fromAddress"));
            currentOrder.setToAdress(request.getParameter("toAddress"));
            currentOrder.setOrderedDateTime(Timestamp.valueOf(request.getParameter("orderedDateTime")));
        }

        currentOrder.setDistance(orderManagerImpl.GetDistance(request.getParameter("fromAddress"), request.getParameter("toAddress")));
        currentOrder.setPrice(orderManagerImpl.getPriceOfRide(currentOrder.getDistance()));

        OrderDTO currentOrderDTO = convertorDTO.convertOrderToDTO(currentOrder);

        return new ModelAndView(request.getParameter("returnPage").replaceAll(".jsp",""), "model", new MVCModel(null, currentOrderDTO, ""));
    }
}
