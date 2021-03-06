package com.example.crmfood.models;

import java.util.List;

public class CreateOrder {

    private long TableId;
    private String Comment;
    private List<Basket> MealOrders;

    public CreateOrder(long tableId, String comment, List<Basket> mealOrders) {
        this.TableId = tableId;
        this.Comment = comment;
        this.MealOrders = mealOrders;
    }

    public long getTableId() {
        return TableId;
    }

    public void setTableId(long tableId) {
        this.TableId = tableId;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        this.Comment = comment;
    }

    public List<Basket> getMealOrders() {
        return MealOrders;
    }

    public void setMealOrders(List<Basket> mealOrders) {
        this.MealOrders = mealOrders;
    }
}
