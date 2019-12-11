package com.example.crmfood.barMenu;

import com.example.crmfood.models.MenuBar;
import com.example.crmfood.models.MenuKitchen;

import java.util.List;

public interface BarMenuContract {

    interface View {
        void getBarCategoy(List<MenuBar> body);
        void showError();
        void showPodMenu(MenuBar menuBar);

    }

    interface OnItemClickListener {
        void onItemClick(MenuBar menuBar);
    }

    interface Presenter{
        void displayBarCategoy();
    }
}

