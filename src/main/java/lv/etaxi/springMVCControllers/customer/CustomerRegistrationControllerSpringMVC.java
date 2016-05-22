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
public class CustomerRegistrationControllerSpringMVC {

    @Autowired
    CustomerManager customerManagerImpl;

    @Autowired
    СonvertorDTO convertorDTO;


    @RequestMapping(value = "/customer/customerRegistration", method = {RequestMethod.GET})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) {

        return new ModelAndView("/customer/CustomerRegistration", "model",
                                new MVCModel(null, null, "Please, enter information about new customer!"));
    }


    @RequestMapping(value = "/customer/customerRegistration", method = {RequestMethod.POST})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) {

        Customer newCustomer = new Customer(
                (long)0,
                request.getParameter("name"),
                request.getParameter("phone"),
                request.getParameter("password"));

        String errorMessage = customerManagerImpl.create(newCustomer);
        CustomerDTO newCustomerDTO = convertorDTO.convertCustomerToDTO(newCustomer);

        if (errorMessage.isEmpty()) {
            request.getSession().setAttribute("customerDTO", newCustomerDTO);
            errorMessage = "Registration successful: " + newCustomerDTO.getName();
        }

        //return new ModelAndView("/customer/CustomerMenu", "model", new MVCModel(null, newCustomerDTO, errorMessage));
        return new ModelAndView("/index", "model", new MVCModel(null, newCustomerDTO, errorMessage));
    }

}
