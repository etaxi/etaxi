package lv.etaxi.business.managers;

import lv.etaxi.business.CustomerManager;
import lv.etaxi.dao.CustomerDAO;
import lv.etaxi.dao.hibernate.CustomerHibernateDAOImpl;
import lv.etaxi.dao.jdbc.CustomerDAOImpl;
import lv.etaxi.dao.jdbc.DBConnection;
import lv.etaxi.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

/**
 * Проект etaxi
 * Created by D.Lazorkin on 25.03.2016.
 * Класс для реализации функций со стороны клиента
 */

@Service
public class CustomerManagerImpl implements CustomerManager {

    //@Qualifier("customerHibernateDAOImpl")
    @Autowired
    private CustomerDAO customerDAO;

    public CustomerManagerImpl() {
        this.customerDAO = (DBConnection.getDatabasePropertyFromFile("db.hibernate").equals("YES")) ?
                            new CustomerHibernateDAOImpl() : new CustomerDAOImpl();
    }

    public Customer findCustomerByLogin(String login) throws SQLException {

        return customerDAO.getByLogin(login);

    }

    public Customer findCustomerById(long Id) throws SQLException {

        return customerDAO.getById(Id);

    }

    @Transactional
    public void createNewCustomerInDataBase(Customer customer) throws SQLException {

        customer.setCustomerId(customerDAO.update(customer));

    }

    @Transactional
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

    @Transactional
    public String updateCustomer(Customer customer) {

        if (!checkCustomerByLogin(customer)) {
            return "You can't use such phone! The customer with such phone already present!";
        } else {
            try {
                updateCustomerInDataBase(customer);
                return "";
            } catch (SQLException e) {
            }
        }
        return "Data update failed! Please try again!";
    }

    @Transactional
    public String createNewCustomer(Customer customer) {

        if (!checkCustomerByLogin(customer)) {
            return "You can't use such phone! The customer with such phone already present!";
        } else {
            try {
                createNewCustomerInDataBase(customer);
                return "";
            } catch (SQLException e) {
            }
        }
        return "Registration failed! Please try again!";
    }

    @Transactional
    public void deleteCustomer(Customer customer) throws SQLException {

        customerDAO.delete(customer);

    }

}
