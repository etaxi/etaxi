package servlets.taxi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Aleks on 24.03.2016.
 */
//@WebServlet(name = "ServletTaxiMenu", urlPatterns = "/taxi")
public class ServletTaxiMenu extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("taxiID") == null) {
            request.setAttribute("message", "");
            request.getRequestDispatcher("/taxi/TaxiMenuStart.jsp").forward(request, response);


        } else {
            request.setAttribute("message", "");
            request.getRequestDispatcher("/taxi/TaxiMenuAuthorized.jsp").forward(request, response);
        }

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

    }
}
