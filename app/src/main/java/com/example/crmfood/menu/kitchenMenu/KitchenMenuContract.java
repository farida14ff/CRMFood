package com.example.crmfood.menu.kitchenMenu;

import com.example.crmfood.models.MenuKitchen;

import java.util.List;

public interface KitchenMenuContract {

    interface View {
        void getMenuCategoy(List<MenuKitchen> body);
        void showError();
        void showPodMenu(MenuKitchen menuKitchen);

    }

    interface OnItemClickListener {
        void onItemClick(MenuKitchen menuKitchen);
    }

    interface Presenter{
        void displayMenuCategoy();
    }
}
