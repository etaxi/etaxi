package lv.etaxi.MVC.MVCControllers.taxi;

import lv.etaxi.MVC.MVCController;
import lv.etaxi.MVC.MVCModel;
import lv.etaxi.business.managers.OrderManagerImpl;
import lv.etaxi.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Aleks on 01.04.2016.
 */
@Controller
public class TaxiOpenOrdersController implements MVCController {

    @Autowired
    OrderManagerImpl orderManagerImpl;

    public TaxiOpenOrdersController() {

        this.orderManagerImpl = new OrderManagerImpl();
    }

    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) throws SQLException {
        List<Order> listOfOrders = orderManagerImpl.getOpenOrdersAll();
        return new MVCModel("/taxi/TaxiOpenOrders.jsp", listOfOrders);
    }



    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) {
        return null;
    }
}
