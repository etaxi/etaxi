package lv.etaxi.commands.customers;

import lv.etaxi.business.CustomerManager;
import lv.etaxi.dto.CustomerDTO;
import lv.etaxi.dto.СonvertorDTO;
import lv.etaxi.entity.Customer;
import lv.etaxi.commands.DomainCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class CreateCustomerCommandHandler implements DomainCommandHandler<CreateCustomerCommand, CustomerOperationResult> {

	@Autowired
	CustomerManager customerManagerImpl;

	@Autowired
	СonvertorDTO convertorDTO;

	@Override
	public CustomerOperationResult execute(CreateCustomerCommand command) {

		Customer customer = new Customer(
				(long) 0,
				command.getName(),
				command.getPhone(),
				command.getPassword());

		String errorMessage = customerManagerImpl.create(customer);

		CustomerDTO customerDTO = convertorDTO.convertCustomerToDTO((errorMessage.isEmpty()) ? customer : null);

		return new CustomerOperationResult(customerDTO);
	}

	@Override
	public Class getCommandType() {
		return CreateCustomerCommand.class;
	}
	
}
