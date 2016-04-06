package MVC.MVCControllers.admin;

import MVC.MVCController;
import MVC.MVCModel;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Genady Zalesky on 06.04.2016
 */
public class AdminSignOutController implements MVCController {
    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) {

        return signOutUser(request);
    }

    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) {

        return signOutUser(request);
    }

    private MVCModel signOutUser(HttpServletRequest request) {

        if (request.getSession().getAttribute("admin") != null) {
            request.getSession().removeAttribute("admin");
        }
        return new MVCModel("/admin/AdminMenu.jsp", null, "");
    }
}
