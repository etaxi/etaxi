package servlets.taxi;

import dao.TaxiDAO;
import dao.jdbc.DBConnection;
import dao.jdbc.TaxiDAOImpl;
import entity.Taxi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Aleks on 24.03.2016.
 */
@WebServlet(name = "ServletAuthorization" , urlPatterns = {"/taxi/authorization"})
public class ServletAuthorization extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null || password == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        DBConnection dbConnection = new DBConnection();
        TaxiDAO taxiDAO = new TaxiDAOImpl(dbConnection.getConnection(), dbConnection.getDatabaseName());
        Taxi taxi = null;
        try {
            taxi = taxiDAO.getByLogin(login);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (taxi == null || !taxi.getPassword().equals(password)) {
            request.setAttribute("message", "Wrong username or password");
            request.getRequestDispatcher("/taxi/menustart.jsp").forward(request, response);
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // сохраняем логин (телефон) в сессию, для дальнейшей идентификации
        request.getSession().setAttribute("taxiID", taxi.getTaxiId());


        request.setAttribute("message", "Autorization compliete as "+ taxi.getName());
        request.getRequestDispatcher("/taxi/menuauthorized.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
