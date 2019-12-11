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
        Call<List<Table>> call = service.getTables("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVc2VySWQiOiIxIiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvbmFtZSI6IndhaXRlcjEiLCJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3JvbGUiOiJ3YWl0ZXIiLCJuYmYiOjE1NzQ2NzYwNzYsImV4cCI6MTU3NjQwNDA3NiwiaXNzIjoiQ1JNU2VydmVyIiwiYXVkIjoiQ1JNRm9vZCJ9.iuaSG2_KkFQsofJGwAM9Wn_Qc9f0BghJihrA5HrbTFY");
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
