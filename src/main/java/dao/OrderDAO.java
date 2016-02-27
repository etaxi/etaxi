package dao;

import executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;

/** Проект etaxi
 * Реализация управления объектами класса OrderDataSet
 * */
public class OrderDAO implements OrderDAOinterface {

    private Executor executor;

    public OrderDAO(Connection connection) {

        this.executor = new Executor(connection);

    }

    public void createTable() throws SQLException {
        executor.executeUpdate("CREATE TABLE IF NOT EXISTS orders (" +
                "  orderId bigint(9) NOT NULL auto_increment," +
                "  customerId int(6)," +
                "  datetime datetime," +
                "  orderStatus int(1)," +
                "  fromAdress text," +
                "  toAdress text," +
                "  taxiId int(6)," +
                "  distance double," +
                "  price double," +
                "  rate int(1)," +
                "  feedback text," +
                "  PRIMARY KEY  (orderId)" +
                ");");
    }

}
