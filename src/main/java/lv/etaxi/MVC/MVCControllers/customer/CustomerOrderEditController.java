package lv.etaxi.MVC.MVCControllers.customer;

import lv.etaxi.MVC.MVCController;
import lv.etaxi.MVC.MVCModel;
import lv.etaxi.business.OrderManager;
import lv.etaxi.dto.CustomerDTO;
import lv.etaxi.dto.OrderDTO;
import lv.etaxi.dto.СonvertorDTO;
import lv.etaxi.entity.Customer;
import lv.etaxi.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by D.Lazorkin on 02.04.2016.
 */

@Controller
public class CustomerOrderEditController implements MVCController {

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

        String orderId = request.getParameter("orderId");
        Order currentOrder = orderManagerImpl.findById(orderId);

        Customer currentCustomer = convertorDTO.convertCustomerFromDTO(currentCustomerDTO);

        return  orderManagerImpl.checkChangePossibility(currentCustomer, currentOrder) ?
                new MVCModel("/customer/CustomerEditOrder.jsp", currentOrder, "") :
                new MVCModel("/customer/CustomerMenu.jsp", null, "");

    }

    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) {

        CustomerDTO currentCustomerDTO = (CustomerDTO) request.getSession().getAttribute("customerDTO");
        if (currentCustomerDTO == null) {
            return new MVCModel("/customer/CustomerMenu.jsp", null, "");
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
            return new MVCModel("/customer/CustomerEditDeleteOrders.jsp", null, message);
        } else {
            Order currentOrder = orderManagerImpl.findById(request.getParameter("orderId"));
            OrderDTO currentOrderDTO = convertorDTO.convertOrderToDTO(currentOrder);
            return new MVCModel("/customer/CustomerEditOrder.jsp", currentOrderDTO, message);
        }
    }

}
