package lv.etaxi.business.managers;

import lv.etaxi.business.CustomerManager;
import lv.etaxi.config.SpringAppConfig;
import lv.etaxi.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Aleks on 19.04.2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAppConfig.class)
//@Transactional
public class CustomerManagerImplTest {

    String name     = "name123";
    String phone    = "phone123";
    String password = "password123";

    Customer newCustomer;

    @Autowired
    CustomerManager customerManagerImpl;

    public CustomerManagerImplTest() {
        newCustomer = new Customer((long)0, name, phone, password);
    }

    public void createNewCustomer() throws Exception {
        customerManagerImpl.create(newCustomer);
    }

    public void deleteCustomer() throws Exception {
        customerManagerImpl.delete(newCustomer);
    }

    @Test
    public void findCustomerById() throws Exception {
        //create NewCustomer();
        Customer customer= new Customer((long)0, name, phone, password);
        customerManagerImpl.create(customer);

        Customer customerFind = customerManagerImpl.findById(customer.getCustomerId());

        assertNotNull(customerFind);
        assertEquals(name,     customerFind.getName());
        assertEquals(phone,    customerFind.getPhone());
        assertEquals(password, customerFind.getPassword());

        customerManagerImpl.delete(customerFind);

        Customer customerFindAfterDelete = customerManagerImpl.findById(customerFind.getCustomerId());
        assertEquals(customerFindAfterDelete, null);
    }

    @Test
    public void findCustomerByLogin() throws Exception {
        createNewCustomer();

        Customer customer = customerManagerImpl.findByLogin(phone);

        assertNotNull(customer);
        assertEquals(newCustomer.getName(),     customer.getName());
        assertEquals(newCustomer.getPhone(),    customer.getPhone());
        assertEquals(newCustomer.getPassword(), customer.getPassword());

        deleteCustomer();

        Customer customerFindAfterDelete = customerManagerImpl.findById(customer.getCustomerId());
        assertEquals(customerFindAfterDelete, null);
    }

    @Test
    public void updateCustomer() throws Exception {
        Customer customer= new Customer((long)0, name, phone, password);
        customerManagerImpl.create(customer);

        Customer customerFind = customerManagerImpl.findById(customer.getCustomerId());
        customerFind.setName("name555");

        //ToDo  .update Customer  - не  хочет работать
        customerManagerImpl.updateInDataBase(customerFind);
        Customer customerFindAfterUpdate = customerManagerImpl.findById(customerFind.getCustomerId());

        assertNotNull(customerFindAfterUpdate);
        assertEquals("name555",     customerFindAfterUpdate.getName());
        assertEquals(phone,         customerFindAfterUpdate.getPhone());
        assertEquals(password,      customerFindAfterUpdate.getPassword());

        customerManagerImpl.delete(customerFindAfterUpdate);

        Customer customerFindAfterDelete = customerManagerImpl.findById(customerFindAfterUpdate.getCustomerId());
        assertEquals(customerFindAfterDelete, null);
    }

    @Test
    public void CheckAuthorization() throws Exception {
        createNewCustomer();

        Customer customer = customerManagerImpl.CheckAuthorization(phone, password);

        assertNotNull(customer);
        assertEquals(newCustomer.getName(),     customer.getName());
        assertEquals(newCustomer.getPhone(),    customer.getPhone());
        assertEquals(newCustomer.getPassword(), customer.getPassword());

        deleteCustomer();

        Customer customerFindAfterDelete = customerManagerImpl.findById(customer.getCustomerId());
        assertEquals(customerFindAfterDelete, null);
    }
}