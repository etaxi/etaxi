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
class UpdateCustomerCommandHandler implements DomainCommandHandler<UpdateCustomerCommand, CustomerOperationResult> {

    @Autowired
    CustomerManager customerManagerImpl;

    @Autowired
    СonvertorDTO convertorDTO;

    @Override
    public CustomerOperationResult execute(UpdateCustomerCommand command) {

        Customer customer = new Customer(
                command.getId(),
                command.getName(),
                command.getPhone(),
                command.getPassword());

        CustomerDTO customerDTO = null;
        try {
            customerManagerImpl.updateInDataBase(customer);
            customerDTO = convertorDTO.convertCustomerToDTO(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new CustomerOperationResult(customerDTO);
    }

    @Override
    public Class getCommandType() {
        return UpdateCustomerCommand.class;
    }


}
