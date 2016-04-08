package lv.etaxi.servlets.taxi;

import lv.etaxi.business.TaxiManagerImpl;
import lv.etaxi.entity.Taxi;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

//@WebServlet(name = "ServletTaxiAuthorization" , urlPatterns = {"/taxi/authorization"})
public class ServletTaxiAuthorization extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null || password == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Taxi taxi = null;
        try {
            taxi = new TaxiManagerImpl().findTaxiByLogin(login);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (taxi == null || !taxi.getPassword().equals(password)) {
            request.setAttribute("message", "Wrong username or password");
            request.getRequestDispatcher("/taxi/TaxiMenuStart.jsp").forward(request, response);
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // сохраняем логин (телефон) в сессию, для дальнейшей идентификации
        request.getSession().setAttribute("taxiID", taxi.getTaxiId());

        request.setAttribute("message", "Autorization compliete as "+ taxi.getName());
        request.getRequestDispatcher("/taxi/TaxiMenuAuthorized.jsp").forward(request, response);
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
