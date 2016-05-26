package lv.etaxi.commands.orders;


import lv.etaxi.business.OrderManager;
import lv.etaxi.commands.DomainCommandHandler;
import lv.etaxi.dto.OrderDTO;
import lv.etaxi.dto.СonvertorDTO;
import lv.etaxi.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
class UpdateOrderCommandHandler implements DomainCommandHandler<UpdateOrderCommand, OrderOperationResult> {

    @Autowired
    OrderManager orderManagerImpl;

    @Autowired
    СonvertorDTO convertorDTO;

    @Override
    public OrderOperationResult execute(UpdateOrderCommand command) {

        Order order = new Order(
                command.getOrderId(),
                command.getCustomerId(),
                command.getDateTime(),
                command.getOrderedDateTime(),
                command.getOrderStatus(),
                command.getFromAddress(),
                command.getToAddress(),
                command.getTaxiId(),
                0.00, 0.00, 0, "");

        OrderDTO orderDTO = null;
        try {
            orderManagerImpl.update(order);
            orderDTO = convertorDTO.convertOrderToDTO(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new OrderOperationResult(orderDTO);
    }

    @Override
    public Class getCommandType() {
        return UpdateOrderCommand.class;
    }

}
