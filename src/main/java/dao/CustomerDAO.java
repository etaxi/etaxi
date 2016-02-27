package dao;

import dataSets.CustomerDataSet;
import executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** Проект etaxi
 * Реализация управления объектами класса CustomerDataSet
 * */
public class CustomerDAO {

    private Executor executor;

    public CustomerDAO(Connection connection) {

        this.executor = new Executor(connection);

    }

    public CustomerDataSet getById(long id) throws SQLException {
        executor.executeUpdate("USE etaxi;");
        return executor.executeQuery("select * from customers where Id=" + id, resultSet -> {
            resultSet.next();
            return new CustomerDataSet(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3),
                                       resultSet.getString(4), resultSet.getString(5));

        });
    }


    public void update(CustomerDataSet customer) throws SQLException {

        executor.executeUpdate("USE etaxi;");
        if (customer.getCustomerId() > 0) {
            executor.executeUpdate("UPDATE customers SET " +
                                   " name = '" + customer.getName() + "'," +
                                   " phone = '" + customer.getPhone() + "'," +
                                   " login = '" + customer.getLogin() + "'," +
                                   " password = '" + customer.getPassword() + "'" +
                                   " WHERE id=" + customer.getCustomerId());
        }
        else {
            executor.executeUpdate("INSERT INTO customers (name, phone, login, password) VALUES (" +
                                    "'" + customer.getName() + "'," +
                                    "'" + customer.getPhone() + "'," +
                                    "'" + customer.getLogin() + "'," +
                                    "'" + customer.getPassword() + "')");
        }

    }

    public void delete(CustomerDataSet customer) throws SQLException {
        executor.executeUpdate("USE etaxi;");
        executor.executeUpdate("delete from customers where Id=" + customer.getCustomerId());
    }

    public List<CustomerDataSet> getALL() throws SQLException {
        executor.executeUpdate("USE etaxi;");
        return executor.executeQuery("select * from customers ",
                resultSet -> {
                    List<CustomerDataSet> list = new ArrayList<CustomerDataSet>();
                    while (resultSet.next()) {
                        list.add(new CustomerDataSet(resultSet.getLong(1),
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
        executor.executeUpdate("CREATE TABLE IF NOT EXISTS customers(" +
                            "   Id bigint(9) NOT NULL auto_increment," +
                            "   name varchar(256)," +
                            "   phone varchar(256)," +
                            "   login varchar(256)," +
                            "   password varchar(256)," +
                            "   PRIMARY KEY (Id)" +
                            "   );");
    }


}
