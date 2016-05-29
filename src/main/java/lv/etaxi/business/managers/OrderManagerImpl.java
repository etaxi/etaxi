package lv.etaxi.business.managers;

import lv.etaxi.business.OrderManager;
import lv.etaxi.business.direction.Direction;
import lv.etaxi.dao.OrderDAO;
import lv.etaxi.entity.Customer;
import lv.etaxi.entity.Order;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/** Проект etaxi
 * Created by D.Lazorkin on 25.03.2016.
 * Интерфейс для реализации функций над заказами
 */
@Service
public class OrderManagerImpl implements OrderManager {

    @Autowired
    private OrderDAO orderDAO;

    @Transactional
    public void create(Order order) throws SQLException {

        order.setOrderId(orderDAO.create(order));
    }

    @Transactional
    public void delete(Order order) throws SQLException {

        orderDAO.delete(order);
    }

    @Transactional
    public void update(Order order) throws SQLException {

        orderDAO.update(order);
    }

    @Transactional
    public Order findById(long Id) throws SQLException {
        return orderDAO.getById(Id);
    }

    @Transactional
    public Order findById(String orderId) {
        try {
            return findById(Long.valueOf(orderId));
        } catch (SQLException e) {
            return null;
        }
    }

    @Transactional
    public List<Order> getOrdersByCustomerId(long id, Timestamp begin, Timestamp end) throws SQLException {

        return orderDAO.getCustomerOrders(id, begin, end);
    }

    @Transactional
    public List<Order> getOpenOrdersOfCustomer(long id, Timestamp begin, Timestamp end) throws SQLException {

        return orderDAO.getOpenOrdersOfCustomer(id, begin, end);
    }

    @Transactional
    public List<Order> getCompletedOrdersOfCustomer(long id, Timestamp begin, Timestamp end) throws SQLException {

        return  orderDAO.getCompletedOrdersOfCustomer(id, begin, end);
    }

    @Transactional
    public List<Order> getTaxiOrders(long id) throws SQLException {

        return  orderDAO.getTaxiOrders(id);
    }

    @Transactional
    public List<Order> getOpenOrdersAll() throws SQLException {

        return  orderDAO.getOpenOrdersAll();
    }


    @Transactional
    public Order createNewInDataBase(Customer customer, String fromAddress, String toAddress, String orderedDateTime, String distance) {

        Order newOrder = new Order(null, customer.getCustomerId(),
                new Timestamp(new Date().getTime()),
                Timestamp.valueOf(orderedDateTime),
                Order.OrderStatus.WAITING,
                fromAddress, toAddress, null,
                Double.valueOf(distance), getPriceOfRide(Double.valueOf(distance)), 0, "");
        try {
            create(newOrder);
            return newOrder;
        } catch (SQLException e) {
            return null;
        }
    }

    @Transactional
    public boolean checkChangePossibility(Customer customer, Order order){
        return  (order.getCustomerId() == customer.getCustomerId());
    }

    @Transactional
    public boolean deleteOrderByIdByCustomer(Customer customer, String orderIdToDelete){

        Order currentOrder = findById(orderIdToDelete);
        if (currentOrder != null) {
            if (checkChangePossibility(customer, currentOrder)) {
                try {
                    delete(currentOrder);
                    return true;
                } catch (SQLException e) {}
            }
        }
        return false;
    }

    @Transactional
    public  boolean updateOrderByIdByCustomer(Customer customer, String orderIdToUpdate, String fromAddress,
                                              String toAddress, String orderedDateTime, String feedback, double distance, double price) {

        Order currentOrder = findById(orderIdToUpdate);
        if (currentOrder != null) {

            if (checkChangePossibility(customer, currentOrder)) {

                currentOrder.setFromAdress((fromAddress.isEmpty()) ? currentOrder.getFromAdress() : fromAddress);
                currentOrder.setToAdress((toAddress.isEmpty()) ? currentOrder.getToAdress() : toAddress);
                currentOrder.setOrderedDateTime((orderedDateTime.isEmpty()) ? currentOrder.getOrderedDateTime()
                        : Timestamp.valueOf(orderedDateTime));
                currentOrder.setFeedback((feedback.isEmpty()) ? currentOrder.getFeedback() : feedback);
                currentOrder.setDistance((distance == 0.0) ? currentOrder.getDistance() : distance);
                currentOrder.setPrice((price == 0.0) ? currentOrder.getPrice() : price);

                try {
                    update(currentOrder);
                    return true;
                } catch (SQLException e) {
                }
            }
        }

        return false;
    }

    @Transactional
    public double GetDistance(String addressFrom, String addressTo) throws JSONException {

        Direction direction = new Direction(addressFrom, addressTo);
        final Map<String, String> params = direction.putParameters();
        String url = direction.generateURL(params);
        JSONObject response = null;
        try {
            response = direction.getResponse(url);
        } catch (IOException e) {
            return 0;
        }
        JSONObject location = null;
        try {
            location = direction.getLocation(response);
        } catch (JSONException e) {
            return 0;
        }
        String distanceAsString = direction.distance(location);
        Double distance = Double.valueOf(distanceAsString.replaceAll("км","").replaceAll(",",".").trim());
        return distance;
    }

    public double getPriceOfRide(double distance) {
        return BigDecimal.valueOf((1.5 + distance * 0.3)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}

