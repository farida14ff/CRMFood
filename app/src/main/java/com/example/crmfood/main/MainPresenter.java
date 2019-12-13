package com.example.crmfood.main;

import android.util.Log;

import com.example.crmfood.models.ActiveOrder;
import com.example.crmfood.network.MyService;
import com.example.crmfood.network.RetrofitClientInstance;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private MyService service = RetrofitClientInstance.getRetrofitInstance().create(MyService.class);

    MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void displayListOfActiveOrders() {
        Call<List<ActiveOrder>> call = service.getListOfActiveOrders("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVc2VySWQiOiI3IiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvbmFtZSI6ImxvZ2lud3NkIiwiaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS93cy8yMDA4LzA2L2lkZW50aXR5L2NsYWltcy9yb2xlIjoid2FpdGVyIiwibmJmIjoxNTc2MDY1MTU5LCJleHAiOjE1Nzc3OTMxNTksImlzcyI6IkNSTVNlcnZlciIsImF1ZCI6IkNSTUZvb2QifQ.btgL4dMpF0sKcGKPC7zV_G-2GrL8TWHXYz-Z5hGCtJg");
        call.enqueue(new Callback<List<ActiveOrder>>() {
            @Override
            public void onResponse(@NotNull Call<List<ActiveOrder>> call,
                                   @NotNull Response<List<ActiveOrder>> response) {
                view.setListOfActiveOrders(response.body());
                Log.i(TAG, "onResponse ");
            }

            @Override
            public void onFailure(@NotNull Call<List<ActiveOrder>> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure " + t.getMessage());
                view.showError();

            }
        });
    }
}
