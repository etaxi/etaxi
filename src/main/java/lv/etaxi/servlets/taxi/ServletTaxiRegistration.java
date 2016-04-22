package lv.etaxi.servlets.taxi;

import lv.etaxi.business.TaxiManager;
import lv.etaxi.business.managers.TaxiManagerImpl;
import lv.etaxi.entity.Taxi;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aleks on 24.03.2016.
 */
//@WebServlet(name = "ServletTaxiRegistration", urlPatterns = {"/taxi/registration"})
public class ServletTaxiRegistration extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("message", "Please, enter information about new taxi!");
        request.getRequestDispatcher("/taxi/TaxiRegistration.jsp").forward(request, response);

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> pageVariables = new HashMap<>();

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
                message = "Registration successful (new taxi ID: " + newTaxi.getTaxiId() + ")";
                taxiManager.create(newTaxi);
            } catch (SQLException e) {
                e.printStackTrace();
                message = "Registration failed! Please try again!";
            }

        }
        else {
            message = "Please, input information in fields: " + message;
        }
        System.out.println(message);

        request.setAttribute("message", message);
        request.getRequestDispatcher("/taxi/TaxiMenuStart.jsp").forward(request, response);
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

    }
}
