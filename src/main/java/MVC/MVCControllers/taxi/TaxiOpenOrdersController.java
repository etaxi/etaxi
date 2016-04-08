package MVC.MVCControllers.taxi;

import MVC.MVCController;
import MVC.MVCModel;
import business.OrderManagerImpl;
import entity.Order;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Aleks on 01.04.2016.
 */
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
