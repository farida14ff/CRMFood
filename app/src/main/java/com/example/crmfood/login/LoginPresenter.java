//package com.example.crmfood.login;
//
//import com.example.crmfood.models.Login;
//import com.example.crmfood.models.User;
//import com.example.crmfood.network.MyService;
//import com.example.crmfood.network.RetrofitClientInstance;
//
//import org.jetbrains.annotations.NotNull;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class LoginPresenter implements LoginContract.LoginPresenter{
//
//    LoginContract.LoginView loginView;
//    MyService service = RetrofitClientInstance.getRetrofitInstance().create(MyService.class);
//
//    public LoginPresenter(LoginContract.LoginView loginView){
//        this.loginView = loginView;
//    }
//
//    @Override
//    public void login(String login_u, String password) {
//
//        Login login = new Login(login_u,password);
//        Call<User> call =  service.login(login);
//        call.enqueue(new Callback<User>() {
//
//            @Override
//            public void onResponse(@NotNull Call<User> call, @NotNull Response<User> response) {
//                if (response.isSuccessful()) {
//                    assert response.body() != null;
//                    loginView.addAuthToken(String.format("%s", response.body().getToken()));
//                } else {
//                    if (loginView.isConnected()) {
//                        loginView.showLoginError();
//                    } else {
//                        loginView.showErrorToast();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(@NotNull Call<User> call, @NotNull Throwable t) {
//                if (loginView.isConnected()) {
//                    loginView.showLoginError();
//                } else {
//                    loginView.showErrorToast();
//                }
//            }
//        });
//
//    }
//}
