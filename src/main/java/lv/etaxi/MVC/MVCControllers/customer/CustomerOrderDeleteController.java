package lv.etaxi.MVC.MVCControllers.customer;

import lv.etaxi.MVC.MVCController;
import lv.etaxi.MVC.MVCModel;
import lv.etaxi.business.OrderManager;
import lv.etaxi.dto.CustomerDTO;
import lv.etaxi.dto.СonvertorDTO;
import lv.etaxi.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by D.Lazorkin on 02.04.2016.
 */
@Controller
public class CustomerOrderDeleteController implements MVCController {

    @Autowired
    OrderManager orderManagerImpl;

    @Autowired
    СonvertorDTO convertorDTO;

    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) {

        return new MVCModel("/customer/CustomerMenu.jsp", null, "");
    }

    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) {

        CustomerDTO currentCustomerDTO = (CustomerDTO) request.getSession().getAttribute("customerDTO");
        if (currentCustomerDTO == null) {
            return new MVCModel("/customer/CustomerMenu.jsp", null, "");
        }

        String orderIdToDelete = request.getParameter("orderId");

        Customer currentCustomer = convertorDTO.convertCustomerFromDTO(currentCustomerDTO);

        String message = (orderManagerImpl.deleteOrderByIdByCustomer(currentCustomer, orderIdToDelete)) ?
                         "Your order (ID: " + orderIdToDelete + ") was deleted!" :
                         "Order information delete failed! Please try again!";

        return new MVCModel("/customer/CustomerEditDeleteOrders.jsp", null, message);
    }

}
