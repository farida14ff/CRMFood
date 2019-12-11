package com.example.crmfood.models;

public class Login {

    private String login_u;
    private String password;

    public Login(String login_u, String password){
        this.login_u = login_u;
        this.password = password;
    }

    public String getLogin_u() {
        return login_u;
    }

    public String getPassword() {
        return password;
    }
}
