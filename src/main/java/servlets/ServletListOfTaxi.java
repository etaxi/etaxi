package servlets;

import dao.TaxiDAO;
import dao.TaxiDAOImpl;
import dataSets.TaxiDataSet;
import freemarker.template.Configuration;
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

public class ServletListOfTaxi extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

//        if (request.getSession().getAttribute("userId") == null) {
//            response.setContentType("text/html;charset=utf-8");
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        } else {

            Configuration cfg = new Configuration();

            Map<String, Object> pageVariables = new HashMap<>();
            pageVariables.put("table", "");

            response.getWriter().println(PageGenerator.instance().getPage("taxis.html", pageVariables));
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
        }
    //}

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

//        if (request.getSession().getAttribute("userId") == null) {
//            response.setContentType("text/html;charset=utf-8");
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        } else {

            Map<String, Object> pageVariables = new HashMap<>();

            DBService dbService = new DBService();
            TaxiDAO taxiDAO = new TaxiDAOImpl(dbService.getConnection(), dbService.getDatabaseName());

            String htmlTable = "";

            try {
                List<TaxiDataSet> listOfTaxi = taxiDAO.getAll();
                htmlTable = generateHTMLTableForTaxi(listOfTaxi);

            } catch (SQLException e) {
                e.printStackTrace();
            }

            pageVariables.put("table", htmlTable);
            response.getWriter().println(PageGenerator.instance().getPage("taxis.html", pageVariables));

            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
        }
    //}

    private String generateHTMLTableForTaxi(List<TaxiDataSet> listOfTaxi) {

        StringBuilder htmlString = new StringBuilder("<table border = 1 width=\"100%\">");
        htmlString.append("<tr>")
                .append("<th> ID </th>")
                .append("<th> Name, Surname </th>")
                .append("<th> Car </th>")
                .append("<th> Phone </th>")
                .append("<th> Login </th>")
                .append("<th> Password </th>")
                .append("</tr>");

        for (TaxiDataSet item : listOfTaxi) {
            htmlString.append("<tr>")
                    .append("<td>").append(item.getTaxiId())
                    .append("<td>").append(item.getName())
                    .append("<td>").append(item.getCar())
                    .append("<td>").append(item.getPhone())
                    .append("<td>").append(item.getLogin())
                    .append("<td>").append(item.getPassword())
                    .append("</tr>");
        }

        htmlString.append("</table>");

        return htmlString.toString();
    }

}

