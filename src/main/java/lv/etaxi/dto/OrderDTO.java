package lv.etaxi.dto;

import lv.etaxi.entity.Order;

import java.sql.Timestamp;

/** Проект etaxi
 * Класс DTO для передачи данных по заказу
 */
public class OrderDTO {

    private Long orderId;  // Идентификатор заказа

    private Long customerId;  // Клиент

    private Timestamp orderedDateTime;  // Дата Время "2015-02-18T00:00:00" (на которую такси заказано)

    private Order.OrderStatus orderStatus;      // статус (ждёт, едет, выполнен)

    private String fromAdress;    // адрес откуда

    private String toAdress;   // адрес куда

    private Long taxiId;      // Идентификатор такси

    private double distance;       // растояние

    private double price;      // стоимость

    private int rate;       // rating  [1..10]

    private String feedback;       // отзыв о поездке

    public OrderDTO(Long orderId, Long customerId, Timestamp orderedDateTime, Order.OrderStatus orderStatus,
                 String fromAdress, String toAdress, Long taxiId, double distance,
                 double price, int rate, String feedback) {
        this.orderId = orderId;
        this.customerId = customerId;
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


    public Timestamp  getOrderedDateTime() {
        return orderedDateTime;
    }

    public void setOrderedDateTime(Timestamp  orderedDateTime) {
        this.orderedDateTime = orderedDateTime;
    }

    public Order.OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Order.OrderStatus orderStatus) {
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

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "{" +
                "\"orderId\":" + "\"" + orderId + "\"" +
                ", \"customerId\":" + "\"" + customerId + "\"" +
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
}

