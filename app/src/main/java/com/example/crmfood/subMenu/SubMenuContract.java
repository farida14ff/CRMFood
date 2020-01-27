package com.example.crmfood.subMenu;

import com.example.crmfood.models.Basket;

import java.util.List;

public interface SubMenuContract {


    interface OnItemClickListener {
        void onItemClick(Basket subMenu);
    }



    interface Presenter {
        void displayMeals(long id);
    }

    interface View {
        void saveBasket(Basket b);
        void getListOfMeals(List<Basket> body);
        void initRecyclerView();
        void showError();
    }
}
