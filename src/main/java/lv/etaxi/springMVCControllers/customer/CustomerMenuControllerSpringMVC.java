package lv.etaxi.springMVCControllers.customer;

import lv.etaxi.MVC.MVCModel;
import lv.etaxi.business.CustomerManager;
import lv.etaxi.dto.CustomerDTO;
import lv.etaxi.dto.СonvertorDTO;
import lv.etaxi.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Created by D.Lazorkin on 11.05.2016.
 */

@Controller
public class CustomerMenuControllerSpringMVC  {

    @Autowired
    CustomerManager customerManagerImpl;

    @Autowired
    СonvertorDTO convertorDTO;

    @RequestMapping(value = "/customer", method = {RequestMethod.GET})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        Object userDetailsImpl = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = ((User) userDetailsImpl).getUsername();

        Customer currentCustomer = customerManagerImpl.findByLogin(userName);

        if (currentCustomer != null) {
            CustomerDTO currentCustomerDTO = convertorDTO.convertCustomerToDTO(currentCustomer);
            request.getSession().setAttribute("customerDTO", currentCustomerDTO);
        }

        return new ModelAndView("/customer/CustomerMenu", "model", new MVCModel(null, null, ""));
    }

}
