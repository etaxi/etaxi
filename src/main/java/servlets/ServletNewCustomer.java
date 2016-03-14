package servlets;

import dao.CustomerDAO;
import dao.CustomerDAOImpl;
import dataSets.CustomerDataSet;
import services.DBService;
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
        pageVariables.put("message", "Please, enter information about new customer!");
        pageVariables.put("name", "");
        pageVariables.put("phone", "");
        pageVariables.put("login", "");
        pageVariables.put("password", "");
        pageVariables.put("tariff", "");

        response.getWriter().println(PageGenerator.instance().getPage("customer.html", pageVariables));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        Map<String, Object> pageVariables = new HashMap<>();

        String name     = request.getParameter("name");
        String phone    = request.getParameter("phone");
        String login    = request.getParameter("login");
        String password = request.getParameter("password");
        String tariff = request.getParameter("tariff");

        String message = ((name == null || name.isEmpty()) ? "name, surname; " : "") +
                         ((phone == null || phone.isEmpty()) ? "phone; " : "") +
                         ((login == null || login.isEmpty()) ? "login; " : "") +
                         ((password == null || password.isEmpty()) ? "password; " : "") +
                         ((tariff == null || tariff.isEmpty()) ? "tariff; " : "");

        if (message.isEmpty()) {

            DBService dbService = new DBService();
            CustomerDAO customerDAO = new CustomerDAOImpl(dbService.getConnection(), dbService.getDatabaseName());
            CustomerDataSet newCustomer = new CustomerDataSet((long)0, name, phone, login, password, tariff);
            try {
                newCustomer.setCustomerId(customerDAO.update(newCustomer));
                message = "Registration successful (new customer ID: " + newCustomer.getCustomerId() + ")";
                name = ""; phone = ""; login = ""; password = ""; tariff = "";
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
        pageVariables.put("login", login);
        pageVariables.put("password", password);
        pageVariables.put("tariff", tariff);
        response.getWriter().println(PageGenerator.instance().getPage("customer.html", pageVariables));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

}

