package lv.etaxi.springMVCControllers.admin;

import lv.etaxi.MVC.MVCModel;
import lv.etaxi.business.AdminManager;
import lv.etaxi.dto.AdminDTO;
import lv.etaxi.dto.СonvertorDTO;
import lv.etaxi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
public class AdminMenuControllerSpringMVC {

    @Autowired
    AdminManager adminManagerImpl;

    @Autowired
    СonvertorDTO convertorDTO;

    @RequestMapping(value = "/admin", method = {RequestMethod.GET})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        Object userDetailsImpl = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = ((User) userDetailsImpl).getUsername();

        Admin currentAdmin = adminManagerImpl.findByLogin(userName);

        if (currentAdmin != null) {
            AdminDTO currentAdminDTO = convertorDTO.convertAdminToDTO(currentAdmin);
            request.getSession().setAttribute("adminDTO", currentAdminDTO);
        }

        return new ModelAndView("/admin/AdminMenu", "model", new MVCModel(null, null, ""));
    }
}