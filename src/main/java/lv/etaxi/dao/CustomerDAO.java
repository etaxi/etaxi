package lv.etaxi.dao;

import lv.etaxi.entity.Customer;

import java.sql.SQLException;
import java.util.List;

/** Проект etaxi
 * Интерфейс для реализации управления объектами класса Customer
 * */
public interface CustomerDAO extends BaseDAO<Customer> {

    /** Возвращает объект соответствующий записи с таким логином (он же номер телефона для данной таблицы) */
    Customer getByLogin(String phone) throws SQLException;

    /** Создает таблицу в базе данных для хранения объектов класса Customer */
    void createTable() throws SQLException;
}
