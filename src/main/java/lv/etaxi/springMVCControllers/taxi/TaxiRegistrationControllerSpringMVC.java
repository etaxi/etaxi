package lv.etaxi.springMVCControllers.taxi;

import lv.etaxi.MVC.MVCController;
import lv.etaxi.MVC.MVCModel;
import lv.etaxi.business.TaxiManager;
import lv.etaxi.entity.Taxi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Created by Aleks on 01.04.2016.
 */
@Controller
public class TaxiRegistrationControllerSpringMVC {

    @Autowired
    TaxiManager taxiManagerImpl;


    @RequestMapping(value = "/taxi/registration", method = {RequestMethod.GET})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView modelAndView = new ModelAndView("/taxi/TaxiRegistration", "model", null);
        return modelAndView;

    }

    @RequestMapping(value = "/taxi/registration", method = {RequestMethod.POST})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) {

        String name     = request.getParameter("name");
        String phone    = request.getParameter("phone");
        String car      = request.getParameter("car");
        String login    = request.getParameter("login");
        String password = request.getParameter("password");

        String message = ((name == null || name.isEmpty()) ? "name, surname; " : "") +
                ((phone == null || phone.isEmpty()) ? "phone; " : "") +
                ((car == null || car.isEmpty()) ? "car; " : "") +
                ((login == null || login.isEmpty()) ? "login; " : "") +
                ((password == null || password.isEmpty()) ? "password; " : "");

        //ToDo remove after testing
        System.out.println("Registration : name="+ name + " phone="+phone+" login="+login);

        if (message.isEmpty()) {

            Taxi newTaxi = new Taxi((long)0, name, car, phone, login, password);
            try {
                message = " Registration successful (new taxi ID: " + newTaxi.getTaxiId() + ")";
                taxiManagerImpl.create(newTaxi);
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


        ModelAndView modelAndView = new ModelAndView("/taxi/TaxiRegistration", "model", message);
        return modelAndView;

    }

}
