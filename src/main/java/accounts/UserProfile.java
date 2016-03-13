package accounts;


public class UserProfile {
    private final String login;
    private final String pass;
    private final String name;

    public UserProfile(String login, String pass, String name) {
        this.login = login;
        this.pass = pass;
        this.name = name;
    }

    public UserProfile(String login) {
        this.login = login;
        this.pass = login;
        this.name = login;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public String getName() {
        return name;
    }
}
