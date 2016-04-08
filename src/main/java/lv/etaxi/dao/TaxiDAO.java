package lv.etaxi.dao;

import lv.etaxi.entity.Taxi;

import java.sql.SQLException;
import java.util.List;

/** Проект etaxi
 * Интерфейс для реализации управления объектами класса Taxi
 */
public interface TaxiDAO {

    /** Возвращает объект соответствующий записи с первичным ключом key или null */
    Taxi getById(long id) throws SQLException;

    /** Возвращает объект соответствующий записи по логину */
    Taxi getByLogin(String login) throws SQLException;

    /** Сохраняет состояние объекта Taxi в базе данных (если ID нет, создаем новую запись) */
    long update(Taxi customer) throws SQLException;

    /** Удаляет запись об объекте из базы данных */
    void delete(Taxi customer) throws SQLException;

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    List<Taxi> getAll() throws SQLException;

    /** Создает таблицу в базе данных для хранения объектов класса Taxi */
    void createTable() throws SQLException;

}

