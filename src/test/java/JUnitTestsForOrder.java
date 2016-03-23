import dao.*;
import dao.jdbc.CustomerDAOImpl;
import dao.jdbc.OrderDAOImpl;
import dao.jdbc.TaxiDAOImpl;
import entity.Customer;
import entity.Order;
import entity.Taxi;
import org.junit.Test;
import dao.jdbc.DBService;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.assertTrue;

/** Проект etaxi
 * JUnit тесты для проекта etaxi (design patterns "Object Mother" and "Test Data Builder")
 * */

class OrderBuilder {

    public static final Long DEFAULT_ID = (long) 0;
    public static final Order.OrderStatus DEFAULT_ORDERSTATUS = Order.OrderStatus.WAITING;
    public static final String DEFAULT_FROMADRESS = "Riga, Elizabetes 123";
    public static final String DEFAULT_TOADRESS = "Riga, Skanstes 78b";
    public static final Double DEFAULT_DISTANCE = 100.00;
    public static final Double DEFAULT_PRICE = 34.00;
    public static final int DEFAULT_RATE = 0;
    public static final String DEFAULT_FEEDBACK = "Wonderful travel";

    private Long id = DEFAULT_ID;
    private Order.OrderStatus orderStatus = DEFAULT_ORDERSTATUS;
    private String fromAdress = DEFAULT_FROMADRESS;
    private String toAdress = DEFAULT_TOADRESS;
    private Double distance = DEFAULT_DISTANCE;
    private Double price = DEFAULT_PRICE;
    private int rate = DEFAULT_RATE;
    private String feedback = DEFAULT_FEEDBACK;
    private Timestamp dateTime = new Timestamp(new java.util.Date().getTime());
    private Long customerId = (long) 0;
    private Long taxiId = (long) 0;


    private OrderBuilder() {}

    public static OrderBuilder aOrder() {
        return new OrderBuilder();
    }

