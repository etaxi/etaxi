package servlets;

import dao.*;
import dataSets.CustomerDataSet;
import dataSets.OrderDataSet;
import dataSets.TaxiDataSet;
import services.DBService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SrvltCustomers")
public class SrvltCustomers extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String customers;
    private String taxis;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String str_table = request.getParameter("table");
        boolean noError = true;
        String result = "";
        try {
            if (str_table.equals("customers"))
                result = getCustomers();
            else if (str_table.equals("taxis"))
                result = getTaxis();
            else if (str_table.equals("orders"))
                result = getOrders();
            else
                noError = false;
        } catch (Exception ex) {
            noError = false;
        }

        if (noError) {
            doSetResult(response, result);
            return;
        }
        doSetError(response);
    }



    protected void doSetResult( HttpServletResponse response, String result ) throws UnsupportedEncodingException, IOException {
        String reply = result;
        response.getOutputStream().write( reply.getBytes("UTF-8") );
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setStatus( HttpServletResponse.SC_OK );
    }

    protected void doSetError( HttpServletResponse response ) throws UnsupportedEncodingException, IOException {
        String reply = "{\"error\":1}";
        response.getOutputStream().write( reply.getBytes("UTF-8") );
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setStatus( HttpServletResponse.SC_OK );
    }

    public String getCustomers() throws SQLException {
        String customers = "{\"customers\":[\n";

        DBService dbService = new DBService();
        Connection connection = dbService.getMysqlConnection();
        CustomerDAO customerDAO = new CustomerDAOImpl(dbService.getConnection(), dbService.getDatabaseName());
        List<CustomerDataSet> listOfCustomers = customerDAO.getAll();

        for(CustomerDataSet object: listOfCustomers){
            customers = customers + object.toString()+ ",\n";
        }
        customers = customers.substring(0, customers.length() - 2) + "\n]}";

        return customers;
    }

    public String getTaxis() throws SQLException {
        String taxis = "{\"taxis\":[\n";

        DBService dbService = new DBService();
        Connection connection = dbService.getMysqlConnection();
        TaxiDAO taxiDAO = new TaxiDAOImpl(dbService.getConnection(), dbService.getDatabaseName());
        List<TaxiDataSet> listOfTaxi = taxiDAO.getAll();

        for(TaxiDataSet object: listOfTaxi){
            taxis = taxis + object.toString()+ ",\n";
        }
        taxis = taxis.substring(0, taxis.length() - 2) + "\n]}";

        return taxis;
    }


    public String getOrders() throws SQLException {
        String orders = "{\"orders\":[\n";

        DBService dbService = new DBService();
        Connection connection = dbService.getMysqlConnection();
        OrderDAO orderDAO = new OrderDAOImpl(dbService.getConnection(), dbService.getDatabaseName());
        List<OrderDataSet> listOfOrder = orderDAO.getAll();

        for(OrderDataSet object: listOfOrder){
            orders = orders + object.toString()+ ",\n";
        }
        orders = orders.substring(0, orders.length() - 2) + "\n]}";

        return orders;
    }


}
