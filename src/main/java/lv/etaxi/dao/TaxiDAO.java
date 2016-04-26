package lv.etaxi.dao;

import lv.etaxi.entity.Taxi;

import java.sql.SQLException;
import java.util.List;

/** Проект etaxi
 * Интерфейс для реализации управления объектами класса Taxi
 */
public interface TaxiDAO extends BaseDAO<Taxi>{

    /** Возвращает объект соответствующий записи по логину */
    Taxi getByLogin(String login) throws SQLException;

    /** Создает таблицу в базе данных для хранения объектов класса Taxi */
    void createTable() throws SQLException;

}

