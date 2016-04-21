package lv.etaxi.MVC.MVCControllers.admin;

import lv.etaxi.MVC.MVCController;
import lv.etaxi.MVC.MVCModel;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Genady Zalesky on 06.04.2016
 */
@Controller
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
