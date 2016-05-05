package lv.etaxi.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/** Проект etaxi
 * Класс для хранения данных заказа
 */

@Entity
@Table(name = "orders")
public class Order implements Serializable {
    public enum OrderStatus {WAITING, DRIVING, DELIVERED, TAKEN}

    // Идентификатор заказа
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    // Клиент
    @Column(name = "customerId", unique = false, updatable = true)
    private Long customerId;

    // Клиент - объект (не используем в функционале системы, только для теста свзей таблиц Hibernate)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private Customer customer;

    // Дата Время "2015-02-18T00:00:00" (ввода или последнего изменения заказа)
    @Column(name = "datetime", unique = false, updatable = true)
    private Timestamp dateTime;

    // Дата Время "2015-02-18T00:00:00" (на которую такси заказано)
    @Column(name = "ordereddatetime", unique = false, updatable = true)
    private Timestamp orderedDateTime;

    // статус (ждёт, едет, выполнен)
    @Column(name = "orderStatus", unique = false, updatable = true)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    // откуда строка
    @Column(name = "fromAdress", unique = false, updatable = true)
    private String fromAdress;

    // куда стока
    @Column(name = "toAdress", unique = false, updatable = true)
    private String toAdress;

    // Такси
    @Column(name = "taxiId", unique = false, updatable = true)
    private Long taxiId;

    // Такси - объект (не используем в функционале системы, только для теста свзей таблиц Hibernate)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private Taxi taxi;

    // растояние
    @Column(name = "distance", unique = false, updatable = true)
    private double distance;

    // стоимость
    @Column(name = "price", unique = false, updatable = true)
    private double price;

    // rating  [1..10]
    @Column(name = "rate", unique = false, updatable = true)
    private int rate;

    // отзыв
    @Column(name = "feedback", unique = false, updatable = true)
    private String feedback;

    //Important to Hibernate!
    @SuppressWarnings("UnusedDeclaration")
    public Order() {}

    public Order(Long orderId, Long customerId, Timestamp dateTime, Timestamp orderedDateTime, OrderStatus orderStatus,
                 String fromAdress, String toAdress, Long taxiId, double distance,
                 double price, int rate, String feedback) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.dateTime = dateTime;
        this.orderedDateTime = orderedDateTime;
        this.orderStatus = orderStatus;
        this.fromAdress = fromAdress;
        this.toAdress = toAdress;
        this.taxiId = taxiId;
        this.distance = distance;
        this.price = price;
        this.rate = rate;
        this.feedback = feedback;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Timestamp  getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp  dateTime) {
        this.dateTime = dateTime;
    }

    public Timestamp  getOrderedDateTime() {
        return orderedDateTime;
    }

    public void setOrderedDateTime(Timestamp  orderedDateTime) {
        this.orderedDateTime = orderedDateTime;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getFromAdress() {
        return fromAdress;
    }

    public void setFromAdress(String fromAdress) {
        this.fromAdress = fromAdress;
    }

    public String getToAdress() {
        return toAdress;
    }

    public void setToAdress(String toAdress) {
        this.toAdress = toAdress;
    }

    public Long getTaxiId() {
        return taxiId;
    }

    public void setTaxiId(Long taxiId) {
        this.taxiId = taxiId;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(byte rate) {
        this.rate = rate;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Taxi getTaxi() {
        return taxi;
    }

    public void setTaxi(Taxi taxi) {
        this.taxi = taxi;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "{" +
                "\"orderId\":" + "\"" + orderId + "\"" +
                ", \"customerId\":" + "\"" + customerId + "\"" +
                ", \"dateTime\":" + "\"" + dateTime + "\"" +
                ", \"orderedDateTime\":" + "\"" + orderedDateTime + "\"" +
                ", \"orderStatus\":" + "\"" + orderStatus + "\"" +
                ", \"fromAdress\":" + "\"" + fromAdress + "\"" +
                ", \"toAdress\":" + "\"" + toAdress + "\"" +
                ", \"taxiId\":" + "\"" + taxiId + "\"" +
                ", \"distance\":" + "\"" + distance + "\"" +
                ", \"price\":" + "\"" + price + "\"" +
                ", \"rate\":" + "\"" + rate + "\"" +
                ", \"feedback\":" + "\"" + feedback + "\"" +
                '}';
    }

    public static OrderStatus DetermineOrderStatus(String name) {
        return OrderStatus.valueOf(name);
    }

}

