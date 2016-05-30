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
public class AdminEditProfileControllerSpringMVC {

    @Autowired
    AdminManager adminManagerImpl;

    @Autowired
    СonvertorDTO convertorDTO;

    @RequestMapping(value = "/admin/adminEditProfile", method = {RequestMethod.GET})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) {

        AdminDTO currentAdminDTO = (AdminDTO) request.getSession().getAttribute("taxi");
        if (currentAdminDTO == null) {
            ModelAndView modelAndView = new ModelAndView("/admin/AdminMenu", "model", "");
            return modelAndView;
        }

        ModelAndView modelAndView = new ModelAndView("/admin/AdminEditProfile", "model", "Please, enter your new information!");
        return modelAndView;
    }


    @RequestMapping(value = "/admin/adminEditProfile", method = {RequestMethod.POST})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        AdminDTO currentAdminDTO = (AdminDTO) request.getSession().getAttribute("admin");
        if (currentAdminDTO == null) {
            ModelAndView modelAndView = new ModelAndView("/admin/AdminMenu", "model", "");
            return modelAndView;
        }

        currentAdminDTO.setName(request.getParameter("name"));
        currentAdminDTO.setLogin(request.getParameter("login"));
        currentAdminDTO.setPassword(request.getParameter("password"));

        Admin currentAdmin = convertorDTO.convertAdminFromDTO(currentAdminDTO);
        adminManagerImpl.update(currentAdmin);

        ModelAndView modelAndView = new ModelAndView("/admin/AdminMenu", "model", "");
        return modelAndView;
    }
}
