package lv.etaxi.commands.orders;

import lv.etaxi.builders.CustomerBuilder;
import lv.etaxi.builders.TaxiBuilder;
import lv.etaxi.business.OrderManager;
import lv.etaxi.commands.DomainCommandHandler;
import lv.etaxi.dao.CustomerDAO;
import lv.etaxi.dao.TaxiDAO;
import lv.etaxi.dao.jdbc.CustomerDAOImpl;
import lv.etaxi.dao.jdbc.TaxiDAOImpl;
import lv.etaxi.dto.OrderDTO;
import lv.etaxi.dto.СonvertorDTO;
import lv.etaxi.entity.Customer;
import lv.etaxi.entity.Order;
import lv.etaxi.entity.Taxi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
class CreateOrderCommandHandler implements DomainCommandHandler<CreateOrderCommand, OrderOperationResult> {

	@Autowired
	OrderManager orderManagerImpl;

	@Autowired
	СonvertorDTO convertorDTO;

	private Customer CreateNewCustomer() {
		Customer customer = CustomerBuilder.aCustomer().build();
		CustomerDAO customerDAO = new CustomerDAOImpl();
		try {
			customer.setCustomerId(customerDAO.create(customer));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return  customer;
	}

	private Taxi CreateTaxi() {
		Taxi taxi = TaxiBuilder.aTaxi().build();
		TaxiDAO taxiDAO = new TaxiDAOImpl();
		try {
			taxi.setTaxiId(taxiDAO.create(taxi));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return taxi;
	}

	@Override
	public OrderOperationResult execute(CreateOrderCommand command) {

		Order order = new Order(
				(long) 0,
				CreateNewCustomer().getCustomerId(),
				command.getDateTime(),
				command.getOrderedDateTime(),
				command.getOrderStatus(),
				command.getFromAddress(),
				command.getToAddress(),
				CreateTaxi().getTaxiId(),
				0.00, 0.00, 0, "");

		OrderDTO orderDTO = null;
		try {
			orderManagerImpl.create(order);
			orderDTO = convertorDTO.convertOrderToDTO(order);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new OrderOperationResult(orderDTO);
	}

	@Override
	public Class getCommandType() {
		return CreateOrderCommand.class;
	}
	
}
