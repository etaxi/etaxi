import dao.CustomerDAO;
import dataSets.CustomerDataSet;
import org.junit.Test;
import services.DBService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/** Проект etaxi
 * JUnit тесты для проекта etaxi
 * */
public class JUnitTestsForCustomer {

    @Test
    public void testСreateDataBaseWithTables() throws SQLException {

        DBService dbService = new DBService();
        dbService.createDataBaseWithTables();

    }

    @Test
    public void testNewCustomerRecord() throws SQLException {

        DBService dbService = new DBService();
        Connection connection = dbService.getMysqlConnection();
        CustomerDAO customerDAO = new CustomerDAO(connection);

        CustomerDataSet customer = new CustomerDataSet((long) 0, "Oleg Ivanov", "(+371)26094567", "login", "password");
        long newCustmerID = customerDAO.update(customer);
    }

    @Test
    public void testUpdateCustomerRecord() throws SQLException {

        DBService dbService = new DBService();
        Connection connection = dbService.getMysqlConnection();
        CustomerDAO customerDAO = new CustomerDAO(connection);

        CustomerDataSet customer = new CustomerDataSet((long) 0, "Ivanova", "(+371)26094567", "login", "password");
        customer.setCustomerId(customerDAO.update(customer));

        customer = new CustomerDataSet(customer.getCustomerId(), "Olga Ivanova", "(+371)26094567", "login", "password");
        customerDAO.update(customer);

    }

    @Test
    public void testGetCustomerByID() throws SQLException {

        DBService dbService = new DBService();
        Connection connection = dbService.getMysqlConnection();
        CustomerDAO customerDAO = new CustomerDAO(connection);

        CustomerDataSet customer = new CustomerDataSet((long) 0, "Olga Ivanova", "(+371)26094567", "login", "password");
        customer.setCustomerId(customerDAO.update(customer));

        customer = customerDAO.getById(customer.getCustomerId());
    }

    @Test
    public void testDeleteCustomerByID() throws SQLException {

        DBService dbService = new DBService();
        Connection connection = dbService.getMysqlConnection();
        CustomerDAO customerDAO = new CustomerDAO(connection);

        CustomerDataSet customer = new CustomerDataSet((long) 0, "Olga Ivanova", "(+371)26094567", "login", "password");
        customer.setCustomerId(customerDAO.update(customer));

        customerDAO.delete(customer);

    }

    @Test
    public void testgetListOfAllCustomers() throws SQLException {

        DBService dbService = new DBService();
        Connection connection = dbService.getMysqlConnection();
        CustomerDAO customerDAO = new CustomerDAO(connection);

        List<CustomerDataSet> listOfCustomers = customerDAO.getALL();
        System.out.println(listOfCustomers.size());

    }


}
