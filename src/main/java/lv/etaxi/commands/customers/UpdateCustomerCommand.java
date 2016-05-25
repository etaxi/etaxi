package lv.etaxi.commands.customers;

import lv.etaxi.commands.DomainCommand;

public class UpdateCustomerCommand implements DomainCommand<CustomerOperationResult> {

    private Long customerId;
    private String name;
    private String phone;
    private String password;

    public UpdateCustomerCommand(Long Id,
                                 String name,
                                 String phone,
                                 String password) {
        this.customerId = Id;
        this.name = name;
        this.phone = phone;
        this.password = password;
    }


    public Long getId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

}
