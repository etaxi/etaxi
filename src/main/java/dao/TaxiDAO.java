package dao;

import dataSets.TaxiDataSet;
import executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** Проект etaxi
 * Реализация управления объектами класса TaxiDataSet
 * */
public class TaxiDAO {

    private Executor executor;

    public TaxiDAO(Connection connection) {

        this.executor = new Executor(connection);

    }

    public TaxiDataSet getById(long id) throws SQLException {
        executor.executeUpdate("USE etaxi;");
        return executor.executeQuery("select * from taxis where Id=" + id, resultSet -> {
            resultSet.next();
            return new TaxiDataSet(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), resultSet.getString(5));

        });
    }


    public long update(TaxiDataSet taxi) throws SQLException {

        executor.executeUpdate("USE etaxi;");
        if (taxi.getTaxiId() > 0) {
            return executor.executeUpdate("UPDATE taxis SET " +
                    " name = '" + taxi.getName() + "'," +
                    " phone = '" + taxi.getPhone() + "'," +
                    " login = '" + taxi.getLogin() + "'," +
                    " password = '" + taxi.getPassword() + "'" +
                    " WHERE id=" + taxi.getTaxiId());
        }
        else {
            return executor.executeUpdate("INSERT INTO taxis (name, phone, login, password) VALUES (" +
                    "'" + taxi.getName() + "'," +
                    "'" + taxi.getPhone() + "'," +
                    "'" + taxi.getLogin() + "'," +
                    "'" + taxi.getPassword() + "')");
        }

    }

    public void delete(TaxiDataSet taxi) throws SQLException {
        executor.executeUpdate("USE etaxi;");
        executor.executeUpdate("delete from taxis where Id=" + taxi.getTaxiId());
    }

    public List<TaxiDataSet> getALL() throws SQLException {
        executor.executeUpdate("USE etaxi;");
        return executor.executeQuery("select * from taxis ",
                resultSet -> {
                    List<TaxiDataSet> list = new ArrayList<TaxiDataSet>();
                    while (resultSet.next()) {
                        list.add(new TaxiDataSet(resultSet.getLong(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5)));
                    }
                    return list;
                }
        );
    }


    public void createTable() throws SQLException {
        executor.executeUpdate("USE etaxi;");
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
