package lv.etaxi.business.managers;

import lv.etaxi.business.AdminManager;
import lv.etaxi.dao.AdminDAO;
import lv.etaxi.dao.DBException;
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
    public String create(Admin admin) throws SQLException {

        if (!checkByLogin(admin)) {
            return "You can't use such login! The admin with such login already exists!";
        } else {
            createNewInDataBase(admin);
            return "";
        }
    }

    @Transactional
    public void delete(Admin admin) throws SQLException {

        adminDAO.delete(admin);
    }

    @Transactional
    public String update(Admin admin) throws SQLException {

        if (!checkByLogin(admin)) {
            return "You can't use such login! The admin with such login already exists!";
        } else {
            updateAdminInDataBase(admin);
            return "";
        }
    }

    @Transactional
    public Admin findById(long Id) throws SQLException {

        return adminDAO.getById(Id);
    }

    @Transactional
    public Admin findByLogin(String login) throws SQLException {

        return adminDAO.getByLogin(login);
    }

    @Transactional
    public void createNewInDataBase(Admin admin) throws SQLException {

        admin.setAdminId(adminDAO.create(admin));
    }

    @Transactional
    private void updateAdminInDataBase(Admin admin) throws SQLException {

        adminDAO.update(admin);
    }

    @Transactional
    public Admin CheckAuthorization(String login, String password) throws SQLException {

        Admin admin = null;
        admin = findByLogin(login);

        if (admin != null) {
            if (!admin.getPassword().equals(password)) {
                return null;
            }
        }

        return admin;
    }

    @Transactional
    public boolean checkByLogin(Admin admin) throws SQLException {
        Admin presentAdminWithSuchLogin = findByLogin(admin.getLogin());
        if ((presentAdminWithSuchLogin != null)
                && (presentAdminWithSuchLogin.getAdminId() != admin.getAdminId())) {
            return false;
        }
        return true;
    }
}
