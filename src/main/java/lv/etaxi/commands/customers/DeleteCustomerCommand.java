package lv.etaxi.commands.customers;


import lv.etaxi.commands.DomainCommand;

public class DeleteCustomerCommand implements DomainCommand<CustomerOperationResult> {

    private Long customerId;

    public DeleteCustomerCommand(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCustomerId() {
        return customerId;
    }

}
