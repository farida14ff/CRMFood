package com.example.crmfood.menu.kitchenMenu;

import android.util.Log;

import com.example.crmfood.BaseActivity;
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
        Call<List<MenuKitchen>> call = service.getMenuKitchen(BaseActivity.authToken);
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
