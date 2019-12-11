package com.example.crmfood.subMenu;

import android.util.Log;

import com.example.crmfood.models.SubMenu;
import com.example.crmfood.network.MyService;
import com.example.crmfood.network.RetrofitClientInstance;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SubMenuPresenter implements SubMenuContract.Presenter {

    private SubMenuContract.View view;
    private MyService service = RetrofitClientInstance.getRetrofitInstance().create(MyService.class);

    public SubMenuPresenter(SubMenuContract.View view) {
        this.view = view;
    }

    @Override
    public void displayMeals(long id) {
        Call<List<SubMenu>> call = service.getSubMenu("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVc2VySWQiOiIxIiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvbmFtZSI6IndhaXRlcjEiLCJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3JvbGUiOiJ3YWl0ZXIiLCJuYmYiOjE1NzQ2NzYwNzYsImV4cCI6MTU3NjQwNDA3NiwiaXNzIjoiQ1JNU2VydmVyIiwiYXVkIjoiQ1JNRm9vZCJ9.iuaSG2_KkFQsofJGwAM9Wn_Qc9f0BghJihrA5HrbTFY",id);
        call.enqueue(new Callback<List<SubMenu>>() {
            @Override
            public void onResponse(@NotNull Call<List<SubMenu>> call, @NotNull Response<List<SubMenu>> response) {
                view.getListOfMeals(response.body());
                Log.i(TAG, "onResponse ");

            }

            @Override
            public void onFailure(@NotNull Call<List<SubMenu>> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure " + t.getMessage());

                view.showError();
            }
        });

    }
}
