package com.example.crmfood.login;

public interface LoginContract {

    interface  LoginView{
        void showErrorToast();
        void showLoginError();
        void addAuthToken(String authToken);
        boolean isConnected();
    }

    interface LoginPresenter{
        void login(String login, String password);
    }
}
