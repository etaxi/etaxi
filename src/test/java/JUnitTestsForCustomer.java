import dao.CustomerDAO;
import dataSets.CustomerDataSet;
import org.junit.Test;
import services.DBService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/** Проект etaxi
 * JUnit тесты для проекта etaxi (design patterns "Object Mother" and "Test Data Builder")
 * */


 class CustomerBuilder {

    public static final Long   DEFAULT_ID = (long) 0;
    public static final String DEFAULT_NAME = "Oleg Ivanov";
    public static final String DEFAULT_PHONE = "(+371) 26907856";
    public static final String DEFAULT_LOGIN = "login";
    public static final String DEFAULT_PASSWORD = "password";

    private Long id = DEFAULT_ID;
    private String login = DEFAULT_LOGIN;
    private String password = DEFAULT_PASSWORD;
    private String phone = DEFAULT_PHONE;
    private String name = DEFAULT_NAME;

    private CustomerBuilder() {}

    public static CustomerBuilder aCustomer() {
        return new CustomerBuilder();
    }

    public CustomerBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public CustomerBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CustomerBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public CustomerBuilder withLogin(String login) {
        this.login = login;
        return this;
    }

    public CustomerBuilder withNoLogin() {
        this.login = null;
        return this;
    }

    public CustomerBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public CustomerBuilder withNoPassword() {
        this.password = null;
        return this;
    }


    public CustomerBuilder but() {
        return CustomerBuilder
                .aCustomer()
                .withName(name)
                .withPhone(phone)
                .withPassword(password)
                .withLogin(login);
     }

    public CustomerDataSet build() {
        return new CustomerDataSet(id, name, phone, login, password);
    }
}

public class JUnitTestsForCustomer {

    public CustomerDAO aCustomerDAO() {

        DBService dbService = new DBService();
        Connection connection = dbService.getMysqlConnection();
        CustomerDAO customerDAO = new CustomerDAO(connection);

        return customerDAO;
    }

    @Test
    public void testСreateDataBaseWithTables() throws SQLException {

        DBService dbService = new DBService();
        dbService.createDataBaseWithTables();

    }

    @Test
    public void testNewCustomerRecord() throws SQLException {

        CustomerBuilder customerBuilder = CustomerBuilder.aCustomer()
                .withName("Olga Zvonkova")
                .withLogin("Olga")
                .withPassword("olgazvonkova");

        CustomerDataSet customer = customerBuilder.build();

        long newCustmerID = aCustomerDAO().update(customer);
    }


    @Test
    public void testNewCustomersRecord() throws SQLException {

        CustomerDAO customerDAO = aCustomerDAO();

        CustomerBuilder customerBuilder = CustomerBuilder.aCustomer()
                .withName("Oleg Vasiljevs")
                .withLogin("olgVas")
                .withPassword("olg12345");

        CustomerDataSet customer1 = customerBuilder.build();
        long newCustmerID1 = customerDAO.update(customer1);

        CustomerDataSet customer2 = customerBuilder.aCustomer().build();  //USE "DEFAULT USER"
        long newCustmerID2 = customerDAO.update(customer2);

    }

    @Test
    public void testUpdateCustomerRecord() throws SQLException {

        CustomerDAO customerDAO = aCustomerDAO();

        CustomerDataSet customer = new CustomerDataSet((long) 0, "Ivanova", "(+371)26094567", "login", "password");
        customer.setCustomerId(customerDAO.update(customer));

        customer = new CustomerDataSet(customer.getCustomerId(), "Olga Ivanova", "(+371)26094567", "login", "password");
        customerDAO.update(customer);

    }

    @Test
    public void testGetCustomerByID() throws SQLException {

        CustomerDAO customerDAO = aCustomerDAO();

        CustomerDataSet customer = new CustomerDataSet((long) 0, "Olga Ivanova", "(+371)26094567", "login", "password");
        customer.setCustomerId(customerDAO.update(customer));

        customer = customerDAO.getById(customer.getCustomerId());
    }

    @Test
    public void testDeleteCustomerByID() throws SQLException {

        CustomerDAO customerDAO = aCustomerDAO();

        CustomerDataSet customer = new CustomerDataSet((long) 0, "Olga Ivanova", "(+371)26094567", "login", "password");
        customer.setCustomerId(customerDAO.update(customer));

        customerDAO.delete(customer);

    }

    @Test
    public void testgetListOfAllCustomers() throws SQLException {

        CustomerDAO customerDAO = aCustomerDAO();

        List<CustomerDataSet> listOfCustomers = customerDAO.getALL();
        System.out.println(listOfCustomers.size());

    }


}
