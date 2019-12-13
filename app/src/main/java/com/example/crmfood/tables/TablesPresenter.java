package com.example.crmfood.tables;

import android.util.Log;


import com.example.crmfood.main.MainActivity;
import com.example.crmfood.models.Table;
import com.example.crmfood.network.MyService;
import com.example.crmfood.network.RetrofitClientInstance;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class TablesPresenter implements TablesContract.Presenter {

    private TablesContract.View view;
    private MyService service = RetrofitClientInstance.getRetrofitInstance().create(MyService.class);

    TablesPresenter(TablesContract.View view) {
        this.view = view;
    }

    @Override
    public void displayTables() {
        Call<List<Table>> call = service.getTables("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVc2VySWQiOiI3IiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvbmFtZSI6ImxvZ2lud3NkIiwiaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS93cy8yMDA4LzA2L2lkZW50aXR5L2NsYWltcy9yb2xlIjoid2FpdGVyIiwibmJmIjoxNTc2MDY1MTU5LCJleHAiOjE1Nzc3OTMxNTksImlzcyI6IkNSTVNlcnZlciIsImF1ZCI6IkNSTUZvb2QifQ.btgL4dMpF0sKcGKPC7zV_G-2GrL8TWHXYz-Z5hGCtJg");
        call.enqueue(new Callback<List<Table>>() {
            @Override
            public void onResponse(@NotNull Call<List<Table>> call,
                                   @NotNull Response<List<Table>> response) {
                view.setTables(response.body());
                Log.e(TAG, "onResponse getTables");
            }


            @Override
            public void onFailure(@NotNull Call<List<Table>> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure getTABLES " + t.getMessage());

                view.showError();
            }
        });
    }
}
