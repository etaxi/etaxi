package lv.etaxi.springMVCControllers.admin;

import lv.etaxi.MVC.MVCModel;
import lv.etaxi.business.AdminManager;
import lv.etaxi.dto.AdminDTO;
import lv.etaxi.dto.СonvertorDTO;
import lv.etaxi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Created by Genady Zalesky on 30.05.2016
 */
@Controller
public class AdminAuthorizationControllerSpringMVC {

    @Autowired
    AdminManager adminManagerImpl;

    @Autowired
    СonvertorDTO convertorDTO;

    @RequestMapping(value = "/admin/adminAuthorization", method = {RequestMethod.GET})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) {

        return new ModelAndView("/admin/AdminAuthorization", "model", null);
    }

    @RequestMapping(value = "/admin/adminAuthorization", method = {RequestMethod.POST})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        Admin currentAdmin = adminManagerImpl.CheckAuthorization(
                request.getParameter("login"),
                request.getParameter("password"));

        if (currentAdmin != null) {
            // сохраняем пользователя в сессию, для дальнейшей идентификации клиента в системе
            AdminDTO currentAdminDTO = convertorDTO.convertAdminToDTO(currentAdmin);
            request.getSession().setAttribute("adminDTO", currentAdminDTO);

            return new ModelAndView("/admin/AdminMenu", "model",
                    new MVCModel(null, currentAdminDTO,
                            "Authorization successful: " + currentAdminDTO.getName()));

        }
        else {
            return new ModelAndView("/admin/AdminMenu", "model",
                    new MVCModel(null, null, "Wrong login or password!"));
        }
    }
}
