package lv.etaxi.business;

import lv.etaxi.entity.Admin;

import java.sql.SQLException;

/** Проект etaxi
 * Created by D.Lazorkin on 08.04.2016.
 * Интерфейс для реализации функций со стороны администраторов
 */
public interface AdminManager {

    Admin findByLogin(String login) throws SQLException;

    Admin findById(long Id) throws SQLException;

    void createNewInDataBase(Admin admin) throws SQLException;

    String update(Admin admin);

    Admin CheckAuthorization(String login, String password);

    String create(Admin admin);

    boolean checkByLogin(Admin admin);

}
