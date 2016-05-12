package lv.etaxi.springMVCControllers.customer;

import lv.etaxi.MVC.MVCModel;
import lv.etaxi.business.OrderManager;
import lv.etaxi.dto.CustomerDTO;
import lv.etaxi.dto.OrderDTO;
import lv.etaxi.dto.СonvertorDTO;
import lv.etaxi.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by D.Lazorkin on 11.05.2016.
 */
@Controller
public class CustomerOrdersEditDeleteControllerSpringMVC {

    @Autowired
    OrderManager orderManagerImpl;

    @Autowired
    СonvertorDTO convertorDTO;

    @RequestMapping(value = "/customer/customerEditDeleteOrders", method = {RequestMethod.GET})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) {

        CustomerDTO currentCustomerDTO = (CustomerDTO) request.getSession().getAttribute("customerDTO");
        if (currentCustomerDTO == null) {
            return new ModelAndView("/customer/CustomerMenu", "model", new MVCModel(null, null, ""));
        }

        return new ModelAndView("/customer/CustomerEditDeleteOrders", "model", new MVCModel(null, null, ""));
    }

    @RequestMapping(value = "/customer/customerEditDeleteOrders", method = {RequestMethod.POST})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        CustomerDTO currentCustomerDTO = (CustomerDTO) request.getSession().getAttribute("customerDTO");
        if (currentCustomerDTO == null) {
            return new ModelAndView("/customer/CustomerMenu", "model", new MVCModel(null, null, ""));
        }

        Timestamp orderedDateTimeBegin = Timestamp.valueOf(request.getParameter("orderedDateTimeBegin"));
        Timestamp orderedDateTimeEnd = Timestamp.valueOf(request.getParameter("orderedDateTimeEnd"));

        List<Order> listOfOrders = orderManagerImpl.getOpenOrdersOfCustomer(
                currentCustomerDTO.getCustomerId(), orderedDateTimeBegin, orderedDateTimeEnd);

        List<OrderDTO> listOfOrdersDTO = convertorDTO.convertOrderListToDTOList(listOfOrders);
        return new ModelAndView("/customer/CustomerEditDeleteOrders", "model", new MVCModel(null, listOfOrdersDTO, ""));
    }

}
