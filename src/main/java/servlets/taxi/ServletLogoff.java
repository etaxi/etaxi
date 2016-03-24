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
@WebServlet(name = "ServletLogoff" , urlPatterns = {"/taxi/logoff"})
public class ServletLogoff extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("taxiId") != null) {
            request.getSession().removeAttribute("taxiId");
            request.getSession().removeAttribute("orderId");

            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        request.setAttribute("message", "");
        request.getRequestDispatcher("/taxi/menustart.jsp").forward(request, response);

    }
}
