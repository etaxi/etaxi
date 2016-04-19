package lv.etaxi.business.managers;

import lv.etaxi.entity.Customer;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Aleks on 19.04.2016.
 */
public class CustomerManagerImplTest {

    String name     = "name";
    String phone    = "phone";
    String password = "password";

    Customer newCustomer;

    CustomerManagerImpl customerManagerImpl;

    public CustomerManagerImplTest() {
        this.customerManagerImpl = new CustomerManagerImpl();
        newCustomer = new Customer((long)0, name, phone, password);
    }


    public void createNewCustomer() throws Exception {
        customerManagerImpl.createNewCustomer(newCustomer);
    }

    public void deleteCustomer() throws Exception {
        customerManagerImpl.deleteCustomer(newCustomer);
    }

    @Test
    public void findCustomerById() throws Exception {
        //createNewCustomer();
        Customer customer= new Customer((long)0, name, phone, password);
        customerManagerImpl.createNewCustomer(customer);

        Customer customerFind = customerManagerImpl.findCustomerById(customer.getCustomerId());

        assertNotNull(customerFind);
        assertEquals(name,     customerFind.getName());
        assertEquals(phone,    customerFind.getPhone());
        assertEquals(password, customerFind.getPassword());

        customerManagerImpl.deleteCustomer(customerFind);
    }

    @Test
    public void findCustomerByLogin() throws Exception {
        createNewCustomer();

        Customer customer = customerManagerImpl.findCustomerByLogin(phone);

        assertNotNull(customer);
        assertEquals(newCustomer.getName(),     customer.getName());
        assertEquals(newCustomer.getPhone(),    customer.getPhone());
        assertEquals(newCustomer.getPassword(), customer.getPassword());

        deleteCustomer();
    }

    @Test
    public void updateCustomer() throws Exception {
        Customer customer= new Customer((long)0, name, phone, password);
        customerManagerImpl.createNewCustomer(customer);

        Customer customerFind = customerManagerImpl.findCustomerById(customer.getCustomerId());

        customerFind.setName("name555");

        assertNotNull(customerFind);
        assertEquals("name555",     customerFind.getName());
        assertEquals(phone,    customerFind.getPhone());
        assertEquals(password, customerFind.getPassword());

        customerManagerImpl.deleteCustomer(customerFind);
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
    }

}