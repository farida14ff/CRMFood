package com.example.crmfood.basket;

import com.example.crmfood.models.AddMealList;
import com.example.crmfood.models.Basket2;

import java.util.List;

public interface BasketContract {

    interface OnItemClickListener{
        void onItemClick(Basket2 basket);
    }


    interface Presenter{
        void sendCreatedOrder(long tableId,String comment,List<Basket2> mealOrders);
        void sendAddMealOrder(long mealId,List<AddMealList> mealOrders);
    }

    interface View{
        void showConfirmLogoutDialog();
        boolean isConnected();
        void openActiveOrderList(List<Basket2> body);
    }
}
