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
public class CustomerAuthorizationController implements MVCController {

    @Autowired
    CustomerManager customerManagerImpl;

    @Autowired
    СonvertorDTO convertorDTO;

    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) {

        return new MVCModel("/customer/CustomerAuthorization.jsp", null, "");
    }

    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) {

        Customer currentCustomer = customerManagerImpl.CheckAuthorization(
                request.getParameter("login"),
                request.getParameter("password"));

        if (currentCustomer != null) {
            // сохраняем пользователя в сессию, для дальнейшей идентификации клиента в системе
            CustomerDTO currentCustomerDTO = convertorDTO.convertCustomerToDTO(currentCustomer);
            request.getSession().setAttribute("customerDTO", currentCustomerDTO);
            return new MVCModel("/customer/CustomerMenu.jsp", currentCustomerDTO, "Authorization successful: " + currentCustomerDTO.getName());
        }
        else {
            return  new MVCModel("/customer/CustomerMenu.jsp", null, "Wrong login or password!");
        }
    }

}
