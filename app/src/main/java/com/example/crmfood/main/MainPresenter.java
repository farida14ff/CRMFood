package com.example.crmfood.main;

import android.util.Log;

import com.example.crmfood.BaseActivity;
import com.example.crmfood.models.ActiveOrder;
import com.example.crmfood.models.CloseCheque;
import com.example.crmfood.models.OrderId;
import com.example.crmfood.network.MyService;
import com.example.crmfood.network.RetrofitClientInstance;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private MyService service = RetrofitClientInstance.getRetrofitInstance().create(MyService.class);

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void displayListOfActiveOrders() {
//        Call<List<ActiveOrder>> call = service.getListOfActiveOrders("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVc2VySWQiOiI3IiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvbmFtZSI6ImxvZ2lud3NkIiwiaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS93cy8yMDA4LzA2L2lkZW50aXR5L2NsYWltcy9yb2xlIjoid2FpdGVyIiwibmJmIjoxNTc2MDY1MTU5LCJleHAiOjE1Nzc3OTMxNTksImlzcyI6IkNSTVNlcnZlciIsImF1ZCI6IkNSTUZvb2QifQ.btgL4dMpF0sKcGKPC7zV_G-2GrL8TWHXYz-Z5hGCtJg");
        Call<List<ActiveOrder>> call = service.getListOfActiveOrders(BaseActivity.authToken);
        call.enqueue(new Callback<List<ActiveOrder>>() {
            @Override
            public void onResponse(@NotNull Call<List<ActiveOrder>> call,
                                   @NotNull Response<List<ActiveOrder>> response) {

                if (response.body() != null && response.body().size() > 0) {
                    Log.i(TAG, "onResponse if");
                    view.setListOfActiveOrders(response.body());
                    view.hideProgressBar();
                } else {
                    Log.i(TAG, "onResponse else");
                    view.setListOfActiveOrders(response.body());
                    view.showEmptyView();
                }
                view.stopRefreshingOrders();



            }

            @Override
            public void onFailure(@NotNull Call<List<ActiveOrder>> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure " + t.getMessage());
                view.showError();

            }
        });
    }

    @Override
    public void closeCheque(final long id) {
        Call<CloseCheque> call = service.closeCheque(BaseActivity.authToken, new OrderId(id));

        call.enqueue(new Callback<CloseCheque>() {
            @Override
            public void onResponse(@NotNull Call<CloseCheque> call, @NotNull Response<CloseCheque> response) {

                System.out.println(id);
                Log.e(TAG, response.message());
            }

            @Override
            public void onFailure(@NotNull Call<CloseCheque> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure :Did not closeCheque");

            }
        });



    }
}
