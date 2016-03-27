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
@WebServlet(name = "ServletStartAsCustomer", urlPatterns = "/customer")
public class ServletStartAsCustomer extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("customerId") == null) {
            request.setAttribute("message", "");
            request.getRequestDispatcher("/customer/CustomerAuthorization.jsp").forward(request, response);

        } else {
            request.setAttribute("message", "");
            request.getRequestDispatcher("/customer/CustomerMenu.jsp").forward(request, response);
        }
    }
}
