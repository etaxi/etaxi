package lv.etaxi.business.managers;

import lv.etaxi.business.CustomerManager;
import lv.etaxi.dao.CustomerDAO;
import lv.etaxi.dao.DBException;
import lv.etaxi.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Transactional
    public String create(Customer customer) {

        if (!checkByLogin(customer)) {
            return "You can't use such phone! The customer with such phone already present!";
        } else {
            try {
                createNewInDataBase(customer);
                return "";
            } catch (SQLException e) {
            }
        }
        return "Registration failed! Please try again!";
    }

    @Transactional
    public String update(Customer customer) {

        if (!checkByLogin(customer)) {
            return "You can't use such phone! The customer with such phone already present!";
        } else {
            try {
                updateInDataBase(customer);
                return "";
            } catch (SQLException e) {
            }
        }
        return "Data update failed! Please try again!";
    }

    @Transactional
    public void delete(Customer customer) throws SQLException {

        customerDAO.delete(customer);

    }

    @Transactional
    public Customer findById(long Id) throws SQLException {

        return customerDAO.getById(Id);
    }


    @Transactional
    public Customer findByLogin(String login) throws SQLException {

        return customerDAO.getByLogin(login);
    }

    @Transactional
    public void createNewInDataBase(Customer customer) throws SQLException {

        customer.setCustomerId(customerDAO.create(customer));
    }

    @Transactional
    public void updateInDataBase(Customer customer) throws SQLException {

        customerDAO.update(customer);
    }

    @Transactional
    public Customer CheckAuthorization(String login, String password) {

        Customer customer = null;
        try {
            customer = findByLogin(login);
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

    @Transactional
    public boolean checkByLogin(Customer customer) {
        try {
            Customer presentCustomerWithSuchLogin = findByLogin(customer.getPhone());
            if ((presentCustomerWithSuchLogin != null)
                    && (presentCustomerWithSuchLogin.getCustomerId() != customer.getCustomerId())) {
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
        return true;
    }



}
