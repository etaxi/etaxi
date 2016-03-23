package dao.jdbc;

import dao.OrderDAO;
import entity.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** Проект etaxi
 * Реализация управления объектами класса Order
 * */
public class OrderDAOImpl implements OrderDAO {

    private Executor executor;

    public OrderDAOImpl(Connection connection, String databaseName) {

        this.executor = new Executor(connection, databaseName);

    }

    /**
     * Возвращает объект соответствующий записи с первичным ключом key или null
     */
    public Order getById(long id) throws SQLException {
        return executor.executeQuery("select * from orders where Id=" + id, resultSet -> {
            resultSet.next();
            return new Order(resultSet.getLong(1), resultSet.getLong(2), resultSet.getTimestamp(3),
                    Order.DetermineOrderStatus(resultSet.getString(4)), resultSet.getString(5),
                    resultSet.getString(6), resultSet.getLong(7), resultSet.getDouble(8), resultSet.getDouble(9),
                    resultSet.getInt(10), resultSet.getString(11));
        });
    }

    /**
     * Сохраняет состояние объекта Order в базе данных (если ID нет, создаем новую запись)
     */
    public long update(Order order) throws SQLException {

        if (order.getOrderId() > 0) {
            return executor.executeUpdate("UPDATE orders SET " +
                    " customerId = '" + order.getCustomerId() + "'," +
                    " datetime = '" + order.getDateTime() + "'," +
                    " orderStatus = '" + order.getOrderStatus().toString() + "'," +
                    " fromAdress = '" + order.getFromAdress() + "'," +
                    " toAdress = '" + order.getToAdress() + "'," +
                    " taxiId = '" + order.getTaxiId() + "'," +
                    " distance = '" + order.getDistance() + "'," +
                    " price = '" + order.getPrice() + "'," +
                    " rate = '" + order.getRate() + "'," +
                    " feedback = '" + order.getFeedback() + "'" +
                    " WHERE id=" + order.getOrderId());
        }
        else {
            return executor.executeUpdate("INSERT INTO orders (customerId, datetime, orderStatus, fromAdress, toAdress, " +
                    "taxiId, distance, price, rate, feedback) VALUES (" +
                    "'" + order.getCustomerId() + "'," +
                    "'" + order.getDateTime() + "'," +
                    "'" + order.getOrderStatus().toString() + "'," +
                    "'" + order.getFromAdress() + "'," +
                    "'" + order.getToAdress() + "'," +
                    "'" + order.getTaxiId() + "'," +
                    "'" + order.getDistance() + "'," +
                    "'" + order.getPrice() + "'," +
                    "'" + order.getRate() + "'," +
                    "'" + order.getFeedback() + "')");
        }
    }

    /**
     * Удаляет запись об объекте из базы данных
     */
    public void delete(Order order) throws SQLException {
        executor.executeUpdate("delete from orders where Id=" + order.getOrderId());
    }

    /**
     * Возвращает список объектов соответствующих всем записям в базе данных
     */
    public List<Order> getAll() throws SQLException {
        return executor.executeQuery("select * from orders ",
                resultSet -> {
                    List<Order> list = new ArrayList<Order>();
                    while (resultSet.next()) {
                        list.add(new Order(resultSet.getLong(1),
                                resultSet.getLong(2),
                                resultSet.getTimestamp(3),
                                Order.DetermineOrderStatus(resultSet.getString(4)),
                                resultSet.getString(5),
                                resultSet.getString(6),
                                resultSet.getLong(7),
                                resultSet.getDouble(8),
                                resultSet.getDouble(9),
                                resultSet.getInt(10),
                                resultSet.getString(11)));
                    }
                    return list;
                }
        );
    }


    public void createTable() throws SQLException {
        executor.executeUpdate("CREATE TABLE IF NOT EXISTS orders (" +
                "  Id bigint(9) NOT NULL auto_increment," +
                "  customerId bigint(9)," +
                "  datetime datetime," +
                "  orderStatus text," +
                "  fromAdress text," +
                "  toAdress text," +
                "  taxiId bigint(9)," +
                "  distance double," +
                "  price double," +
                "  rate int(2)," +
                "  feedback text," +
                "  PRIMARY KEY (Id)" +
                ");");
    }

}
