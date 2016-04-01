package MVC.MVCControllers.taxi;

import MVC.MVCController;
import MVC.MVCModel;
import business.OrderManager;
import entity.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Created by Aleks on 01.04.2016.
 */
public class TaxiCompleteOrderController implements MVCController {
    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) {
        if (request.getSession().getAttribute("taxi") != null) {
            Order order = (Order) request.getSession().getAttribute("order");

            try {
                order.setOrderStatus(Order.OrderStatus.DELIVERED);
                new OrderManager().updateOrder(order);

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
