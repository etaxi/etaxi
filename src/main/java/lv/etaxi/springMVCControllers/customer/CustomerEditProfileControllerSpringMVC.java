package lv.etaxi.springMVCControllers.customer;

import lv.etaxi.MVC.MVCModel;
import lv.etaxi.business.CustomerManager;
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
public class CustomerEditProfileControllerSpringMVC {

    @Autowired
    CustomerManager customerManagerImpl;

    @Autowired
    СonvertorDTO convertorDTO;

    @RequestMapping(value = "/customer/customerEditProfile", method = {RequestMethod.GET})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) {

        CustomerDTO currentCustomerDTO = (CustomerDTO) request.getSession().getAttribute("customerDTO");
        if (currentCustomerDTO == null) {
            return new ModelAndView("/customer/CustomerMenu", "model", new MVCModel(null, null, ""));
        }

        return new ModelAndView("/customer/CustomerEditProfile", "model",
                                 new MVCModel(null, currentCustomerDTO, "Please, enter your new information!"));
    }

    @RequestMapping(value = "/customer/customerEditProfile", method = {RequestMethod.POST})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) {

        CustomerDTO currentCustomerDTO = (CustomerDTO) request.getSession().getAttribute("customerDTO");
        if (currentCustomerDTO == null) {
            return new ModelAndView("/customer/CustomerMenu", "model", new MVCModel(null, null, ""));
        }

        currentCustomerDTO.setName(request.getParameter("name"));
        currentCustomerDTO.setPhone(request.getParameter("phone"));
        currentCustomerDTO.setPassword(request.getParameter("password"));

        Customer currentCustomer = convertorDTO.convertCustomerFromDTO(currentCustomerDTO);
        String errorMessage = customerManagerImpl.update(currentCustomer);

        if (errorMessage.isEmpty()) {
            errorMessage = "The data change was made (" + currentCustomerDTO.getName() + ")";
            return new ModelAndView("/customer/CustomerMenu", "model", new MVCModel(null, currentCustomerDTO, errorMessage));
        } else {
            return new ModelAndView("/customer/CustomerEditProfile", "model", new MVCModel(null, null, errorMessage));
        }
    }

}
