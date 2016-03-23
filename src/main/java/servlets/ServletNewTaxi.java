package servlets;

import dao.TaxiDAO;
import dao.jdbc.TaxiDAOImpl;
import entity.Taxi;
import dao.jdbc.DBService;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ServletNewTaxi extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("message", "Please, enter information about new taxi!");
        pageVariables.put("name", "");
        pageVariables.put("phone", "");
        pageVariables.put("taxiStatus", "");
        pageVariables.put("location", "");
        pageVariables.put("car", "");
        pageVariables.put("login", "");
        pageVariables.put("password", "");
        pageVariables.put("rating", "");

        response.getWriter().println(PageGenerator.instance().getPage("taxi.html", pageVariables));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        Map<String, Object> pageVariables = new HashMap<>();

        String name     = request.getParameter("name");
        String phone    = request.getParameter("phone");
        String car    = request.getParameter("car");
        String login    = request.getParameter("login");
        String password = request.getParameter("password");

        String message = ((name == null || name.isEmpty()) ? "name, surname; " : "") +
                         ((phone == null || phone.isEmpty()) ? "phone; " : "") +
                         ((car == null || car.isEmpty()) ? "car; " : "") +
                         ((login == null || login.isEmpty()) ? "login; " : "") +
                         ((password == null || password.isEmpty()) ? "password; " : "");

        if (message.isEmpty()) {

            DBService dbService = new DBService();
            TaxiDAO taxiDAO = new TaxiDAOImpl(dbService.getConnection(), dbService.getDatabaseName());
            Taxi newTaxi = new Taxi((long)0, name, car, phone, login, password);
            try {
                newTaxi.setTaxiId(taxiDAO.update(newTaxi));
                message = "Registration successful (new taxi ID: " + newTaxi.getTaxiId() + ")";
                name = ""; car = ""; phone = ""; login = ""; password = "";
            } catch (SQLException e) {
                message = "Registration failed! Please try again!";
            }

        }
        else {
            message = "Please, input information in fields: " + message;
        }

        pageVariables.put("message", message);
        pageVariables.put("name", name);
        pageVariables.put("car", car);
        pageVariables.put("phone", phone);
        pageVariables.put("login", login);
        pageVariables.put("password", password);
        response.getWriter().println(PageGenerator.instance().getPage("taxi.html", pageVariables));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

}

