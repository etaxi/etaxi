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

@WebServlet(name = "ServletAdminEditProfile", urlPatterns = {"/admin/adminEditProfile"})
public class ServletAdminEditProfile extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("adminId") != null) {

            Admin currentAdmin = null;
            try {
                currentAdmin = new AdminManagerImpl().findById((long) request.getSession().getAttribute("adminId"));
            } catch (SQLException e) {
                e.printStackTrace();
            }

            request.setAttribute("message", "Please, enter new information about admin!");
            request.setAttribute("name", currentAdmin.getName());
            request.setAttribute("login", currentAdmin.getLogin());
            request.setAttribute("password", currentAdmin.getPassword());

            request.getRequestDispatcher("/admin/AdminEditProfile.jsp").forward(request, response);
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
        }
        else {
            request.setAttribute("message", "");
            request.getRequestDispatcher("/admin/AdminAuthorization.jsp").forward(request, response);
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("adminId") != null) {

            AdminManager adminManager = new AdminManagerImpl();
            Admin currentAdmin = null;
            try {
                currentAdmin = adminManager.findById((long) request.getSession().getAttribute("adminId"));
            } catch (SQLException e) {
                e.printStackTrace();
            }

            String name = request.getParameter("name");
            String login = request.getParameter("login");
            String password = request.getParameter("password");

            String message = ((name == null || name.isEmpty()) ? "name, surname; " : "") +
                    ((login == null || login.isEmpty()) ? "login; " : "") +
                    ((password == null || password.isEmpty()) ? "password; " : "");

            Boolean registrationSuccessful = false;
            if (message.isEmpty()) {

                currentAdmin.setName(name);
                currentAdmin.setPassword(password);
                currentAdmin.setLogin(login);

                try {
                    Admin presentAdminWthSuchLogin = adminManager.findByLogin(currentAdmin.getLogin());
                    if ((presentAdminWthSuchLogin != null)
                            && (presentAdminWthSuchLogin.getAdminId() != currentAdmin.getAdminId())) {
                        message = "You can't use this login! The admin with such login already present!";
                    }
                    else{
                        adminManager.update(currentAdmin);
                        message = "The data change is made (" + currentAdmin.getName() + ")";
                        registrationSuccessful = true;
                    }
                } catch (SQLException e) {
                    message = "Registration failed! Please try again!";
                }

            } else {
                message = "Please, input information in fields: " + message;
            }

            request.setAttribute("message", message);
            request.setAttribute("name", name);
            request.setAttribute("login", login);
            request.setAttribute("password", password);

            if (registrationSuccessful) {
                request.getRequestDispatcher("/admin/AdminMenu.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/admin/AdminEditProfile.jsp").forward(request, response);
            }
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
        }
        else {
            request.setAttribute("message", "");
            request.getRequestDispatcher("/admin/AdminAuthorization.jsp").forward(request, response);
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
