package lv.etaxi.springMVCControllers.admin;

import lv.etaxi.business.AdminManager;
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
public class AdminRegistrationControllerSpringMVC {

    @Autowired
    AdminManager adminManagerImpl;

    @RequestMapping(value = "/admin/adminRegistration", method = {RequestMethod.GET})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView modelAndView = new ModelAndView("/admin/AdminRegistration", "model", null);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/adminRegistration", method = {RequestMethod.POST})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) {

        String name     = request.getParameter("name");
        String login    = request.getParameter("login");
        String password = request.getParameter("password");

        String message = ((name == null || name.isEmpty()) ? "name, surname; " : "") +
                ((login == null || login.isEmpty()) ? "login; " : "") +
                ((password == null || password.isEmpty()) ? "password; " : "");

        //ToDo remove after testing
        System.out.println("Registration : name="+ name + " login="+login);

        if (message.isEmpty()) {

            Admin newAdmin = new Admin((long)0, name, login, password);
            try {
                message = " Registration successful (new admin ID: " + newAdmin.getAdminId() + ")";
                adminManagerImpl.create(newAdmin);
            } catch (SQLException e) {
                e.printStackTrace();
                message = "Registration failed! Please try again!";
            }

        }
        else {
            message = "Please, input information in fields: " + message;
        }

        //ToDo remove after testing
        System.out.println(message);


        ModelAndView modelAndView = new ModelAndView("/admin/AdminRegistration", "model", message);
        return modelAndView;
    }
}