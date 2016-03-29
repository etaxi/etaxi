package business.direction;

import dao.AdminDAO;
import dao.jdbc.AdminDAOImpl;
import dao.jdbc.DBConnection;
import entity.Admin;

import java.sql.SQLException;

/**
 * Created by Genady Zalesky on 29.03.2016
 */
public class AdminManager {

    private AdminDAO adminDAO;

    public AdminManager() {

        DBConnection dbService = new DBConnection();
        this.adminDAO = new AdminDAOImpl(dbService.getConnection(), dbService.getDatabaseName());
    }

    public Admin findAdminByLogin(String login) throws SQLException {

        return adminDAO.getByLogin(login);
    }

    public Admin findAdminById(long Id) throws SQLException {

        return adminDAO.getById(Id);
    }

    public void createNewAdmin(Admin admin) throws SQLException {

        admin.setAdminId(adminDAO.update(admin));
    }

    public void updateCustomer(Admin admin) throws SQLException {

        adminDAO.update(admin);
    }
}
