package lv.etaxi.dao;

import lv.etaxi.entity.Customer;

import java.sql.SQLException;

/** Проект etaxi
 * Интерфейс для реализации управления объектами класса Customer
 * */
public interface CustomerDAO extends BaseDAO<Customer> {

    /** Возвращает объект соответствующий записи с таким логином (он же номер телефона для данной таблицы) */
    Customer getByLogin(String phone) throws SQLException;

}
