package pers.illiant.yummy.model;

public class MemberVO_login {

    private String name;
    private String password;

    public MemberVO_login() {
    }

    public MemberVO_login(String name, String password) {
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
