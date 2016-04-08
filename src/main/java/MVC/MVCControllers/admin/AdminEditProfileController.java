package MVC.MVCControllers.admin;

import MVC.MVCController;
import MVC.MVCModel;
import business.AdminManagerImpl;
import entity.Admin;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Genady Zalesky on 07.04.2016
 */
@Component
public class AdminEditProfileController implements MVCController {

    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) {

        Admin currentAdmin = (Admin)request.getSession().getAttribute("admin");
        if (currentAdmin == null) {
            return new MVCModel("/admin/AdminMenu.jsp", null, "");
        }

        return new MVCModel("/admin/AdminEditProfile.jsp", currentAdmin, "Please, enter your new information!");
    }

    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) {

        Admin currentAdmin = (Admin)request.getSession().getAttribute("admin");
        if (currentAdmin == null) {
            return new MVCModel("/admin/AdminMenu.jsp", null, "");
        }

        currentAdmin.setName(request.getParameter("name"));
        currentAdmin.setLogin(request.getParameter("login"));
        currentAdmin.setPassword(request.getParameter("password"));

        String errorMessage = new AdminManagerImpl().updateAdmin(currentAdmin);

        if (errorMessage.isEmpty()) {
            errorMessage = "The data change was made (" + currentAdmin.getName() + ")";
            return new MVCModel("/admin/AdminMenu.jsp", currentAdmin, errorMessage);
        } else {
            return new MVCModel("/admin/AdminEditProfile.jsp", null, errorMessage);
        }

    }
}
