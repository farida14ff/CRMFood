package com.example.crmfood.menu.barMenu;


import android.util.Log;


import com.example.crmfood.BaseActivity;
import com.example.crmfood.menu.barMenu.BarMenuContract;
import com.example.crmfood.models.MenuBar;
import com.example.crmfood.network.MyService;
import com.example.crmfood.network.RetrofitClientInstance;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class BarMenuPresenter implements BarMenuContract.Presenter{
    private BarMenuContract.View view;
    private MyService service = RetrofitClientInstance.getRetrofitInstance().create(MyService.class);

    public BarMenuPresenter(BarMenuContract.View view) {
        this.view = view;
    }

    @Override
    public void displayBarCategoy() {
        Call<List<MenuBar>> call = service.getMenuBar(BaseActivity.authToken);
        call.enqueue(new Callback<List<MenuBar>>() {
            @Override
            public void onResponse(@NotNull Call<List<MenuBar>> call, @NotNull Response<List<MenuBar>> response) {
                view.getBarCategoy(response.body());
                Log.i(TAG, "onResponse ");
            }

            @Override
            public void onFailure(@NotNull Call<List<MenuBar>> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure " + t.getMessage());

                view.showError();
            }

        });
    }

}

