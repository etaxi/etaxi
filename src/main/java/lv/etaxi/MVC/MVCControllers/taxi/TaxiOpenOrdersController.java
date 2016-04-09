package lv.etaxi.MVC.MVCControllers.taxi;

import lv.etaxi.MVC.MVCController;
import lv.etaxi.MVC.MVCModel;
import lv.etaxi.business.OrderManagerImpl;
import lv.etaxi.entity.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Aleks on 01.04.2016.
 */
@Component
public class TaxiOpenOrdersController implements MVCController {
    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) throws SQLException {
        List<Order> listOfOrders = new OrderManagerImpl().getOpenOrdersAll();
        return new MVCModel("/taxi/TaxiOpenOrders.jsp", listOfOrders);
    }



    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) {
        return null;
    }
}
