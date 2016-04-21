package lv.etaxi.business.managers;

import lv.etaxi.business.AdminManager;
import lv.etaxi.dao.AdminDAO;
import lv.etaxi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;

/**
 * Created by Genady Zalesky on 29.03.2016
 */
@Service
public class AdminManagerImpl implements AdminManager {

    @Autowired
    private AdminDAO adminDAO;


    @Transactional
    public Admin findAdminByLogin(String login) throws SQLException {

        return adminDAO.getByLogin(login);
    }

    @Transactional
    public Admin findAdminById(long Id) throws SQLException {

        return adminDAO.getById(Id);
    }

    @Transactional
    public void createNewAdminInDataBase(Admin admin) throws SQLException {

        admin.setAdminId(adminDAO.update(admin));
    }

    @Transactional
    public String updateAdmin(Admin admin) {

        if (!checkAdminByLogin(admin)) {
            return "You can't use such login! The admin with such login already exists!";
        } else {
            try {
                updateAdminInDataBase(admin);
                return "";
            } catch (SQLException e) {}
        }
        return "Data update failed! Please try again!";
    }

    @Transactional
    private void updateAdminInDataBase(Admin admin) throws SQLException {

        adminDAO.update(admin);
    }

    @Transactional
    public Admin CheckAuthorization(String login, String password) {

        Admin admin = null;
        try {
            admin = findAdminByLogin(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (admin != null) {
            if (!admin.getPassword().equals(password)) {
                return null;
            }
        }

        return admin;
    }

    @Transactional
    public String createNewAdmin(Admin admin) {

        if (!checkAdminByLogin(admin)) {
            return "You can't use such login! The admin with such login already exists!";
        } else {
            try {
                createNewAdminInDataBase(admin);
                return "";
            } catch (SQLException e) {}
        }
        return  "Registration failed! Please try again!";
    }

    @Transactional
    public boolean checkAdminByLogin(Admin admin) {
        try {
            Admin presentAdminWithSuchLogin = findAdminByLogin(admin.getLogin());
            if ((presentAdminWithSuchLogin != null)
                    && (presentAdminWithSuchLogin.getAdminId() != admin.getAdminId())) {
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
}
