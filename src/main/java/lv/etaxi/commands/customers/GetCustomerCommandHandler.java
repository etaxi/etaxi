package lv.etaxi.commands.customers;

import lv.etaxi.business.CustomerManager;
import lv.etaxi.dto.CustomerDTO;
import lv.etaxi.dto.СonvertorDTO;
import lv.etaxi.entity.Customer;
import lv.etaxi.commands.DomainCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
class GetCustomerCommandHandler implements DomainCommandHandler<GetCustomerCommand, CustomerOperationResult> {

    @Autowired
    CustomerManager customerManagerImpl;

    @Autowired
    СonvertorDTO convertorDTO;

    @Override
    public CustomerOperationResult execute(GetCustomerCommand command) {

        Customer customer = null;
        CustomerDTO customerDTO = null;
        try {
            customer = customerManagerImpl.findById(command.getCustomerId());
            customerDTO = convertorDTO.convertCustomerToDTO(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new CustomerOperationResult(customerDTO);
    }

    @Override
    public Class getCommandType() {
        return GetCustomerCommand.class;
    }
}
