package lv.etaxi.MVC.MVCControllers.taxi;

import lv.etaxi.MVC.MVCController;
import lv.etaxi.MVC.MVCModel;
import lv.etaxi.business.OrderManagerImpl;
import lv.etaxi.entity.Order;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Created by Aleks on 01.04.2016.
 */
@Controller
public class TaxiCancelOrderController implements MVCController {
    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) {

        if (request.getSession().getAttribute("taxi") != null) {

            if (request.getSession().getAttribute("order") == null){
                return new MVCModel("/taxi/TaxiMenu.jsp", "You don't have order to cancel");
            }

            Order order = (Order) request.getSession().getAttribute("order");

            try {
                order.setTaxiId((long) 0);
                order.setOrderStatus(Order.OrderStatus.WAITING);
                new OrderManagerImpl().updateOrder(order);
                request.getSession().removeAttribute("order");
                return new MVCModel("/taxi/TaxiCancelOrder.jsp", "");
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
