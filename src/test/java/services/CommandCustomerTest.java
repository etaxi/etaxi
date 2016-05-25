package services;

import lv.etaxi.commands.customers.*;
import lv.etaxi.config.SpringAppConfig;
import lv.etaxi.dto.CustomerDTO;
import lv.etaxi.services.CommandExecutor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

/**
 * Created by D.Lazorkin on 24.05.2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAppConfig.class)
@WebAppConfiguration
public class CommandCustomerTest {

    @Autowired
    CommandExecutor commandExecutorImpl;

    @Test
    public void createNewCustomer() throws Exception {

        String name     =  "Oleg Efremovs";
        String login    =  "loginOleg";
        String password =  "passwordOleg";

        CreateCustomerCommand createCustomerCommand = new CreateCustomerCommand(name, login, password);
        CustomerOperationResult customerOperationResult = commandExecutorImpl.execute(createCustomerCommand);
        CustomerDTO newCustomer = customerOperationResult.getCustomer();

        assertNotNull(newCustomer);
        assertFalse(newCustomer.getCustomerId() == 0);
        assertEquals(name, newCustomer.getName());
        assertEquals(login, newCustomer.getPhone());
        assertEquals(password, newCustomer.getPassword());

        // Delete created customer from database
        DeleteCustomerCommand deleteCustomerCommand = new DeleteCustomerCommand(newCustomer.getCustomerId());
        commandExecutorImpl.execute(deleteCustomerCommand);
    }


    @Test
    public void getCustomerById() throws Exception {

        String name     =  "Oleg Efremovs";
        String login    =  "loginOleg2";
        String password =  "passwordOleg2";

        CreateCustomerCommand createCustomerCommand = new CreateCustomerCommand(name, login, password);
        CustomerOperationResult customerOperationResult = commandExecutorImpl.execute(createCustomerCommand);
        CustomerDTO newCustomer = customerOperationResult.getCustomer();

        GetCustomerCommand getCustomerCommand = new GetCustomerCommand(newCustomer.getCustomerId());
        customerOperationResult = commandExecutorImpl.execute(getCustomerCommand);
        CustomerDTO findedCustomer = customerOperationResult.getCustomer();

        assertNotNull(findedCustomer);
        assertFalse(findedCustomer.getCustomerId() == 0);
        assertEquals(name, findedCustomer.getName());
        assertEquals(login, findedCustomer.getPhone());
        assertEquals(password, findedCustomer.getPassword());

        // Delete created customer from database
        DeleteCustomerCommand deleteCustomerCommand = new DeleteCustomerCommand(newCustomer.getCustomerId());
        commandExecutorImpl.execute(deleteCustomerCommand);
    }

    @Test
    public void findCustomerByLogin() throws Exception {

        String name     =  "Oleg Efremovs";
        String login    =  "loginOleg3";
        String password =  "passwordOleg3";

        CreateCustomerCommand createCustomerCommand = new CreateCustomerCommand(name, login, password);
        CustomerOperationResult customerOperationResult = commandExecutorImpl.execute(createCustomerCommand);
        CustomerDTO newCustomer = customerOperationResult.getCustomer();

        FindCustomerByLoginCommand findCustomerByLoginCommand = new FindCustomerByLoginCommand(login);
        customerOperationResult = commandExecutorImpl.execute(findCustomerByLoginCommand);
        CustomerDTO findedCustomer = customerOperationResult.getCustomer();

        assertNotNull(findedCustomer);
        assertTrue(findedCustomer.getCustomerId() == newCustomer.getCustomerId());
        assertEquals(name, findedCustomer.getName());
        assertEquals(login, findedCustomer.getPhone());
        assertEquals(password, findedCustomer.getPassword());

        // Delete created customer from database
        DeleteCustomerCommand deleteCustomerCommand = new DeleteCustomerCommand(newCustomer.getCustomerId());
        commandExecutorImpl.execute(deleteCustomerCommand);
    }

    @Test
    public void updateCustomer() throws Exception {

        String name      =  "Oleg Efremovs";
        String login     =  "loginOleg4";
        String password  =  "passwordOleg4";

        String newName     =  "Oleg Efremovs (new)";
        String newLogin    =  "loginOleg4 (new)";
        String newPassword =  "passwordOleg4 (new)";

        CreateCustomerCommand createCustomerCommand = new CreateCustomerCommand(name, login, password);
        CustomerOperationResult customerOperationResult = commandExecutorImpl.execute(createCustomerCommand);
        CustomerDTO newCustomer = customerOperationResult.getCustomer();

        UpdateCustomerCommand updateCustomerCommand = new UpdateCustomerCommand(newCustomer.getCustomerId(), newName, newLogin, newPassword);
        customerOperationResult = commandExecutorImpl.execute(updateCustomerCommand);
        CustomerDTO updatedCustomer = customerOperationResult.getCustomer();

        assertNotNull(updatedCustomer);
        assertTrue(updatedCustomer.getCustomerId() == newCustomer.getCustomerId());
        assertEquals(newName, updatedCustomer.getName());
        assertEquals(newLogin, updatedCustomer.getPhone());
        assertEquals(newPassword, updatedCustomer.getPassword());

        // Delete created customer from database
        DeleteCustomerCommand deleteCustomerCommand = new DeleteCustomerCommand(newCustomer.getCustomerId());
        commandExecutorImpl.execute(deleteCustomerCommand);
    }


    @Test
    public void CheckAuthorization() throws Exception {

        String name     =  "Oleg Efremovs";
        String login    =  "loginOleg5";
        String password =  "passwordOleg5";

        CreateCustomerCommand createCustomerCommand = new CreateCustomerCommand(name, login, password);
        CustomerOperationResult customerOperationResult = commandExecutorImpl.execute(createCustomerCommand);
        CustomerDTO newCustomer = customerOperationResult.getCustomer();

        CheckCustomerAuthorizationCommand checkCustomerAuthorizationCommand = new CheckCustomerAuthorizationCommand(login, password);
        customerOperationResult = commandExecutorImpl.execute(checkCustomerAuthorizationCommand);
        CustomerDTO customer = customerOperationResult.getCustomer();

        assertNotNull(customer);
        assertEquals(customer.getName(),     name);
        assertEquals(customer.getPhone(),    login);
        assertEquals(customer.getPassword(), password);

        // Delete created customer from database
        DeleteCustomerCommand deleteCustomerCommand = new DeleteCustomerCommand(newCustomer.getCustomerId());
        commandExecutorImpl.execute(deleteCustomerCommand);
    }

}