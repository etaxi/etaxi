package lv.etaxi.commands.customers;

import lv.etaxi.commands.DomainCommand;

public class CheckCustomerAuthorizationCommand implements DomainCommand<CustomerOperationResult> {

    private String login;
    private String password;

    public CheckCustomerAuthorizationCommand(String login, String password) {
        this.password = password;
        this.login = login;
    }

    public String getCustomerLogin() {
        return login;
    }

    public String getCustomerPassword() {
        return password;
    }

}
