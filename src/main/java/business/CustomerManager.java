package business;


import dao.CustomerDAO;
import dao.jdbc.CustomerDAOImpl;
import dao.jdbc.DBConnection;
import entity.Customer;

import java.sql.SQLException;

/** Проект etaxi
 * Created by D.Lazorkin on 25.03.2016.
 * Класс для реализации функций со стороны клиента
 */
public class CustomerManager {

    private CustomerDAO customerDAO;

    public CustomerManager() {
        DBConnection dbService = new DBConnection();
        this.customerDAO = new CustomerDAOImpl(dbService.getConnection(), dbService.getDatabaseName());
    }

    public Customer findCustomerByLogin(String login) throws SQLException {

        return customerDAO.getByLogin(login);

    }

    public Customer findCustomerById(long Id) throws SQLException {

        return customerDAO.getById(Id);

    }

    public void createNewCustomer(Customer customer) throws SQLException {

        customer.setCustomerId(customerDAO.update(customer));

    }

    public void updateCustomer(Customer customer) throws SQLException {

        customerDAO.update(customer);

    }

}

