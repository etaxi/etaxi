package lv.etaxi.dao.jdbc;

import lv.etaxi.dao.AdminDAO;
import lv.etaxi.entity.Admin;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** Проект etaxi
 * Реализация управления объектами класса Admin
 * Created by Genady Zalesky on 27.03.2016
 */
@Repository
public class AdminDAOImpl implements AdminDAO{


    public Admin getById(long id) throws SQLException {
        Executor executor = GetExecutor();
        return executor.executeQuery("select * from admins where Id=" + id, resultSet -> {
            resultSet.next();
            return new Admin(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4));

        });
    }

    public Admin getByLogin(String login) throws SQLException {
        Executor executor = GetExecutor();
        return executor.executeQuery("select * from admins where login = '" + login + "'", resultSet -> {
            if (resultSet.next()) {
                return new Admin(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4));
            }
            else return null;
        });
    }

    public long update(Admin admin) throws SQLException {

        Executor executor = GetExecutor();
        if (admin.getAdminId() > 0) {
            return executor.executeUpdate("UPDATE admins SET " +
                    " name = '" + admin.getName() + "'," +
                    " login = '" + admin.getLogin() + "'," +
                    " password = '" + admin.getPassword() + "'" +
                    " WHERE id=" + admin.getAdminId());
        }
        else {
            return executor.executeUpdate("INSERT INTO admins (name, login, password) VALUES (" +
                    "'" + admin.getName() + "'," +
                    "'" + admin.getLogin() + "'," +
                    "'" + admin.getPassword() + "')");
        }
    }

    public void delete(Admin admin) throws SQLException {

        Executor executor = GetExecutor();
        Connection connection = executor.getConnection();
        try {
            connection.setAutoCommit(false);
            executor.executeUpdate("DELETE FROM admins WHERE Id=" + admin.getAdminId());
            connection.commit();
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
                throw ignore;
            }
            throw exception;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
                throw ignore;
            }
        }
    }

    public List<Admin> getAll() throws SQLException {
        Executor executor = GetExecutor();
        return executor.executeQuery("select * from admins ",
                resultSet -> {
                    List<Admin> list = new ArrayList<Admin>();
                    while (resultSet.next()) {
                        list.add(new Admin(resultSet.getLong(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4)));
                    }
                    return list;
                }
        );
    }

    public void createTable() throws SQLException {
        Executor executor = GetExecutor();
        executor.executeUpdate("CREATE TABLE IF NOT EXISTS admins(" +
                "   Id bigint(9) NOT NULL auto_increment," +
                "   name varchar(256)," +
                "   login varchar(256)," +
                "   password varchar(256)," +
                "   PRIMARY KEY (Id)" +
                "   );");
    }

    private Executor GetExecutor() {

        DBConnection dbService = new DBConnection();
        return (new Executor(dbService.getConnection(), dbService.getDatabaseName()));

    }

}
