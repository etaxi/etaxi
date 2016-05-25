package lv.etaxi.commands.customers;

import lv.etaxi.business.CustomerManager;
import lv.etaxi.commands.DomainCommandHandler;
import lv.etaxi.dto.СonvertorDTO;
import lv.etaxi.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
class DeleteCustomerCommandHandler implements DomainCommandHandler<DeleteCustomerCommand, CustomerOperationResult> {

    @Autowired
    CustomerManager customerManagerImpl;

    @Autowired
    СonvertorDTO convertorDTO;

    @Override
    public CustomerOperationResult execute(DeleteCustomerCommand command) {

        try {
            Customer customer = customerManagerImpl.findById(command.getCustomerId());
            customerManagerImpl.delete(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new CustomerOperationResult(null);
    }

    @Override
    public Class getCommandType() {
        return DeleteCustomerCommand.class;
    }
}
