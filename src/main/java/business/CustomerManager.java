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

    public void updateCustomerInDataBase(Customer customer) throws SQLException {

        customerDAO.update(customer);

    }

    public Customer CheckAuthorization(String login, String password) {

        Customer customer = null;
        try {
            customer = findCustomerByLogin(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (customer != null) {
            if (!customer.getPassword().equals(password)) {
                return null;
            }
        }

        return customer;
    }


    public boolean checkCustomerByLogin(Customer customer) {
        try {
            Customer presentCustomerWithSuchLogin = findCustomerByLogin(customer.getPhone());
            if ((presentCustomerWithSuchLogin != null)
                    && (presentCustomerWithSuchLogin.getCustomerId() != customer.getCustomerId())) {
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public boolean updateCustomer(Customer customer) {
        try {
            updateCustomerInDataBase(customer);
            return true;
        }
        catch (SQLException e) {
            return false;
        }
    }

}

