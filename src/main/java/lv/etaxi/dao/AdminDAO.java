package lv.etaxi.dao;

import lv.etaxi.entity.Admin;

import java.sql.SQLException;

/** Проект etaxi
 * Интерфейс для реализации управления объектами класса Admin
 */

public interface AdminDAO extends BaseDAO<Admin>{

    /** Возвращает объект соответствующий записи по логину */
    Admin getByLogin(String login) throws SQLException;

}