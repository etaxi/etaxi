package dao;

import entity.Admin;
import entity.Taxi;

import java.sql.SQLException;
import java.util.List;

/** Проект etaxi
 * Интерфейс для реализации управления объектами класса Admin
 */

public interface AdminDAO {

    /** Возвращает объект соответствующий записи с первичным ключом key или null */
    Taxi getById(long id) throws SQLException;

    /** Возвращает объект соответствующий записи по логину */
    Taxi getByLogin(String login) throws SQLException;

    /** Сохраняет состояние объекта Admin в базе данных (если ID нет, создаем новую запись) */
    long update(Admin admin) throws SQLException;

    /** Удаляет запись об объекте Admin из базы данных */
    void delete(Admin admin) throws SQLException;

    /** Возвращает список объектов Admin соответствующих всем записям в базе данных */
    List<Admin> getAll() throws SQLException;

    /** Создает таблицу в базе данных для хранения объектов класса Admin */
    void createTable() throws SQLException;
}
