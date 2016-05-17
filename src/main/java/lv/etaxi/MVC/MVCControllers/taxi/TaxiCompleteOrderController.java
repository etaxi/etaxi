package lv.etaxi.MVC.MVCControllers.taxi;

import lv.etaxi.MVC.MVCController;
import lv.etaxi.MVC.MVCModel;
import lv.etaxi.business.OrderManager;
import lv.etaxi.dto.OrderDTO;
import lv.etaxi.dto.СonvertorDTO;
import lv.etaxi.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Created by Aleks on 01.04.2016.
 */
@Controller
public class TaxiCompleteOrderController implements MVCController {

    @Autowired
    OrderManager orderManagerImpl;

    @Autowired
    СonvertorDTO convertorDTO;

    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) {
        if (request.getSession().getAttribute("taxi") != null) {
            OrderDTO orderDTO = (OrderDTO) request.getSession().getAttribute("order");

            try {
                Order order = convertorDTO.convertOrderFromDTO(orderDTO);
                order.setOrderStatus(Order.OrderStatus.DELIVERED);
                orderManagerImpl.update(order);

                return new MVCModel("/taxi/TaxiCompleteOrder.jsp", "");

            } catch (SQLException e) {
                e.printStackTrace();
                return new MVCModel("/taxi/", "");
            }

        }
        else {
            return new MVCModel("/taxi/TaxiMenu.jsp", "");
        }
    }

    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) {
        return null;
    }
}
