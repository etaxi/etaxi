package lv.etaxi.business;


import lv.etaxi.entity.Customer;

import java.sql.SQLException;

/** Проект etaxi
 * Created by D.Lazorkin on 25.03.2016.
 * Интерфейс для реализации функций со стороны клиента - заказчика такси
 */
public interface CustomerManager {

    Customer findCustomerByLogin(String login) throws SQLException;

    Customer findCustomerById(long Id) throws SQLException;

    void createNewCustomerInDataBase(Customer customer) throws SQLException;

    void updateCustomerInDataBase(Customer customer) throws SQLException;

    Customer CheckAuthorization(String login, String password);

    boolean checkCustomerByLogin(Customer customer);

    public String updateCustomer(Customer customer);

    public String createNewCustomer(Customer customer);

}

