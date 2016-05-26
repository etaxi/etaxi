package databaseTests;

import lv.etaxi.builders.CustomerBuilder;
import lv.etaxi.config.SpringAppConfig;
import lv.etaxi.dao.CustomerDAO;
import lv.etaxi.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/** Проект etaxi
 * JUnit тесты для проекта etaxi (design patterns "Object Mother" and "Test Data Builder")
 * */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAppConfig.class)
@WebAppConfiguration
public class TestsForCustomerHibernate {

    @Autowired
    private CustomerDAO customerDAO;

    @Test
    public void testNewCustomerRecord() throws SQLException {

        CustomerBuilder customerBuilder = CustomerBuilder.aCustomer()
                .withName("Olga Zvonova")
                .withPassword("olgazvonova");

        Customer customer = customerBuilder.build();

        long newCustmerID = customerDAO.create(customer);
    }

    @Test
    public void testNew1000CustomersRecord() throws SQLException {

        CustomerBuilder customerBuilder = CustomerBuilder.aCustomer()
                .withName("Olga Zvonova")
                .withPassword("olgazvonova");

        Customer customer = customerBuilder.build();
        long newCustmerID = customerDAO.create(customer);
    }

    @Test
    public void testNewCustomersRecord() throws SQLException {

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

        Customer customer = CustomerBuilder.aCustomer().build();
        customer.setCustomerId(customerDAO.create(customer));

        int countOfCustomersBeforeDeleteOperation = customerDAO.getAll().size();

        customerDAO.delete(customer);

        int countOfCustomersAfterDeleteOperation = customerDAO.getAll().size();

        assertTrue(countOfCustomersBeforeDeleteOperation-1 == countOfCustomersAfterDeleteOperation);

    }

    @Test
    public void testGetListOfAllCustomers() throws SQLException {

        Customer customer = CustomerBuilder.aCustomer().build();
        customerDAO.create(customer);

        List<Customer> listOfCustomers = customerDAO.getAll();
        assertTrue(listOfCustomers.size()>0);

    }

}
