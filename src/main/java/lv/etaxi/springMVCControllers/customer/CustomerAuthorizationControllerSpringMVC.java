package lv.etaxi.springMVCControllers.customer;

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
public class CustomerAuthorizationControllerSpringMVC {

    @Autowired
    CustomerManager customerManagerImpl;

    @Autowired
    СonvertorDTO convertorDTO;

    @RequestMapping(value = "/customer/customerAuthorization", method = {RequestMethod.GET})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) {

        return new ModelAndView("/customer/CustomerAuthorization", "model", null);
    }


    @RequestMapping(value = "/customer/customerAuthorization", method = {RequestMethod.POST})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) {

        Customer currentCustomer = customerManagerImpl.CheckAuthorization(
                request.getParameter("login"),
                request.getParameter("password"));

        if (currentCustomer != null) {
            // сохраняем пользователя в сессию, для дальнейшей идентификации клиента в системе
            CustomerDTO currentCustomerDTO = convertorDTO.convertCustomerToDTO(currentCustomer);
            request.getSession().setAttribute("customerDTO", currentCustomerDTO);

            return new ModelAndView("/customer/CustomerMenu", "model",  currentCustomerDTO);
        }
        else {
            return new ModelAndView("/customer/CustomerMenu", "model",  null);
        }
    }

}
