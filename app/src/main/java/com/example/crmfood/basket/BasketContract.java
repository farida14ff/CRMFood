package com.example.crmfood.basket;

import com.example.crmfood.models.AddMealList;
import com.example.crmfood.models.Basket;

import java.util.List;

public interface BasketContract {

    interface OnItemClickListener{
        void onItemClick(Basket basket);

    }



    interface Presenter{
        void sendCreatedOrder(long tableId,String comment,List<Basket> mealOrders);
        void sendAddMealOrder(long mealId,List<AddMealList> mealOrders);
    }

    interface View{
        void showConfirmLogoutDialog();
        boolean isConnected();
        void openActiveOrderList(List<Basket> body);
    }
}
