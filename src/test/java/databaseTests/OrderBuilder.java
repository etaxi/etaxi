package databaseTests;

import lv.etaxi.entity.Order;

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
    private Long customerId = (long) 0;
    private Long taxiId = (long) 0;


    private OrderBuilder() {
    }

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

    public OrderBuilder withOrderedDate(Timestamp orderedDateTime) {
        this.orderedDateTime = orderedDateTime;
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

    public Order build() {
        return new Order(id, customerId, dateTime, orderedDateTime, orderStatus, fromAdress, toAdress,
                taxiId, distance, price, rate, feedback);
    }
}

