package lv.etaxi.dao.jdbc;

import lv.etaxi.dao.CustomerDAO;
import lv.etaxi.dao.DBConnection;
import lv.etaxi.dao.Executor;
import lv.etaxi.entity.Customer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** Проект etaxi
 * Реализация управления объектами класса Customer
 * */

@Component("JDBCCustomerDAO")
@Repository
public class CustomerDAOImpl implements CustomerDAO {

    public Customer getById(long id) throws SQLException {

        Executor executor = GetExecutor();
        return executor.executeQuery("select * from customers where Id=" + id, resultSet -> {
            resultSet.next();
            return new Customer(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3),
                                       resultSet.getString(4));

        });
    }

    public Customer getByLogin(String phone) throws SQLException {

        Executor executor = GetExecutor();
        return executor.executeQuery("select * from customers where phone = '" + phone + "'", resultSet -> {
            if (resultSet.next()) {
                return new Customer(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4));
            }
            else return null;
        });
    }

    public long update(Customer customer) throws SQLException {

        Executor executor = GetExecutor();
        if (customer.getCustomerId() > 0) {
            return executor.executeUpdate("UPDATE customers SET " +
                                   " name = '" + customer.getName() + "'," +
                                   " phone = '" + customer.getPhone() + "'," +
                                   " password = '" + customer.getPassword() + "'" +
                                   " WHERE id=" + customer.getCustomerId());
        }
        else {
            return executor.executeUpdate("INSERT INTO customers (name, phone, password) VALUES (" +
                                    "'" + customer.getName() + "'," +
                                    "'" + customer.getPhone() + "'," +
                                    "'" + customer.getPassword() + "')");
        }

    }

    public void delete(Customer customer) throws SQLException {

        Executor executor = GetExecutor();
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

    public List<Customer> getAll() throws SQLException {
        Executor executor = GetExecutor();
        return executor.executeQuery("select * from customers ",
                resultSet -> {
                    List<Customer> list = new ArrayList<Customer>();
                    while (resultSet.next()) {
                        list.add(new Customer(resultSet.getLong(1),
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
        executor.executeUpdate("CREATE TABLE IF NOT EXISTS customers(" +
                            "   Id bigint(9) NOT NULL auto_increment," +
                            "   name varchar(256)," +
                            "   phone varchar(256)," +
                            "   password varchar(256)," +
                            "   PRIMARY KEY (Id)" +
                            "   );");
    }

    private Executor GetExecutor() {

        DBConnection dbService = new DBConnection();
        return (new Executor(dbService.getConnection(), dbService.getDatabaseName()));

    }

}
