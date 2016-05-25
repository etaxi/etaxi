package lv.etaxi.commands.customers;

import lv.etaxi.commands.DomainCommand;

public class CreateCustomerCommand implements DomainCommand<CustomerOperationResult> {

    private String name;
    private String phone;
    private String password;


    public CreateCustomerCommand(String name,
                                 String phone,
                                 String password) {
        this.name = name;
        this.phone = phone;
        this.password = password;
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
