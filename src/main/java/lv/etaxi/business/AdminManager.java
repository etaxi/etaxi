package lv.etaxi.business;

import lv.etaxi.entity.Admin;

import java.sql.SQLException;

/** Проект etaxi
 * Created by D.Lazorkin on 08.04.2016.
 * Интерфейс для реализации функций со стороны администраторов
 */
public interface AdminManager {

    String create(Admin admin) throws SQLException;

    void delete(Admin admin) throws SQLException;

    String update(Admin admin) throws SQLException;

    Admin findById(long Id) throws SQLException;

    Admin findByLogin(String login) throws SQLException;

    void createNewInDataBase(Admin admin) throws SQLException;

    Admin CheckAuthorization(String login, String password);

    boolean checkByLogin(Admin admin);

}
