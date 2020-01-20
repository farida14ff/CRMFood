package com.example.crmfood;

import com.example.crmfood.models.ActiveOrder;

import java.util.List;

public interface BaseContract {

    interface BaseView {
        void setListOfActiveOrders(List<ActiveOrder> body);
    }
}
