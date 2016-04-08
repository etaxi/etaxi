package MVC.MVCControllers.admin;

import MVC.MVCController;
import MVC.MVCModel;
import business.AdminManager;
import entity.Admin;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Created by Genady Zalesky on 06.04.2016
 */
@Component
public class AdminAuthorizationController implements MVCController {
    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) throws SQLException {

        return new MVCModel("/admin/AdminAuthorization.jsp", null, "");
    }

    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) throws SQLException {

        Admin currentAdmin = new AdminManager().CheckAuthorization(
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
