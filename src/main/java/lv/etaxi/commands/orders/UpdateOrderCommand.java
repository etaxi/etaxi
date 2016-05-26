package lv.etaxi.commands.orders;

import lv.etaxi.commands.DomainCommand;
import lv.etaxi.entity.Order;

import java.sql.Timestamp;

public class UpdateOrderCommand implements DomainCommand<OrderOperationResult> {

    private Long orderId;

    private Long customerId;

    private Long taxiId;

    private Timestamp dateTime;

    private Timestamp orderedDateTime;

    private String fromAddress;

    private String toAddress;

    private Order.OrderStatus orderStatus;

    public UpdateOrderCommand(Long orderId,
                              Long customerId,
                              Long taxiId,
                              Timestamp dateTime,
                              Timestamp orderedDateTime,
                              String fromAddress,
                              String toAddress,
                              Order.OrderStatus orderStatus) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.taxiId = taxiId;
        this.dateTime = dateTime;
        this.orderedDateTime = orderedDateTime;
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.orderStatus = orderStatus;
    }

    public Timestamp getOrderedDateTime() {
        return orderedDateTime;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public Order.OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Long getTaxiId() {
        return taxiId;
    }
}
