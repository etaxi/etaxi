package dao.jdbc;

import dao.TaxiDAO;
import entity.Taxi;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** Проект etaxi
 * Реализация управления объектами класса Taxi
 * */
public class TaxiDAOImpl implements TaxiDAO {

    private Executor executor;

    public TaxiDAOImpl(Connection connection, String databaseName) {

        this.executor = new Executor(connection, databaseName);

    }

    public Taxi getById(long id) throws SQLException {
        return executor.executeQuery("select Id, name, car, phone, login, password from taxis where Id=" + id, resultSet -> {
            resultSet.next();
            return new Taxi(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
        });
    }

    public Taxi getByLogin(String login) throws SQLException {
        return executor.executeQuery("select * from taxis where login = '" + login + "'", resultSet -> {
            resultSet.next();
            return new Taxi(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));

        });
    }

    public long update(Taxi taxi) throws SQLException {

        if (taxi.getTaxiId() > 0) {
            return executor.executeUpdate("UPDATE taxis SET " +
                    " name = '" + taxi.getName() + "'," +
                    " car = '" + taxi.getCar() + "'," +
                    " phone = '" + taxi.getPhone() + "'," +
                    " login = '" + taxi.getLogin() + "'," +
                    " password = '" + taxi.getPassword() + "'" +
                    " WHERE id=" + taxi.getTaxiId());
        }
        else {
            return executor.executeUpdate("INSERT INTO taxis (name, car, phone, login, password) VALUES (" +
                    "'" + taxi.getName() + "'," +
                    "'" + taxi.getCar() + "'," +
                    "'" + taxi.getPhone() + "'," +
                    "'" + taxi.getLogin() + "'," +
                    "'" + taxi.getPassword() + "')");
        }

    }

    public void delete(Taxi taxi) throws SQLException {

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

    public void createTable() throws SQLException {
        executor.executeUpdate("CREATE TABLE IF NOT EXISTS taxis (" +
                "  Id bigint(9) NOT NULL auto_increment," +
                "  name varchar(256)," +
                "  phone varchar(256)," +
                "  taxiStatus int(1)," +
                "  location varchar(256)," +
                "  car varchar(256)," +
                "  login varchar(256)," +
                "  password varchar(256)," +
                "  rating double," +
                "  PRIMARY KEY  (Id)" +
                ");");
    }

}