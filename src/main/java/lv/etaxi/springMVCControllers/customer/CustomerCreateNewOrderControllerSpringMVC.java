package lv.etaxi.springMVCControllers.customer;

import lv.etaxi.MVC.MVCModel;
import lv.etaxi.business.OrderManager;
import lv.etaxi.dto.CustomerDTO;
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

/**
 * Created by D.Lazorkin on 11.05.2016.
 */

@Controller
public class CustomerCreateNewOrderControllerSpringMVC  {

    @Autowired
    OrderManager orderManagerImpl;

    @Autowired
    СonvertorDTO convertorDTO;

    @RequestMapping(value = "/customer/customerCreateNewOrder", method = {RequestMethod.GET})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) {

        CustomerDTO currentCustomerDTO = (CustomerDTO) request.getSession().getAttribute("customerDTO");
        if (currentCustomerDTO == null) {
            return new ModelAndView("/customer/CustomerMenu", "model", new MVCModel(null, null, ""));
        }

        return new ModelAndView("/customer/CustomerNewOrder", "model", new MVCModel(null, null, ""));
    }


    @RequestMapping(value = "/customer/customerCreateNewOrder", method = {RequestMethod.POST})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) {

        CustomerDTO currentCustomerDTO = (CustomerDTO) request.getSession().getAttribute("customerDTO");
        if (currentCustomerDTO == null) {
            return new ModelAndView("/customer/CustomerMenu", "model", new MVCModel(null, null, ""));
        }

        Order newOrder = orderManagerImpl.createNewInDataBase(
                convertorDTO.convertCustomerFromDTO(currentCustomerDTO),
                request.getParameter("fromAddress"),
                request.getParameter("toAddress"),
                request.getParameter("orderedDateTime"),
                request.getParameter("distance"));

        String message = (newOrder != null) ?
                          "New order was created (new order ID: " + newOrder.getOrderId() + ")" :
                          "New order creation failed! Please try again!";

        OrderDTO newOrderDTO = convertorDTO.convertOrderToDTO(newOrder);
        return new ModelAndView("/customer/CustomerNewOrder", "model", new MVCModel(null, newOrderDTO, message));
    }
}
