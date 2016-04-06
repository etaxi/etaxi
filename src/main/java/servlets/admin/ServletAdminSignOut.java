package servlets.admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Genady Zalesky on 06.04.2016
 */
@WebServlet(name = "ServletAdminSignOut" , urlPatterns = {"/admin/signOut"})
public class ServletAdminSignOut extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("adminId") != null) {
            request.getSession().removeAttribute("adminId");
        }

        request.setAttribute("message", "");
        request.getRequestDispatcher("admin/AdminAuthorization.jsp").forward(request, response);
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
