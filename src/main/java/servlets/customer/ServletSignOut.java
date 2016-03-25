package servlets.customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by D.Lazorkin on 25.03.2016.
 */
@WebServlet(name = "ServletSignOut" , urlPatterns = {"/customer/signOut"})
public class ServletSignOut extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("customerId") != null) {
            request.getSession().removeAttribute("customerId");

            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        request.setAttribute("message", "");
        request.getRequestDispatcher("/customer/authorization.jsp").forward(request, response);

    }
}
