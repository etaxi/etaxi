package lv.etaxi.MVC.MVCControllers.taxi;

import lv.etaxi.MVC.MVCController;
import lv.etaxi.MVC.MVCModel;
import lv.etaxi.business.OrderManager;
import lv.etaxi.dto.TaxiDTO;
import lv.etaxi.dto.СonvertorDTO;
import lv.etaxi.entity.Order;
import lv.etaxi.entity.Taxi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Aleks on 01.04.2016.
 */
@Controller
public class TaxiHistoryController implements MVCController {

    @Autowired
    OrderManager orderManagerImpl;

    @Autowired
    СonvertorDTO convertorDTO;

    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) throws SQLException {
        TaxiDTO taxiDTO = (TaxiDTO) request.getSession().getAttribute("taxi");

        if (taxiDTO != null) {
            List<Order> listOfOrders = orderManagerImpl.getTaxiOrders(taxiDTO.getTaxiId());
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
