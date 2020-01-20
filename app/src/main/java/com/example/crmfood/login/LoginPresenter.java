package com.example.crmfood.login;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.crmfood.models.Login;
import com.example.crmfood.models.User;
import com.example.crmfood.network.MyService;
import com.example.crmfood.network.RetrofitClientInstance;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class LoginPresenter implements LoginContract.LoginPresenter{

    private LoginContract.LoginView loginView;
    private MyService service = RetrofitClientInstance.getRetrofitInstance().create(MyService.class);

    LoginPresenter(LoginContract.LoginView loginView){
        this.loginView = loginView;
    }

    @Override
    public void login(String login_u, String password) {

//        Login login = new Login(login_u,password);

        Call<User> call =  service.login(new Login(login_u,password));
        call.enqueue(new Callback<User>() {

            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(@NotNull Call<User> call, @NotNull Response<User> response) {
                if (response.isSuccessful() && response.body().getRole() == 3) {
                    assert response.body() != null;
//                    loginView.addAuthToken("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVc2VySWQiOiI3IiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvbmFtZSI6ImxvZ2lud3NkIiwiaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS93cy8yMDA4LzA2L2lkZW50aXR5L2NsYWltcy9yb2xlIjoid2FpdGVyIiwibmJmIjoxNTc2NDk5ODA4LCJleHAiOjE1NzgyMjc4MDgsImlzcyI6IkNSTVNlcnZlciIsImF1ZCI6IkNSTUZvb2QifQ.SB2lMDXWBYOsYVLyaOdt-TK8GkqC_ALBxm7Gp5iusi0");
                        Log.e("onResponse :", "2st if");
                        loginView.addAuthToken(String.format("%s", response.body().getAccess_token()));
                        Log.e("loginView.addAuthToken: ", String.format("%s", response.body().getAccess_token()));

                    Log.e("onResponse :", "1st if");
                    Log.e(TAG, response.message());
                } else {
                    if (loginView.isConnected()) {
                        loginView.showLoginError();

                        Log.e("onResponse","else -if");
                        Log.e(TAG, response.message());
                    } else {
                        loginView.showErrorToast();
                        Log.e("onResponse","else else");
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<User> call, @NotNull Throwable t) {
                Log.e("onFailure","onFailure");

                if (loginView.isConnected()) {
                    loginView.showLoginError();
                } else {
                    loginView.showErrorToast();

                }
            }
        });

    }
}
