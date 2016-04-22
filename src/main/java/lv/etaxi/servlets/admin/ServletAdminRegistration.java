package lv.etaxi.servlets.admin;

import lv.etaxi.business.AdminManager;
import lv.etaxi.business.managers.AdminManagerImpl;
import lv.etaxi.entity.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Genady Zalesky on 29.03.2016
 */

@WebServlet(name = "ServletAdminRegistration", urlPatterns = {"/admin/registration"})
public class ServletAdminRegistration extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("message", "Please, enter information about new admin!");
        request.getRequestDispatcher("/admin/AdminRegistration.jsp").forward(request, response);
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name     = request.getParameter("name");
        String login    = request.getParameter("login");
        String password = request.getParameter("password");

        String message = ((name == null || name.isEmpty()) ? "name, surname; " : "") +
                ((login == null || login.isEmpty()) ? "login; " : "") +
                ((password == null || password.isEmpty()) ? "password; " : "");

        Boolean registrationSuccessful = false;
        AdminManager adminManager = new AdminManagerImpl();
        if (message.isEmpty()) {

            try {
                if (adminManager.findByLogin(login) != null) {
                    message = "You can't use such login! The admin with such login already present!";
                }
                else {
                    Admin newAdmin = new Admin((long)0, name, login, password);
                    adminManager.create(newAdmin);

                    message = "Registration successful: " + newAdmin.getName();
                    registrationSuccessful = true;

                    // сохраняем логин (телефон) пользователя в сессию, для дальнейшем идентификации клиента в системе
                    request.getSession().setAttribute("customerId", newAdmin.getAdminId());
                }
            } catch (SQLException e) {
                message = "Registration failed! Please try again!";
            }

        }
        else {
            message = "Please, input information in fields: " + message;
        }

        request.setAttribute("message", message);
        request.setAttribute("name", name);
        request.setAttribute("login", login);
        request.setAttribute("password", password);

        if (registrationSuccessful) {
            request.getRequestDispatcher("/admin/AdminMenu.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/admin/AdminRegistration.jsp").forward(request, response);
        }

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
