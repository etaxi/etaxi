package lv.etaxi.business;


import lv.etaxi.dao.DBException;
import lv.etaxi.entity.Customer;

import java.sql.SQLException;

/** Проект etaxi
 * Created by D.Lazorkin on 25.03.2016.
 * Интерфейс для реализации функций со стороны клиента - заказчика такси
 */
public interface CustomerManager {

    String create(Customer customer);

    void delete(Customer customer) throws SQLException;

    String update(Customer customer);

    Customer findById(long Id) throws SQLException;

    Customer findByLogin(String login) throws SQLException;

    void createNewInDataBase(Customer customer) throws SQLException;

    void updateInDataBase(Customer customer) throws SQLException;

    Customer CheckAuthorization(String login, String password);

    boolean checkByLogin(Customer customer);

}

