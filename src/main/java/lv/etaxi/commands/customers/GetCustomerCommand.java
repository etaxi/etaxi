package lv.etaxi.commands.customers;


import lv.etaxi.commands.DomainCommand;

public class GetCustomerCommand implements DomainCommand<CustomerOperationResult> {

    private Long customerId;

    public GetCustomerCommand(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCustomerId() {
        return customerId;
    }

}
