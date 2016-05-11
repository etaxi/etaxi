package lv.etaxi.MVC.MVCControllers.customer;

import lv.etaxi.MVC.MVCController;
import lv.etaxi.MVC.MVCModel;
import lv.etaxi.business.CustomerManager;
import lv.etaxi.dto.CustomerDTO;
import lv.etaxi.dto.СonvertorDTO;
import lv.etaxi.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by D.Lazorkin on 31.03.2016.
 */

@Controller
public class CustomerEditProfileController implements MVCController {

    @Autowired
    CustomerManager customerManagerImpl;

    @Autowired
    СonvertorDTO convertorDTO;

    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) {

        CustomerDTO currentCustomerDTO = (CustomerDTO) request.getSession().getAttribute("customerDTO");
        if (currentCustomerDTO == null) {
            return new MVCModel("/customer/CustomerMenu.jsp", null, "");
        }

        return new MVCModel("/customer/CustomerEditProfile.jsp", currentCustomerDTO, "Please, enter your new information!");
    }

    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) {

        CustomerDTO currentCustomerDTO = (CustomerDTO) request.getSession().getAttribute("customerDTO");
        if (currentCustomerDTO == null) {
            return new MVCModel("/customer/CustomerMenu.jsp", null, "");
        }

        currentCustomerDTO.setName(request.getParameter("name"));
        currentCustomerDTO.setPhone(request.getParameter("phone"));
        currentCustomerDTO.setPassword(request.getParameter("password"));

        Customer currentCustomer = convertorDTO.convertCustomerFromDTO(currentCustomerDTO);
        String errorMessage = customerManagerImpl.update(currentCustomer);

        if (errorMessage.isEmpty()) {
            errorMessage = "The data change was made (" + currentCustomerDTO.getName() + ")";
            return new MVCModel("/customer/CustomerMenu.jsp", currentCustomerDTO, errorMessage);
        } else {
            return new MVCModel("/customer/CustomerEditProfile.jsp", null, errorMessage);
        }

    }

}
