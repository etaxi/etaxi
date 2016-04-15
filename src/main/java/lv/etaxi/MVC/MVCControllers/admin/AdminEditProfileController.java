package lv.etaxi.MVC.MVCControllers.admin;

import lv.etaxi.MVC.MVCController;
import lv.etaxi.MVC.MVCModel;
import lv.etaxi.business.managers.AdminManagerImpl;
import lv.etaxi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Genady Zalesky on 07.04.2016
 */

@Controller
public class AdminEditProfileController implements MVCController {

    @Autowired
    AdminManagerImpl adminManagerImpl;

    public AdminEditProfileController() {

        this.adminManagerImpl = new AdminManagerImpl();
    }

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

        String errorMessage = adminManagerImpl.updateAdmin(currentAdmin);

        if (errorMessage.isEmpty()) {
            errorMessage = "The data change was made (" + currentAdmin.getName() + ")";
            return new MVCModel("/admin/AdminMenu.jsp", currentAdmin, errorMessage);
        } else {
            return new MVCModel("/admin/AdminEditProfile.jsp", null, errorMessage);
        }

    }
}
