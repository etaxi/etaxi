package lv.etaxi.dao;

import lv.etaxi.entity.Admin;

import java.sql.SQLException;
import java.util.List;

/** Проект etaxi
 * Интерфейс для реализации управления объектами класса Admin
 */

public interface AdminDAO extends BaseDAO<Admin>{

    /** Возвращает объект соответствующий записи по логину */
    Admin getByLogin(String login) throws SQLException;

    /** Создает таблицу в базе данных для хранения объектов класса Admin */
    void createTable() throws SQLException;
}