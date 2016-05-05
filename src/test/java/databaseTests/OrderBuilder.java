package databaseTests;

import lv.etaxi.dao.CustomerDAO;
import lv.etaxi.dao.TaxiDAO;
import lv.etaxi.dao.jdbc.CustomerDAOImpl;
import lv.etaxi.dao.jdbc.TaxiDAOImpl;
import lv.etaxi.entity.Customer;
import lv.etaxi.entity.Order;
import lv.etaxi.entity.Taxi;

import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Проект etaxi
 * JUnit тесты для проекта etaxi (design patterns "Object Mother" and "Test Data Builder")
 */

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
    private Timestamp orderedDateTime = new Timestamp(new java.util.Date().getTime());
    private Long customerId = null;
    private Long taxiId = null;


    private OrderBuilder() {
    }

    public static OrderBuilder aOrder() {
        return new OrderBuilder();
    }

    private Customer CreateNewCustomer() throws SQLException {
        Customer customer = CustomerBuilder.aCustomer().build();
        CustomerDAO customerDAO = new CustomerDAOImpl();
        customer.setCustomerId(customerDAO.create(customer));
        return  customer;
    }

    private Taxi CreateTaxi() throws SQLException {
        Taxi taxi = TaxiBuilder.aTaxi().build();
        TaxiDAO taxiDAO = new TaxiDAOImpl();
        taxi.setTaxiId(taxiDAO.create(taxi));
        return taxi;
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

    public OrderBuilder withOrderedDate(Timestamp orderedDateTime) {
        this.orderedDateTime = orderedDateTime;
        return this;
    }

    public OrderBuilder withCustomerID(Long customerId) {
        this.customerId = customerId;
        return this;
    }

    public OrderBuilder withTaxiID(Long taxiId) {
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
                .withOrderedDate(orderedDateTime)
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

    public Order build() throws SQLException {
        Customer customer = CreateNewCustomer();
        Taxi taxi = CreateTaxi();

        return new Order(id, customer.getCustomerId(), dateTime, orderedDateTime, orderStatus, fromAdress, toAdress,
                taxi.getTaxiId(), distance, price, rate, feedback);
    }
}