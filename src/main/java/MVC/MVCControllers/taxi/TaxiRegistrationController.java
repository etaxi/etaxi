package MVC.MVCControllers.taxi;

import MVC.MVCController;
import MVC.MVCModel;
import business.TaxiManager;
import business.TaxiManagerImpl;
import entity.Taxi;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Created by Aleks on 01.04.2016.
 */
@Component
public class TaxiRegistrationController implements MVCController {
    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) {
        return new MVCModel("/taxi/TaxiRegistration.jsp", "");
    }

    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) {
       // Map<String, Object> pageVariables = new HashMap<>();

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

        System.out.println("Registration : name="+ name + " phone="+phone+" login="+login);

        if (message.isEmpty()) {

            TaxiManager taxiManager = new TaxiManagerImpl();
            Taxi newTaxi = new Taxi((long)0, name, car, phone, login, password);
            try {
                message = " Registration successful (new taxi ID: " + newTaxi.getTaxiId() + ")";
                taxiManager.createNewTaxi(newTaxi);
            } catch (SQLException e) {
                e.printStackTrace();
                message = "Registration failed! Please try again!";
            }

        }
        else {
            message = "Please, input information in fields: " + message;
        }
        System.out.println(message);

        return new MVCModel("/taxi/TaxiMenu.jsp", message);

    }
}
