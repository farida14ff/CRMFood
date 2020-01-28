package com.example.crmfood.menu.barMenu;

import com.example.crmfood.models.MenuBar;
import com.example.crmfood.models.MenuKitchen;

import java.util.List;

public interface BarMenuContract {

    interface View {
        void getBarCategoy(List<MenuBar> body);
        void showError();
        void showPodMenu(MenuBar menuBar);
        void hideProgressBar();
        void showEmptyView();

    }

    interface OnItemClickListener {
        void onItemClick(MenuBar menuBar);
    }

    interface Presenter{
        void displayBarCategoy();
    }
}

