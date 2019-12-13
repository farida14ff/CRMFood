package com.example.crmfood.subMenu;

import android.util.Log;

import com.example.crmfood.models.CategoryId;
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
        Call<List<SubMenu>> call = service.getSubMenu("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVc2VySWQiOiI3IiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvbmFtZSI6ImxvZ2lud3NkIiwiaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS93cy8yMDA4LzA2L2lkZW50aXR5L2NsYWltcy9yb2xlIjoid2FpdGVyIiwibmJmIjoxNTc2MDY1MTU5LCJleHAiOjE1Nzc3OTMxNTksImlzcyI6IkNSTVNlcnZlciIsImF1ZCI6IkNSTUZvb2QifQ.btgL4dMpF0sKcGKPC7zV_G-2GrL8TWHXYz-Z5hGCtJg", new CategoryId(id));
        call.enqueue(new Callback<List<SubMenu>>() {
            @Override
            public void onResponse(@NotNull Call<List<SubMenu>> call, @NotNull Response<List<SubMenu>> response) {
                view.getListOfMeals(response.body());
                Log.e(TAG, "onResponse " + response.body().toString());

            }

            @Override
            public void onFailure(@NotNull Call<List<SubMenu>> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure " + t.getMessage());

                view.showError();
            }
        });

    }
}
