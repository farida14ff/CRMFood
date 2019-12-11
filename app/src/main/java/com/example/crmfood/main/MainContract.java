package com.example.crmfood.main;

import com.example.crmfood.models.ActiveOrder;
import com.example.crmfood.models.Table;

import java.util.List;

public interface MainContract {

    interface View {
        void setListOfActiveOrders(List<ActiveOrder> body);
        void showError();

    }

    interface OnItemClickListener {
        void onItemClick(ActiveOrder activeOrder);
    }

    interface Presenter{
        void displayListOfActiveOrders();
    }
}
