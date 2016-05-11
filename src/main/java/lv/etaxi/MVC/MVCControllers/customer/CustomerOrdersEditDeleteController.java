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
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by D.Lazorkin on 02.04.2016.
 */
@Controller
public class CustomerOrdersEditDeleteController implements MVCController {

    @Autowired
    OrderManager orderManagerImpl;

    @Autowired
    СonvertorDTO convertorDTO;

    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) throws SQLException {

        CustomerDTO currentCustomerDTO = (CustomerDTO) request.getSession().getAttribute("customerDTO");
        if (currentCustomerDTO == null) {
            return new MVCModel("/customer/CustomerMenu.jsp", null, "");
        }

        return new MVCModel("/customer/CustomerEditDeleteOrders.jsp", null, "");
    }

    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) throws SQLException {

        CustomerDTO currentCustomerDTO = (CustomerDTO) request.getSession().getAttribute("customerDTO");
        if (currentCustomerDTO == null) {
            return new MVCModel("/customer/CustomerMenu.jsp", null, "");
        }

        Timestamp orderedDateTimeBegin = Timestamp.valueOf(request.getParameter("orderedDateTimeBegin"));
        Timestamp orderedDateTimeEnd = Timestamp.valueOf(request.getParameter("orderedDateTimeEnd"));

        List<Order> listOfOrders = orderManagerImpl.getOpenOrdersOfCustomer(
                currentCustomerDTO.getCustomerId(), orderedDateTimeBegin, orderedDateTimeEnd);

        List<OrderDTO> listOfOrdersDTO = convertorDTO.convertOrderListToDTOList(listOfOrders);
        return new MVCModel("/customer/CustomerEditDeleteOrders.jsp", listOfOrdersDTO, "");

    }

}
