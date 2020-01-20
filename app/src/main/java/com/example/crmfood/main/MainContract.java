package com.example.crmfood.main;

import com.example.crmfood.BaseContract;
import com.example.crmfood.models.ActiveOrder;
import com.example.crmfood.models.Table;

import java.util.List;

public interface MainContract {

    interface View extends BaseContract.BaseView{
        void showConfirmLogoutDialog();
        void showError();
        boolean isConnected();


    }

    interface OnItemClickListener {
        void onItemClick(ActiveOrder activeOrder);
    }

    interface Presenter{
        void displayListOfActiveOrders();
        void closeCheque(long id);
    }
}
