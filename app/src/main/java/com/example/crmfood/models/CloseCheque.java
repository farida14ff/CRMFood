package com.example.crmfood.models;

import java.util.ArrayList;

public class CloseCheque {

    private long id;
    private long userId;
    private long tableId;
    private String comment;
    private ArrayList<Basket> mealOrders;

    public CloseCheque(long id, long userId, long tableId, String comment, ArrayList<Basket> mealOrders) {
        this.id = id;
        this.userId = userId;
        this.tableId = tableId;
        this.comment = comment;
        this.mealOrders = mealOrders;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getTableId() {
        return tableId;
    }

    public void setTableId(long tableId) {
        this.tableId = tableId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ArrayList<Basket> getMealOrders() {
        return mealOrders;
    }

    public void setMealOrders(ArrayList<Basket> mealOrders) {
        this.mealOrders = mealOrders;
    }
}
