package lv.etaxi.business;


import lv.etaxi.entity.Customer;

import java.sql.SQLException;

/** Проект etaxi
 * Created by D.Lazorkin on 25.03.2016.
 * Интерфейс для реализации функций со стороны клиента - заказчика такси
 */
public interface CustomerManager {

    Customer findByLogin(String login) throws SQLException;

    Customer findById(long Id) throws SQLException;

    void createNewInDataBase(Customer customer) throws SQLException;

    void updateInDataBase(Customer customer) throws SQLException;

    Customer CheckAuthorization(String login, String password);

    boolean checkByLogin(Customer customer);

    String update(Customer customer);

    String create(Customer customer);

    void deleteByObject(Customer customer) throws SQLException;
}

