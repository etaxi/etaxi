package lv.etaxi.MVC.MVCControllers.admin;

import lv.etaxi.MVC.MVCController;
import lv.etaxi.MVC.MVCModel;
import lv.etaxi.business.managers.AdminManagerImpl;
import lv.etaxi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Created by Genady Zalesky on 06.04.2016
 */

@Controller
public class AdminAuthorizationController implements MVCController {

    @Autowired
    AdminManagerImpl adminManagerImpl;

    public AdminAuthorizationController() {

        this.adminManagerImpl = new AdminManagerImpl();
    }

    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) throws SQLException {

        return new MVCModel("/admin/AdminAuthorization.jsp", null, "");
    }

    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) throws SQLException {

        Admin currentAdmin = adminManagerImpl.CheckAuthorization(
                request.getParameter("login"),
                request.getParameter("password"));

        if (currentAdmin != null) {
            // сохраняем пользователя в сессию, для дальнейшей идентификации клиента в системе
            request.getSession().setAttribute("admin", currentAdmin);
            return new MVCModel("/admin/AdminMenu.jsp", currentAdmin, "Authorization successful: " + currentAdmin.getName());
        }
        else {
            return  new MVCModel("/admin/AdminMenu.jsp", null, "Wrong login or password!");
        }
    }
}
