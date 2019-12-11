package com.example.crmfood.subMenu;

import com.example.crmfood.models.MenuKitchen;
import com.example.crmfood.models.SubMenu;

import java.util.List;

public interface SubMenuContract {


    interface OnItemClickListener{
        void onItemClick(SubMenu subMenu);
    }


    interface Presenter{
        void displayMeals(long id);
    }

    interface View{
        void getListOfMeals(List<SubMenu> body);
        void showError();
    }
}
