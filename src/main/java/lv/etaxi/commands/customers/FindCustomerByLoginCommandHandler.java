package lv.etaxi.commands.customers;

import lv.etaxi.business.CustomerManager;
import lv.etaxi.commands.DomainCommandHandler;
import lv.etaxi.dto.CustomerDTO;
import lv.etaxi.dto.СonvertorDTO;
import lv.etaxi.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
class FindCustomerByLoginCommandHandler implements DomainCommandHandler<FindCustomerByLoginCommand, CustomerOperationResult> {

    @Autowired
    CustomerManager customerManagerImpl;

    @Autowired
    СonvertorDTO convertorDTO;

    @Override
    public CustomerOperationResult execute(FindCustomerByLoginCommand command) {

        Customer customer = null;
        CustomerDTO customerDTO = null;
        try {
            customer = customerManagerImpl.findByLogin(command.getCustomerLogin());
            customerDTO = convertorDTO.convertCustomerToDTO(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new CustomerOperationResult(customerDTO);
    }

    @Override
    public Class getCommandType() {
        return FindCustomerByLoginCommand.class;
    }
}
