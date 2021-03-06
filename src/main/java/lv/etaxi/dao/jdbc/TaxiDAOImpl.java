package lv.etaxi.dao.jdbc;

import lv.etaxi.dao.DBConnection;
import lv.etaxi.dao.Executor;
import lv.etaxi.dao.TaxiDAO;
import lv.etaxi.entity.Taxi;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** Проект etaxi
 * Реализация управления объектами класса Taxi
 * */
@Repository
public class TaxiDAOImpl implements TaxiDAO {

    public long create(Taxi taxi) throws SQLException {

        Executor executor = GetExecutor();
            return executor.executeUpdate("INSERT INTO taxis (name, car, phone, login, password) VALUES (" +
                    "'" + taxi.getName() + "'," +
                    "'" + taxi.getCar() + "'," +
                    "'" + taxi.getPhone() + "'," +
                    "'" + taxi.getLogin() + "'," +
                    "'" + taxi.getPassword() + "')");
    }

    public void update(Taxi taxi) throws SQLException {

        Executor executor = GetExecutor();
         executor.executeUpdate("UPDATE taxis SET " +
                    " name = '" + taxi.getName() + "'," +
                    " car = '" + taxi.getCar() + "'," +
                    " phone = '" + taxi.getPhone() + "'," +
                    " login = '" + taxi.getLogin() + "'," +
                    " password = '" + taxi.getPassword() + "'" +
                    " WHERE id=" + taxi.getTaxiId());
    }

    public void delete(Taxi taxi) throws SQLException {

        Executor executor = GetExecutor();
        Connection connection = executor.getConnection();
        try {
            connection.setAutoCommit(false);
            executor.executeUpdate("DELETE FROM taxis WHERE Id=" + taxi.getTaxiId());
            executor.executeUpdate("DELETE FROM orders WHERE orders.taxiId=" + taxi.getTaxiId());
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


    public List<Taxi> getAll() throws SQLException {
        Executor executor = GetExecutor();
        return executor.executeQuery("select Id, name, car, phone, login, password from taxis ",
                resultSet -> {
                    List<Taxi> list = new ArrayList<Taxi>();
                    while (resultSet.next()) {
                        list.add(new Taxi(resultSet.getLong(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                resultSet.getString(6)));
                    }
                    return list;
                }
        );
    }

    public Taxi getById(long id) throws SQLException {
        Executor executor = GetExecutor();
        return executor.executeQuery("select Id, name, car, phone, login, password from taxis where Id=" + id, resultSet -> {
            resultSet.next();
            return new Taxi(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
        });
    }


    public Taxi getByLogin(String login) throws SQLException {
        Executor executor = GetExecutor();
        return executor.executeQuery("select * from taxis where login = '" + login + "'", resultSet -> {
            resultSet.next();
            return new Taxi(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));

        });
    }

    private Executor GetExecutor() {

        DBConnection dbService = new DBConnection();
        return (new Executor(dbService.getConnection(), dbService.getDatabaseName()));

    }

}
