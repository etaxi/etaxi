package lv.etaxi.dao.jdbc;

import lv.etaxi.dao.DBConnection;
import lv.etaxi.dao.Executor;
import lv.etaxi.dao.OrderDAO;
import lv.etaxi.entity.Order;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/** Проект etaxi
 * Реализация управления объектами класса Order
 * */
@Repository
public class OrderDAOImpl implements OrderDAO {

    public long create(Order order) throws SQLException {

        Executor executor = GetExecutor();
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

    public void update(Order order) throws SQLException {

        Executor executor = GetExecutor();
         executor.executeUpdate("UPDATE orders SET " +
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

    public void delete(Order order) throws SQLException {
        Executor executor = GetExecutor();
        executor.executeUpdate("delete from orders where Id=" + order.getOrderId());
    }


    public List<Order> getAll() throws SQLException {
        Executor executor = GetExecutor();
        return executor.executeQuery("select * from orders ORDER BY ordereddatetime ASC",
                resultSet -> addOrderToListFromResultSet(resultSet)
        );
    }

    public Order getById(long id) throws SQLException {
        Executor executor = GetExecutor();
        return executor.executeQuery("select * from orders where Id=" + id, resultSet -> {
            resultSet.next();
            return new Order((Long) resultSet.getObject(1), (Long) resultSet.getObject(2), resultSet.getTimestamp(3), resultSet.getTimestamp(4),
                    Order.DetermineOrderStatus(resultSet.getString(5)), resultSet.getString(6),
                    resultSet.getString(7), (Long) resultSet.getObject(8), resultSet.getDouble(9), resultSet.getDouble(10),
                    resultSet.getInt(11), resultSet.getString(12));
        });
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
            list.add(new Order((Long) resultSet.getObject(1),
                    (Long) resultSet.getObject(2),
                    resultSet.getTimestamp(3),
                    resultSet.getTimestamp(4),
                    Order.DetermineOrderStatus(resultSet.getString(5)),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    (Long) resultSet.getObject(8),
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

    private Executor GetExecutor() {

        DBConnection dbService = new DBConnection();
        return (new Executor(dbService.getConnection(), dbService.getDatabaseName()));

    }

}
