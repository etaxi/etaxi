package MVC.MVCControllers.admin;

import MVC.MVCController;
import MVC.MVCModel;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Created by Genady Zalesky on 06.04.2016
 */
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
