package MVC.MVCControllers.admin;

import MVC.MVCController;
import MVC.MVCModel;
import business.AdminManager;
import entity.Admin;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Created by Genady Zalesky on 06.04.2016
 */
public class AdminRegistrationController implements MVCController {
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

        String errorMessage = new AdminManager().createNewAdmin(newAdmin);

        if (errorMessage.isEmpty()) {
            request.getSession().setAttribute("admin", newAdmin);
            errorMessage = "Registration successful: " + newAdmin.getName();
        }

        return new MVCModel("/admin/AdminMenu.jsp", newAdmin, errorMessage);
    }
}
