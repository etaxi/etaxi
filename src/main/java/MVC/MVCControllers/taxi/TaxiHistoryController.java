package MVC.MVCControllers.taxi;

import MVC.MVCController;
import MVC.MVCModel;
import business.OrderManager;
import entity.Order;
import entity.Taxi;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Aleks on 01.04.2016.
 */
public class TaxiHistoryController implements MVCController {
    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) throws SQLException {
        Taxi taxi = (Taxi)request.getSession().getAttribute("taxi");

        if (taxi != null) {
            List<Order> listOfOrders = new OrderManager().getTaxiOrders(taxi.getTaxiId());
            return new MVCModel("/taxi/TaxiOpenOrders.jsp", listOfOrders);
        }
        else{
            return new MVCModel("/taxi/TaxiMenu.jsp", "Only authorized users can see history");
        }
    }

    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) {
        return null;
    }

}
