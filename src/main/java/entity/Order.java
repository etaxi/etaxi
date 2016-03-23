package entity;

import java.sql.Timestamp;

/** Проект etaxi
 * Класс для хранения данных заказа
 */
public class Order {

    public enum OrderStatus {WAITING, DRIVING, DELIVERED}

    // Идентификатор заказа
    private Long orderId;
    // Клиент
    private Long customerId;
    // Дата Время "2015-02-18T00:00:00"
    private Timestamp dateTime;
    // статус (ждёт, едет, выполнен)
    private OrderStatus orderStatus;
    // откуда строка
    private String fromAdress;
    // куда стока
    private String toAdress;
    // Такси
    private Long taxiId;
    // растояние
    private double distance;
    // стоимость
    private double price;
    // rating  [1..10]
    private int rate;
    // отзыв
    private String feedback;

    public Order(Long orderId, Long customerId, Timestamp dateTime, OrderStatus orderStatus,
                 String fromAdress, String toAdress, Long taxiId, double distance,
                 double price, int rate, String feedback) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.dateTime = dateTime;
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

    @Override
    public String toString() {
        return "{" +
                "\"orderId\":" + "\"" + orderId + "\"" +
                ", \"customerId\":" + "\"" + customerId + "\"" +
                ", \"dateTime\":" + "\"" + dateTime + "\"" +
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

