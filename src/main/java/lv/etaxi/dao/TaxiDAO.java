package lv.etaxi.dao;

import lv.etaxi.entity.Taxi;

import java.sql.SQLException;

/** Проект etaxi
 * Интерфейс для реализации управления объектами класса Taxi
 */
public interface TaxiDAO extends BaseDAO<Taxi>{

    /** Возвращает объект соответствующий записи по логину */
    Taxi getByLogin(String login) throws SQLException;

}

