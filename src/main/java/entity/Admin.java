package entity;


public class Admin {

    private Long adminId;       // Идентификатор
    private String name;        // Имя, Фамилия
    private String login;    // логин
    private String password;    // пароль

    public Admin(Long adminId, String name, String login, String password){

        this.adminId = adminId;
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public Long getAdminId() {

        return adminId;
    }

    public void setAdminId(Long adminId) {

        this.adminId = adminId;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getLogin() {

        return login;
    }

    public void setLogin(String login) {

        this.login = login;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    @Override
    public String toString() {

        return "{" +
                "\"adminId\":" + "\"" + adminId + "\"" +
                ", \"name\":" + "\"" + name + "\"" +
                ", \"login\":" + "\"" + login + "\"" +
                ", \"password\":" + "\"" + password + "\"" +
                '}';
    }
}
