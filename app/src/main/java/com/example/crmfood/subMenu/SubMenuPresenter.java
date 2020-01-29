package com.example.crmfood.subMenu;

import android.util.Log;

import com.example.crmfood.BaseActivity;
import com.example.crmfood.data.SharedPreferencesManager;
import com.example.crmfood.halpers.Funct;
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
    public static long act_ored_id;
    long def_val = 1;


    SubMenuPresenter(SubMenuContract.View view) {
        this.view = view;
    }

    @Override
    public void displayMeals(long id) {
        Call<List<SubMenu>> call = service.getSubMenu(BaseActivity.authToken, new CategoryId(id));
        call.enqueue(new Callback<List<SubMenu>>() {
            @Override
            public void onResponse(@NotNull Call<List<SubMenu>> call, @NotNull Response<List<SubMenu>> response) {

                act_ored_id = SharedPreferencesManager.getValue("ORDER_ID",def_val);

                if (response.body() != null && response.body().size() > 0) {
                    Log.i(TAG, "onResponse if");
                    view.getListOfMeals(Funct.getBusketList(response.body()));
                    view.hideProgressBar();
                } else {
                    Log.i(TAG, "onResponse else");
                    assert response.body() != null;
                    view.getListOfMeals(Funct.getBusketList(response.body()));
                    view.showEmptyView();
                }

                Log.e(TAG, "onResponse getTables");

            }

            @Override
            public void onFailure(@NotNull Call<List<SubMenu>> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure " + t.getMessage());
                view.showError();
            }
        });

    }

}
