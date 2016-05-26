package lv.etaxi.commands.orders;

import lv.etaxi.commands.DomainCommandResult;
import lv.etaxi.dto.OrderDTO;

public class OrderOperationResult implements DomainCommandResult {

	private OrderDTO order;

	public OrderOperationResult(OrderDTO order) {
		this.order = order;
	}

	public OrderDTO getOrder() {
		return order;
	}

}
