package com.example.crmfood.models;

public class Login {

    private String login;
    private String password;

    public Login(String login, String password){
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
