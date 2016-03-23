package servlets.customer;

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
import java.util.List;
import java.util.Map;


public class ServletListOfCustomers extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

//        if (request.getSession().getAttribute("userId") == null) {
//            response.setContentType("text/html;charset=utf-8");
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        } else {

            Map<String, Object> pageVariables = new HashMap<>();
            pageVariables.put("table", "");

            response.getWriter().println(PageGenerator.instance().getPage("mainMenuForCustomer.html", pageVariables));
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
        }
//    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

//        if (request.getSession().getAttribute("userId") == null) {
//            response.setContentType("text/html;charset=utf-8");
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        } else {

            Map<String, Object> pageVariables = new HashMap<>();

            DBService dbService = new DBService();
            CustomerDAO customerDAO = new CustomerDAOImpl(dbService.getConnection(), dbService.getDatabaseName());

            String htmlTable = "";

            try {
                List<CustomerDataSet> listOfCustomers = customerDAO.getAll();
                htmlTable = generateHTMLTableForCustomers(listOfCustomers);

            } catch (SQLException e) {
                e.printStackTrace();
            }

            pageVariables.put("table", htmlTable);
            response.getWriter().println(PageGenerator.instance().getPage("mainMenuForCustomer.html", pageVariables));

            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
        }
  //  }

    private String generateHTMLTableForCustomers(List<CustomerDataSet> listOfCustomers) {

        StringBuilder htmlString = new StringBuilder("<table class=\"table\">");
        htmlString.append("<thead><tr>")
                .append("<th> ID </th>")
                .append("<th> Name, Surname </th>")
                .append("<th> Phone </th>")
                .append("<th> Password </th>")
                .append("</tr>")
                .append("<tbody>");

        for (CustomerDataSet item : listOfCustomers) {
            htmlString.append("<tr>")
                    .append("<td>").append(item.getCustomerId())
                    .append("<td>").append(item.getName())
                    .append("<td>").append(item.getPhone())
                    .append("<td>").append(item.getPassword())
                    .append("<td><button type=\"button\" class=\"btn btn-delete\">Delete</button>\n")
                    .append("<button type=\"button\" class=\"btn btn-edit\">Edit</button>")
                    .append("</tr>");
        }

        htmlString.append("</tbody>")
                  .append("</table>");
        return htmlString.toString();
    }

}

