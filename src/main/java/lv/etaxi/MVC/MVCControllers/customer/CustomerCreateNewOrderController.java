package lv.etaxi.MVC.MVCControllers.customer;

import lv.etaxi.MVC.MVCController;
import lv.etaxi.MVC.MVCModel;
import lv.etaxi.business.OrderManager;
import lv.etaxi.dto.CustomerDTO;
import lv.etaxi.dto.OrderDTO;
import lv.etaxi.dto.СonvertorDTO;
import lv.etaxi.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by D.Lazorkin on 31.03.2016.
 */

@Controller
public class CustomerCreateNewOrderController implements MVCController {

    @Autowired
    OrderManager orderManagerImpl;

    @Autowired
    СonvertorDTO convertorDTO;

    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) {

        CustomerDTO currentCustomerDTO = (CustomerDTO) request.getSession().getAttribute("customerDTO");
        if (currentCustomerDTO == null) {
            return new MVCModel("/customer/CustomerMenu.jsp", null, "");
        }

        return new MVCModel("/customer/CustomerNewOrder.jsp", "", "");
    }

    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) {

        CustomerDTO currentCustomerDTO = (CustomerDTO) request.getSession().getAttribute("customerDTO");
        if (currentCustomerDTO == null) {
            return new MVCModel("/customer/CustomerMenu.jsp", null, "");
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
        return new MVCModel("/customer/CustomerNewOrder.jsp", newOrderDTO, message);
    }

}
