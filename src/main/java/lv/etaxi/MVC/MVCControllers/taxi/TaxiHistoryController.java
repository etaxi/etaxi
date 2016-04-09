package lv.etaxi.MVC.MVCControllers.taxi;

import lv.etaxi.MVC.MVCController;
import lv.etaxi.MVC.MVCModel;
import lv.etaxi.business.OrderManagerImpl;
import lv.etaxi.entity.Order;
import lv.etaxi.entity.Taxi;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Aleks on 01.04.2016.
 */
@Controller
public class TaxiHistoryController implements MVCController {
    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) throws SQLException {
        Taxi taxi = (Taxi)request.getSession().getAttribute("taxi");

        if (taxi != null) {
            List<Order> listOfOrders = new OrderManagerImpl().getTaxiOrders(taxi.getTaxiId());
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
