package com.example.crmfood.basket;

import android.util.Log;

import com.example.crmfood.BaseActivity;
import com.example.crmfood.models.AddMeal;
import com.example.crmfood.models.AddMealList;
import com.example.crmfood.models.Basket;
import com.example.crmfood.models.CreateOrder;
import com.example.crmfood.models.StatusMessage;
import com.example.crmfood.network.MyService;
import com.example.crmfood.network.RetrofitClientInstance;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class BasketPresenter implements BasketContract.Presenter{
    private BasketContract.View view;
    private MyService service = RetrofitClientInstance.getRetrofitInstance().create(MyService.class);


    BasketPresenter(BasketContract.View view) {
        this.view = view;
    }




    @Override
    public void sendCreatedOrder(final long tableId, final String comment, final List<Basket> mealOrders) {
        Call<StatusMessage> call = service.createOrder(BaseActivity.authToken, new CreateOrder(tableId, comment, mealOrders));
        call.enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NotNull Call<StatusMessage> call, @NotNull Response<StatusMessage> response) {
                if (response.isSuccessful()){
                    Log.e(TAG, "onResponse sendCreatedOrder ");
                    view.hideProgressBar();
                }
                else {
                    if (view.isConnected()) {
                        view.showEmptyView();
                        Log.e("onResponse", "else -if");
                        Log.e(TAG, response.message());
                    } else {
                        view.showEmptyView();
                        Log.e("onResponse", "else else");
                    }
                }
                view.stopRefreshingOrders();

                Log.e("tableId", "onClick: "+ tableId);
                Log.e("comment", "onClick: "+ comment);

            }

            @Override
            public void onFailure(@NotNull Call<StatusMessage> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure sendOrder " + t.getMessage());
                view.showError();

            }
        });
    }

    @Override
    public void sendAddMealOrder(final long mealId, List<AddMealList> mealOrders) {

        Call<StatusMessage> call = service.addMealsOrder(BaseActivity.authToken, new AddMeal(mealId, mealOrders));
        call.enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NotNull Call<StatusMessage> call, @NotNull Response<StatusMessage> response) {
                if (response.isSuccessful()){
                    Log.e(TAG, "onResponse addOrder ");
                    view.hideProgressBar();
                }
                else {
                    if (view.isConnected()) {
                        Log.e("onResponse addOrder", "else -if");
                        Log.e(TAG, response.message());
                        view.showEmptyView();
                    } else {
                        view.showEmptyView();
                        Log.e("onResponse addOrder", "else else");
                    }
                }

                Log.e("tableId", "onClick: "+ mealId);


            }

            @Override
            public void onFailure(@NotNull Call<StatusMessage> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure addOrder " + t.getMessage());
                view.showError();
            }
        });
    }


}
