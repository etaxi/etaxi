package business;

import entity.Admin;

import java.sql.SQLException;

/** Проект etaxi
 * Created by D.Lazorkin on 08.04.2016.
 * Интерфейс для реализации функций со стороны администраторов
 */
public interface AdminManager {

    Admin findAdminByLogin(String login) throws SQLException;

    Admin findAdminById(long Id) throws SQLException;

    void createNewAdminInDataBase(Admin admin) throws SQLException;

    String updateAdmin(Admin admin);

    Admin CheckAuthorization(String login, String password);

    String createNewAdmin(Admin admin);

    boolean checkAdminByLogin(Admin admin);

}
