package databaseTests;

import lv.etaxi.dao.CustomerDAO;
import lv.etaxi.dao.jdbc.CustomerDAOImpl;
import lv.etaxi.entity.Customer;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/** Проект etaxi
 * JUnit тесты для проекта etaxi (design patterns "Object Mother" and "Test Data Builder")
 * */

public class TestsForCustomerJDBC {

    public CustomerDAO aCustomerDAO() {

        return new CustomerDAOImpl();
    }


    @Test
    public void testNewCustomerRecord() throws SQLException {

        CustomerBuilder customerBuilder = CustomerBuilder.aCustomer()
                .withName("Olga Zvonova")
                .withPassword("olgazvonova");

        Customer customer = customerBuilder.build();

        long newCustmerID = aCustomerDAO().create(customer);
    }

    @Test
    public void testNewCustomersRecord() throws SQLException {

        CustomerDAO customerDAO = aCustomerDAO();

        CustomerBuilder customerBuilder = CustomerBuilder.aCustomer()
                .withName("Oleg Vasiljevs")
                .withPassword("olg12345");

        Customer customer1 = customerBuilder.build();
        long newCustmerID1 = customerDAO.create(customer1);

        Customer customer2 = customerBuilder.aCustomer().build();  //USE "DEFAULT USER"
        long newCustmerID2 = customerDAO.create(customer2);

    }

    @Test
    public void testUpdateCustomerRecord() throws SQLException {

        CustomerDAO customerDAO = aCustomerDAO();

        CustomerBuilder customerBuilder = CustomerBuilder.aCustomer()
                .withId((long) 0)
                .withName("Leskova")
                .withPhone("(+371) 26099569")
                .withPassword("lesk123");

        Customer customer = customerBuilder.build();
        customer.setCustomerId(customerDAO.create(customer));
        customer.setName("Olga Leskova");
        customerDAO.update(customer);

    }

    @Test
    public void testGetCustomerByID() throws SQLException {

        CustomerDAO customerDAO = aCustomerDAO();

        CustomerBuilder customerBuilder = CustomerBuilder.aCustomer()
                .withId((long) 0)
                .withName("Leskova")
                .withPhone("(+371) 26099569")
                .withPassword("lesk123");

        Customer customer = customerBuilder.build();
        customer.setCustomerId(customerDAO.create(customer));

        Customer customerGetById = customerDAO.getById(customer.getCustomerId());
        assertEquals(customer.getCustomerId(), customerGetById.getCustomerId());
    }

    @Test
    public void testDeleteCustomerByID() throws SQLException {

        CustomerDAO customerDAO = aCustomerDAO();

        Customer customer = CustomerBuilder.aCustomer().build();
        customer.setCustomerId(customerDAO.create(customer));

        int countOfCustomersBeforeDeleteOperation = customerDAO.getAll().size();

        customerDAO.delete(customer);

        int countOfCustomersAfterDeleteOperation = customerDAO.getAll().size();

        assertTrue(countOfCustomersBeforeDeleteOperation-1 == countOfCustomersAfterDeleteOperation);

    }

    @Test
    public void testGetListOfAllCustomers() throws SQLException {

        CustomerDAO customerDAO = aCustomerDAO();

        Customer customer = CustomerBuilder.aCustomer().build();
        customerDAO.update(customer);

        List<Customer> listOfCustomers = customerDAO.getAll();
        assertTrue(listOfCustomers.size()>0);

    }

}
