package com.example.crmfood.menu.kitchenMenu;

import android.util.Log;

import com.example.crmfood.menu.kitchenMenu.KitchenMenuContract;
import com.example.crmfood.models.MenuKitchen;
import com.example.crmfood.network.MyService;
import com.example.crmfood.network.RetrofitClientInstance;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class KitchenMenuPresenter implements KitchenMenuContract.Presenter{
    private KitchenMenuContract.View view;
    private MyService service = RetrofitClientInstance.getRetrofitInstance().create(MyService.class);

    public KitchenMenuPresenter(KitchenMenuContract.View view) {
        this.view = view;
    }

    @Override
    public void displayMenuCategoy() {
        Call<List<MenuKitchen>> call = service.getMenuKitchen("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVc2VySWQiOiI3IiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvbmFtZSI6ImxvZ2lud3NkIiwiaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS93cy8yMDA4LzA2L2lkZW50aXR5L2NsYWltcy9yb2xlIjoid2FpdGVyIiwibmJmIjoxNTc2MDY1MTU5LCJleHAiOjE1Nzc3OTMxNTksImlzcyI6IkNSTVNlcnZlciIsImF1ZCI6IkNSTUZvb2QifQ.btgL4dMpF0sKcGKPC7zV_G-2GrL8TWHXYz-Z5hGCtJg");
        call.enqueue(new Callback<List<MenuKitchen>>() {
            @Override
            public void onResponse(@NotNull Call<List<MenuKitchen>> call, @NotNull Response<List<MenuKitchen>> response) {
                view.getMenuCategoy(response.body());
                Log.i(TAG, "onResponse ");
            }

            @Override
            public void onFailure(@NotNull Call<List<MenuKitchen>> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure " + t.getMessage());

                view.showError();
            }

        });
    }

}
