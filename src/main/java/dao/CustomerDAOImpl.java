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
public class CustomerDAOImpl implements CustomerDAO {

    private Executor executor;

    public CustomerDAOImpl(Connection connection, String databaseName) {

        this.executor = new Executor(connection, databaseName);

    }

    public CustomerDataSet getById(long id) throws SQLException {
        return executor.executeQuery("select * from customers where Id=" + id, resultSet -> {
            resultSet.next();
            return new CustomerDataSet(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3),
                                       resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));

        });
    }


    public long update(CustomerDataSet customer) throws SQLException {

        if (customer.getCustomerId() > 0) {
            return executor.executeUpdate("UPDATE customers SET " +
                                   " name = '" + customer.getName() + "'," +
                                   " phone = '" + customer.getPhone() + "'," +
                                   " login = '" + customer.getLogin() + "'," +
                                   " password = '" + customer.getPassword() + "'" +
                                   " tariff = '" + customer.getTariff() + "'," +
                                   " WHERE id=" + customer.getCustomerId());
        }
        else {
            return executor.executeUpdate("INSERT INTO customers (name, phone, login, password, tariff) VALUES (" +
                                    "'" + customer.getName() + "'," +
                                    "'" + customer.getPhone() + "'," +
                                    "'" + customer.getLogin() + "'," +
                                    "'" + customer.getPassword() + "'," +
                                    "'" + customer.getTariff() + "')");
        }

    }

    public void delete(CustomerDataSet customer) throws SQLException {

        Connection connection = executor.getConnection();
        try {
            connection.setAutoCommit(false);
            executor.executeUpdate("DELETE FROM customers WHERE Id=" + customer.getCustomerId());
            executor.executeUpdate("DELETE FROM orders WHERE orders.customerId=" + customer.getCustomerId());
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

    public List<CustomerDataSet> getAll() throws SQLException {
        return executor.executeQuery("select * from customers ",
                resultSet -> {
                    List<CustomerDataSet> list = new ArrayList<CustomerDataSet>();
                    while (resultSet.next()) {
                        list.add(new CustomerDataSet(resultSet.getLong(1),
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
        executor.executeUpdate("CREATE TABLE IF NOT EXISTS customers(" +
                            "   Id bigint(9) NOT NULL auto_increment," +
                            "   name varchar(256)," +
                            "   phone varchar(256)," +
                            "   login varchar(256)," +
                            "   password varchar(256)," +
                            "   tariff varchar(256)," +
                            "   PRIMARY KEY (Id)" +
                            "   );");
    }


}
