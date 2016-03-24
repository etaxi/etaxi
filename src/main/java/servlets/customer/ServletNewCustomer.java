package servlets.customer;

import dao.CustomerDAO;
import dao.jdbc.CustomerDAOImpl;
import entity.Customer;
import dao.jdbc.DBConnection;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ServletNewCustomer extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        Map<String, Object> pageVariables = new HashMap<>();
//        pageVariables.put("message", "Please, enter information about new customer!");
//        pageVariables.put("name", "");
//        pageVariables.put("phone", "");
//        pageVariables.put("password", "");
//
//        response.getWriter().println(PageGenerator.instance().getPage("customer.html", pageVariables));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        Map<String, Object> pageVariables = new HashMap<>();

        String name     = request.getParameter("name");
        String phone    = request.getParameter("phone");
        String password = request.getParameter("password");

        String message = ((name == null || name.isEmpty()) ? "name, surname; " : "") +
                         ((phone == null || phone.isEmpty()) ? "phone; " : "") +
                         ((password == null || password.isEmpty()) ? "password; " : "");

        Boolean registrationSuccessful = false;
        if (message.isEmpty()) {

            DBConnection dbConnection = new DBConnection();
            CustomerDAO customerDAO = new CustomerDAOImpl(dbConnection.getConnection(), dbConnection.getDatabaseName());
            Customer newCustomer = new Customer((long)0, name, phone, password);
            try {
                newCustomer.setCustomerId(customerDAO.update(newCustomer));
                message = "Registration successful (new customer ID: " + newCustomer.getCustomerId() + ")";
                name = ""; phone = ""; password = "";
                registrationSuccessful = true;
            } catch (SQLException e) {
                message = "Registration failed! Please try again!";
            }
        }
        else {
            message = "Please, input information in fields: " + message;
        }

        pageVariables.put("message", message);
        pageVariables.put("name", name);
        pageVariables.put("phone", phone);
        pageVariables.put("password", password);
        response.getWriter().println(PageGenerator.instance().getPage("customer.html", pageVariables));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        if (registrationSuccessful) response.sendRedirect("mainMenuForCustomer.html");

    }

}

