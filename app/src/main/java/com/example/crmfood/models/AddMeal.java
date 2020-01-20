package com.example.crmfood.models;

import java.util.List;

public class AddMeal {

    private long orderId;
    private List<AddMealList> mealOrders;

    public AddMeal(long orderId, List<AddMealList> mealOrders) {
        this.orderId = orderId;
        this.mealOrders = mealOrders;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public List<AddMealList> getMealOrders() {
        return mealOrders;
    }

    public void setMealOrders(List<AddMealList> mealOrders) {
        this.mealOrders = mealOrders;
    }
}
