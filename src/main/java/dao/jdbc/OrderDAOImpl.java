package dao.jdbc;

import dao.OrderDAO;
import entity.Order;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/** Проект etaxi
 * Реализация управления объектами класса Order
 * */
@Component
public class OrderDAOImpl implements OrderDAO {

    /**
     * Возвращает объект соответствующий записи с первичным ключом key или null
     */
    public Order getById(long id) throws SQLException {
        Executor executor = GetExecutor();
        return executor.executeQuery("select * from orders where Id=" + id, resultSet -> {
            resultSet.next();
            return new Order(resultSet.getLong(1), resultSet.getLong(2), resultSet.getTimestamp(3), resultSet.getTimestamp(4),
                    Order.DetermineOrderStatus(resultSet.getString(5)), resultSet.getString(6),
                    resultSet.getString(7), resultSet.getLong(8), resultSet.getDouble(9), resultSet.getDouble(10),
                    resultSet.getInt(11), resultSet.getString(12));
        });
    }

    /**
     * Сохраняет состояние объекта Order в базе данных (если ID нет, создаем новую запись)
     */
    public long update(Order order) throws SQLException {

        Executor executor = GetExecutor();
        if (order.getOrderId() > 0) {
            return executor.executeUpdate("UPDATE orders SET " +
                    " customerId = '" + order.getCustomerId() + "'," +
                    " datetime = '" + order.getDateTime() + "'," +
                    " ordereddatetime = '" + order.getOrderedDateTime() + "'," +
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
            return executor.executeUpdate("INSERT INTO orders (customerId, datetime, ordereddatetime, orderStatus, " +
                    "fromAdress, toAdress, taxiId, distance, price, rate, feedback) VALUES (" +
                    "'" + order.getCustomerId() + "'," +
                    "'" + order.getDateTime() + "'," +
                    "'" + order.getOrderedDateTime() + "'," +
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
        Executor executor = GetExecutor();
        executor.executeUpdate("delete from orders where Id=" + order.getOrderId());
    }

    /**
     * Возвращает список объектов соответствующих всем записям в базе данных
     */
    public List<Order> getAll() throws SQLException {
        Executor executor = GetExecutor();
        return executor.executeQuery("select * from orders ORDER BY ordereddatetime ASC",
                resultSet -> addOrderToListFromResultSet(resultSet)
        );
    }

    public List<Order> getOpenOrdersAll() throws SQLException {
        Executor executor = GetExecutor();
        return executor.executeQuery("select * from orders where orderStatus='" + Order.OrderStatus.WAITING + "' " +
                "ORDER BY ordereddatetime ASC",
                resultSet -> addOrderToListFromResultSet(resultSet)
        );
    }

    public List<Order> getOpenOrdersOfCustomer(long customerId, Timestamp begin, Timestamp end) throws SQLException {
        Executor executor = GetExecutor();
        return executor.executeQuery("select * from orders where orderStatus='" + Order.OrderStatus.WAITING + "'" +
                        ((customerId != 0) ? " and customerId = " + customerId : "") + " " +
                        "AND (ordereddatetime  between '" + begin + "' AND '" + end + "') " +
                        "ORDER BY ordereddatetime ASC",
                resultSet -> addOrderToListFromResultSet(resultSet)
        );
    }

    public List<Order> getCompletedOrdersOfCustomer(long customerId, Timestamp begin, Timestamp end) throws SQLException {
        Executor executor = GetExecutor();
        return executor.executeQuery("select * from orders where orderStatus='" + Order.OrderStatus.WAITING + "'" +  //DELIVERED
                        ((customerId != 0) ? " and customerId = " + customerId : "") + " " +
                        "AND (ordereddatetime  between '" + begin + "' AND '" + end + "') " +
                        "ORDER BY ordereddatetime ASC",
                resultSet -> addOrderToListFromResultSet(resultSet)
        );
    }

    public List<Order> getTaxiOrders(long id) throws SQLException {
        Executor executor = GetExecutor();
        return executor.executeQuery("select * from orders where taxiId=" + id + " " +
                "ORDER BY ordereddatetime ASC",
                resultSet -> addOrderToListFromResultSet(resultSet)
        );
    }

    private List<Order> addOrderToListFromResultSet(ResultSet resultSet) throws SQLException {

        List<Order> list = new ArrayList<Order>();
        while (resultSet.next()) {
            list.add(new Order(resultSet.getLong(1),
                    resultSet.getLong(2),
                    resultSet.getTimestamp(3),
                    resultSet.getTimestamp(4),
                    Order.DetermineOrderStatus(resultSet.getString(5)),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getLong(8),
                    resultSet.getDouble(9),
                    resultSet.getDouble(10),
                    resultSet.getInt(11),
                    resultSet.getString(12)));
        }
        return list;
    }

    public List<Order> getCustomerOrders(long id, Timestamp begin, Timestamp end) throws SQLException {
        Executor executor = GetExecutor();
        return executor.executeQuery("select * from orders where customerId=" + id + " " +
                                     "AND (ordereddatetime  between '" + begin + "' AND '" + end + "') " +
                                     "ORDER BY ordereddatetime ASC",
                resultSet -> addOrderToListFromResultSet(resultSet)
        );
    }

    public void createTable() throws SQLException {
        Executor executor = GetExecutor();
        executor.executeUpdate("CREATE TABLE IF NOT EXISTS orders (" +
                "  Id bigint(9) NOT NULL auto_increment," +
                "  customerId bigint(9)," +
                "  datetime datetime," +
                "  ordereddatetime datetime," +
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

    private Executor GetExecutor() {

        DBConnection dbService = new DBConnection();
        return (new Executor(dbService.getConnection(), dbService.getDatabaseName()));

    }

}
