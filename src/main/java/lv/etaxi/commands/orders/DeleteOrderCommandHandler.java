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
class DeleteOrderCommandHandler implements DomainCommandHandler<DeleteOrderCommand, OrderOperationResult> {

    @Autowired
    OrderManager orderManagerImpl;

    @Autowired
    СonvertorDTO convertorDTO;

    @Override
    public OrderOperationResult execute(DeleteOrderCommand command) {

        try {
            Order order = orderManagerImpl.findById(command.getOrderId());
            orderManagerImpl.delete(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        OrderDTO orderDTO = convertorDTO.convertOrderToDTO(null);
        return new OrderOperationResult(orderDTO);
    }

    @Override
    public Class getCommandType() {
        return DeleteOrderCommand.class;
    }
}
