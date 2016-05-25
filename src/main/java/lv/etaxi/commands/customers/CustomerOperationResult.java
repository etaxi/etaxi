package lv.etaxi.commands.customers;

import lv.etaxi.commands.DomainCommandResult;
import lv.etaxi.dto.CustomerDTO;

public class CustomerOperationResult implements DomainCommandResult {

	private CustomerDTO customer;

	public CustomerOperationResult(CustomerDTO customer) {
		this.customer = customer;
	}

	public CustomerDTO getCustomer() {
		return customer;
	}

}
