package lv.etaxi.MVC.MVCControllers.admin;

import lv.etaxi.MVC.MVCController;
import lv.etaxi.MVC.MVCModel;
import lv.etaxi.business.AdminManager;
import lv.etaxi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Created by Genady Zalesky on 06.04.2016
 */
@Controller
public class AdminRegistrationController implements MVCController {

    @Autowired
    AdminManager adminManagerImpl;


    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) throws SQLException {

        return new MVCModel("/admin/AdminRegistration.jsp", null, "Please, enter information about new admin!");
    }

    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) throws SQLException {

        Admin newAdmin = new Admin(
                (long)0,
                request.getParameter("name"),
                request.getParameter("login"),
                request.getParameter("password"));

        String errorMessage = adminManagerImpl.create(newAdmin);

        if (errorMessage.isEmpty()) {
            request.getSession().setAttribute("admin", newAdmin);
            errorMessage = "Registration successful: " + newAdmin.getName();
        }

        return new MVCModel("/admin/AdminMenu.jsp", newAdmin, errorMessage);
    }
}
