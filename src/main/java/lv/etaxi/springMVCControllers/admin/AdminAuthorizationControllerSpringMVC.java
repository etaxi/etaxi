package lv.etaxi.springMVCControllers.admin;

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

        ModelAndView modelAndView = new ModelAndView("/admin/AdminAuthorization", "model", null);
        return modelAndView;

    }

    @RequestMapping(value = "/admin/adminAuthorization", method = {RequestMethod.POST})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null || password == null) {
            ModelAndView modelAndView = new ModelAndView("/admin/AdminAuthorization", "model", null);
            return modelAndView;
        }

        Admin admin = null;
        try {
            admin = adminManagerImpl.findByLogin(login);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (admin == null || !admin.getPassword().equals(password)) {
            ModelAndView modelAndView = new ModelAndView("/admin/AdminAuthorization", "model", null);
            return modelAndView;
        }

        // сохраняем логин в сессию, для дальнейшей идентификации
        AdminDTO currentAdminDTO = convertorDTO.convertAdminToDTO(admin);
        request.getSession().setAttribute("admin", currentAdminDTO);

        ModelAndView modelAndView = new ModelAndView("/admin/AdminMenu", "model", null);
        return modelAndView;
    }
}
