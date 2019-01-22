package pers.illiant.yummy.model;

public class MemberModel_login {

    private String name;
    private String password;

    public MemberModel_login() {
    }

    public MemberModel_login(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
