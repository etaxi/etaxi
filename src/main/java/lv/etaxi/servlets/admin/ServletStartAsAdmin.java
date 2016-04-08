package lv.etaxi.servlets.admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Genady Zalesky on 29.03.2016
 */
@WebServlet(name = "ServletStartAsAdmin", urlPatterns = "/admin")
public class ServletStartAsAdmin extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("adminID") == null) {
            request.setAttribute("message", "");
            request.getRequestDispatcher("/admin/AdminAuthorization.jsp").forward(request, response);


        } else {
            request.setAttribute("message", "");
            request.getRequestDispatcher("/admin/AdminMenu.jsp").forward(request, response);
        }

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

    }
}