    public OrderBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public OrderBuilder withOrderStatus(Order.OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public OrderBuilder withFromAdress(String fromAdress) {
        this.fromAdress = fromAdress;
        return this;
    }

    public OrderBuilder withToAdress(String toAdress) {
        this.toAdress = toAdress;
        return this;
    }

    public OrderBuilder withDistance(Double distance) {
        this.distance = distance;
        return this;
    }

    public OrderBuilder withPrice(Double price) {
        this.price = price;
        return this;
    }

    public OrderBuilder withRate(int rate) {
        this.rate = rate;
        return this;
    }

    public OrderBuilder withFeedback(String feedback) {
        this.feedback = feedback;
        return this;
    }

    public OrderBuilder withDate(Timestamp dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public OrderBuilder withCustomerID(long customerId) {
        this.customerId = customerId;
        return this;
    }

    public OrderBuilder withTaxiID(long taxiId) {
        this.taxiId = taxiId;
        return this;
    }


    public OrderBuilder withNoOrderStatus() {
        this.orderStatus = null;
        return this;
    }


    public OrderBuilder but() {
        return OrderBuilder
                .aOrder()
                .withDate(dateTime)
                .withCustomerID(customerId)
                .withFromAdress(fromAdress)
                .withToAdress(toAdress)
                .withDistance(distance)
                .withFeedback(feedback)
                .withTaxiID(taxiId)
                .withPrice(price)
                .withOrderStatus(orderStatus)
                .withRate(rate);
    }

    public Order build() {
        return new Order(
                id, customerId, dateTime, orderStatus, fromAdress, toAdress, taxiId, distance, price, rate, feedback);
    }
}

public class JUnitTestsForOrder {

    private Timestamp getCurrentDate() {

        return new Timestamp(new java.util.Date().getTime());
    }

    public OrderDAO aOrderDAO() {

        DBService dbService = new DBService();
        return (new OrderDAOImpl(dbService.getConnection(), dbService.getDatabaseName()));

    }

    @Test
    public void testNewOrderRecord() throws SQLException {

        OrderBuilder orderBuilder = OrderBuilder.aOrder()
                .withFromAdress("Brivibas 123")
                .withToAdress("Terbatas 34a")
                .withPrice(12.45)
                .withDistance(8.9)
                .withDate(getCurrentDate())
                .withOrderStatus(Order.OrderStatus.DELIVERED);

        Order order = orderBuilder.build();
        long newOrderID = aOrderDAO().update(order);
    }


    @Test
    public void testNewOrdersRecords() throws SQLException {

        OrderDAO orderDAO = aOrderDAO();

        OrderBuilder orderBuilder = OrderBuilder.aOrder()
                .withFromAdress("Dzerbenes 14")
                .withToAdress("Stirnu 78k-3")
                .withPrice(7.6)
                .withDistance(9.9)
                .withDate(getCurrentDate())
                .withOrderStatus(Order.OrderStatus.WAITING);

        Order order1 = orderBuilder.build();
        long newOrderID1 = orderDAO.update(order1);

        Order order2 = orderBuilder.aOrder().build();  //USE "DEFAULT ORDER"
        long newOrderID2 = orderDAO.update(order2);

    }

    @Test
    public void testUpdateOrderRecord() throws SQLException {

        OrderDAO orderDAO = aOrderDAO();

        OrderBuilder orderBuilder = OrderBuilder.aOrder()
                .withFromAdress("Dzerbenes 89a")
                .withToAdress("Stirnu 18")
                .withPrice(5.0)
                .withDistance(12.0)
                .withDate(getCurrentDate())
                .withFeedback("Good driver!")
                .withOrderStatus(Order.OrderStatus.WAITING);

        Order order = orderBuilder.build();

        order.setOrderId(orderDAO.update(order));
        order.setOrderStatus(Order.OrderStatus.DELIVERED);
        orderDAO.update(order);
    }

    @Test
    public void testGetOrderByID() throws SQLException {

        OrderDAO orderDAO = aOrderDAO();

        OrderBuilder orderBuilder = OrderBuilder.aOrder()
                .withFromAdress("Dzerbenes 89a")
                .withToAdress("Stirnu 18")
                .withPrice(5.0)
                .withDistance(12.0)
                .withDate(getCurrentDate())
                .withOrderStatus(Order.OrderStatus.WAITING);

        Order order = orderBuilder.build();
        order.setOrderId(orderDAO.update(order));

        Order orderGetById = orderDAO.getById(order.getOrderId());
        assertTrue(order.getOrderId() == orderGetById.getOrderId());
    }

    @Test
    public void testDeleteOrderByID() throws SQLException {

        OrderDAO orderDAO = aOrderDAO();

        Order order = OrderBuilder.aOrder().build();
        order.setOrderId(orderDAO.update(order));

        int countOfOrdersBeforeDeleteOperation = orderDAO.getAll().size();

        orderDAO.delete(order);

        int countOfOrdersAfterDeleteOperation = orderDAO.getAll().size();

        assertTrue(countOfOrdersBeforeDeleteOperation-1 == countOfOrdersAfterDeleteOperation);

    }

    @Test
    public void testGetListOfAllOrders() throws SQLException {

        OrderDAO orderDAO = aOrderDAO();

        Order order = OrderBuilder.aOrder().build();
        orderDAO.update(order);

        List<Order> listOfOrders = orderDAO.getAll();
        assertTrue(listOfOrders.size()>0);

    }

    @Test
    public void testNewOrderRecordWithNewCustomerAndTaxi() throws SQLException {

        DBService dbService = new DBService();
        Connection connection = dbService.getMysqlConnection();
        String databaseName = dbService.getDatabaseName();

        Customer customer = CustomerBuilder.aCustomer().build();
        CustomerDAO customerDAO = new CustomerDAOImpl(connection, databaseName);
        customer.setCustomerId(customerDAO.update(customer));

        Taxi taxi = TaxiBuilder.aTaxi().build();
        TaxiDAO taxiDAO = new TaxiDAOImpl(connection, databaseName);
        taxi.setTaxiId(taxiDAO.update(taxi));

        OrderBuilder orderBuilder = OrderBuilder.aOrder()
                .withCustomerID(customer.getCustomerId())
                .withTaxiID(taxi.getTaxiId())
                .withFromAdress("Anninmuizas 6")
                .withToAdress("Kr.Barona 89")
                .withPrice(23.05)
                .withDistance(14.5)
                .withDate(getCurrentDate())
                .withOrderStatus(Order.OrderStatus.DELIVERED);

        Order order = orderBuilder.build();
        long newOrderID = aOrderDAO().update(order);
    }

}
