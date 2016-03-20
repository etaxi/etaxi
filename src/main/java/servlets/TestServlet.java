package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by User on 19.03.2016.
 */
@WebServlet
public class TestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        // Set response content type
        resp.setContentType("text/html");

        // Prepare output html
        PrintWriter out = resp.getWriter();
        out.println("<h1>" + "Hello 1) world from Java!" + "</h1>");
        out.println("<h3>" + "Hello 3) world from Java!" + "</h3>");

    }
}
