package lv.etaxi.springMVCControllers.customer;

import lv.etaxi.MVC.MVCModel;
import lv.etaxi.business.OrderManager;
import lv.etaxi.dto.CustomerDTO;
import lv.etaxi.dto.СonvertorDTO;
import lv.etaxi.entity.Customer;
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
public class CustomerOrderDeleteControllerSpringMVC {

    @Autowired
    OrderManager orderManagerImpl;

    @Autowired
    СonvertorDTO convertorDTO;

    @RequestMapping(value = "/customer/customerDeleteOrder", method = {RequestMethod.GET})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) {

        return new ModelAndView("/customer/CustomerMenu", "model", new MVCModel(null, null, ""));
    }

    @RequestMapping(value = "/customer/customerDeleteOrder", method = {RequestMethod.POST})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) {

        CustomerDTO currentCustomerDTO = (CustomerDTO) request.getSession().getAttribute("customerDTO");
        if (currentCustomerDTO == null) {
            return new ModelAndView("/customer/CustomerMenu", "model", new MVCModel(null, null, ""));
        }

        String orderIdToDelete = request.getParameter("orderId");

        Customer currentCustomer = convertorDTO.convertCustomerFromDTO(currentCustomerDTO);

        String message = (orderManagerImpl.deleteOrderByIdByCustomer(currentCustomer, orderIdToDelete)) ?
                         "Your order (ID: " + orderIdToDelete + ") was deleted!" :
                         "Order information delete failed! Please try again!";

        return new ModelAndView("/customer/CustomerEditDeleteOrders", "model", new MVCModel(null, null, message));
    }

}
