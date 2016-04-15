package lv.etaxi.MVC.MVCControllers.taxi;

import lv.etaxi.MVC.MVCController;
import lv.etaxi.MVC.MVCModel;
import lv.etaxi.business.managers.OrderManagerImpl;
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
    OrderManagerImpl orderManagerImpl;

    public TaxiCompleteOrderController() {

        this.orderManagerImpl = new OrderManagerImpl();
    }


    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) {
        if (request.getSession().getAttribute("taxi") != null) {
            Order order = (Order) request.getSession().getAttribute("order");

            try {
                order.setOrderStatus(Order.OrderStatus.DELIVERED);
                orderManagerImpl.updateOrder(order);

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
