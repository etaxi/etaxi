package lv.etaxi.MVC.MVCControllers.taxi;

import lv.etaxi.MVC.MVCController;
import lv.etaxi.MVC.MVCModel;
import lv.etaxi.business.OrderManager;
import lv.etaxi.entity.Order;
import lv.etaxi.entity.Taxi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Created by Aleks on 01.04.2016.
 */
@Controller
public class TaxiTakeOrderController implements MVCController {

    @Autowired
    OrderManager orderManagerImpl;

    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) {
        return null;
    }

    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) {

        if (request.getSession().getAttribute("taxi") != null) {
            String orderId     = request.getParameter("orderId");


            try {
                Order order = orderManagerImpl.findOrderById(Long.parseLong(orderId));
                Taxi taxi = (Taxi) request.getSession().getAttribute("taxi");
                order.setTaxiId(taxi.getTaxiId());
                order.setOrderStatus(Order.OrderStatus.TAKEN);
                orderManagerImpl.updateOrder(order);
                request.getSession().setAttribute("order", order);

                return new MVCModel("/taxi/TaxiTakeOrder.jsp", "");

            } catch (SQLException e) {
                e.printStackTrace();
                return new MVCModel("/taxi/", "");
            }


        }
        else {
            return new MVCModel("/taxi/TaxiMenu.jsp", "");
        }

    }

}
