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
                new OrderManager().updateOrder(order);
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
