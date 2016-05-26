package lv.etaxi.commands.orders;


import lv.etaxi.commands.DomainCommand;

public class GetOrderCommand implements DomainCommand<OrderOperationResult> {

    private Long orderId;

    public GetOrderCommand(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

}
