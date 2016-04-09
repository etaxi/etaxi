package lv.etaxi.MVC.MVCControllers.admin;

import lv.etaxi.MVC.MVCController;
import lv.etaxi.MVC.MVCModel;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Created by Genady Zalesky on 06.04.2016
 */
@Controller
public class AdminMenuController implements MVCController {
    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) throws SQLException {

        return new MVCModel("/admin/AdminMenu.jsp", null, "");
    }

    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) throws SQLException {

        return new MVCModel("/admin/AdminMenu.jsp", null, "");
    }
}
