package servlets.admin;

import dao.AdminDAO;
import dao.jdbc.AdminDAOImpl;
import dao.jdbc.DBConnection;
import entity.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Genady Zalesky on 27.03.2016
 */

@WebServlet(name = "ServletAdminAuthorization" , urlPatterns = {"/admin/adminAuthorization"})
public class ServletAdminAuthorization extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        DBConnection dbService = new DBConnection();
        AdminDAO adminDAO = new AdminDAOImpl(dbService.getConnection(), dbService.getDatabaseName());
        Admin adminDataSet = null;
        try {
            adminDataSet = adminDAO.getByLogin(login);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (adminDataSet == null || !adminDataSet.getPassword().equals(password)) {
            request.setAttribute("message", "Wrong username or password!");
            request.getRequestDispatcher("/admin/AdminAuthorization.jsp").forward(request, response);
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // сохраняем логин админа в сессию, для дальнейшем идентификации клиента в системе
        request.getSession().setAttribute("customerId", adminDataSet.getAdminId());

        request.setAttribute("message", "Authorization successful: " + adminDataSet.getName());
        request.getRequestDispatcher("/admin/AdminMenu.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
