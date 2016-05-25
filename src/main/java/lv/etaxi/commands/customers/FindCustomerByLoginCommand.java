package lv.etaxi.commands.customers;


import lv.etaxi.commands.DomainCommand;

public class FindCustomerByLoginCommand implements DomainCommand<CustomerOperationResult> {

    private String login;

    public FindCustomerByLoginCommand(String login) {
        this.login = login;
    }

    public String getCustomerLogin() {
        return login;
    }

}
