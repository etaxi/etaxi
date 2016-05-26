package lv.etaxi.commands.orders;


import lv.etaxi.commands.DomainCommand;

public class DeleteOrderCommand implements DomainCommand<OrderOperationResult> {

    private Long orderId;

    public DeleteOrderCommand(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

}
