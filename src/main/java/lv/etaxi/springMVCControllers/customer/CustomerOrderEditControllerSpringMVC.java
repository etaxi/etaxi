package lv.etaxi.springMVCControllers.customer;

import lv.etaxi.MVC.MVCModel;
import lv.etaxi.business.OrderManager;
import lv.etaxi.dto.CustomerDTO;
import lv.etaxi.dto.OrderDTO;
import lv.etaxi.dto.СonvertorDTO;
import lv.etaxi.entity.Customer;
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
public class CustomerOrderEditControllerSpringMVC {

    @Autowired
    OrderManager orderManagerImpl;

    @Autowired
    СonvertorDTO convertorDTO;


    @RequestMapping(value = "/customer/customerEditOrder", method = {RequestMethod.GET})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) {

        CustomerDTO currentCustomerDTO = (CustomerDTO) request.getSession().getAttribute("customerDTO");
        if (currentCustomerDTO == null) {
            return new ModelAndView("/customer/CustomerMenu", "model", new MVCModel(null, null, ""));
        }

        String orderId = request.getParameter("orderId");
        Order currentOrder = orderManagerImpl.findById(orderId);
        OrderDTO currentOrderDTO = convertorDTO.convertOrderToDTO(currentOrder);

        Customer currentCustomer = convertorDTO.convertCustomerFromDTO(currentCustomerDTO);

        return  orderManagerImpl.checkChangePossibility(currentCustomer, currentOrder) ?
                new ModelAndView("/customer/CustomerEditOrder", "model", new MVCModel(null, currentOrderDTO, "")) :
                new ModelAndView("/customer/CustomerMenu", "model", new MVCModel(null, null, ""));

    }

    @RequestMapping(value = "/customer/customerEditOrder", method = {RequestMethod.POST})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) {

        CustomerDTO currentCustomerDTO = (CustomerDTO) request.getSession().getAttribute("customerDTO");
        if (currentCustomerDTO == null) {
            return new ModelAndView("/customer/CustomerMenu", "model", new MVCModel(null, null, ""));
        }

        Boolean updateSuccessful = orderManagerImpl.updateOrderByIdByCustomer(
                convertorDTO.convertCustomerFromDTO(currentCustomerDTO),
                request.getParameter("orderId"),
                request.getParameter("fromAddress"),
                request.getParameter("toAddress"),
                request.getParameter("orderedDateTime"),
                "",
                Double.valueOf(request.getParameter("distance")),
                Double.valueOf(request.getParameter("price")));

        String message = (updateSuccessful) ?
                "Order ID: " + request.getParameter("orderId") + " was changed!" :
                "Order information update failed! Please try again!";

        if (updateSuccessful) {
            return new ModelAndView("/customer/CustomerEditDeleteOrders", "model", new MVCModel(null, null, message));
        } else {
            Order currentOrder = orderManagerImpl.findById(request.getParameter("orderId"));
            OrderDTO currentOrderDTO = convertorDTO.convertOrderToDTO(currentOrder);
            return new ModelAndView("/customer/CustomerEditOrder", "model", new MVCModel(null, currentOrderDTO, message));
        }
    }

}
