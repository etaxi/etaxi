package lv.etaxi.commands.customers;

import lv.etaxi.business.CustomerManager;
import lv.etaxi.commands.DomainCommandHandler;
import lv.etaxi.dto.CustomerDTO;
import lv.etaxi.dto.СonvertorDTO;
import lv.etaxi.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class CheckCustomerAuthorizationCommandHandler implements DomainCommandHandler<CheckCustomerAuthorizationCommand, CustomerOperationResult> {

    @Autowired
    CustomerManager customerManagerImpl;

    @Autowired
    СonvertorDTO convertorDTO;

    @Override
    public CustomerOperationResult execute(CheckCustomerAuthorizationCommand command) {

        Customer customer = customerManagerImpl.CheckAuthorization(
                            command.getCustomerLogin(),
                            command.getCustomerPassword());

        CustomerDTO customerDTO = convertorDTO.convertCustomerToDTO(customer);

        return new CustomerOperationResult(customerDTO);
    }

    @Override
    public Class getCommandType() {
        return CheckCustomerAuthorizationCommand.class;
    }
}
