package com.example.crmfood.models;

import java.util.ArrayList;
import java.util.List;

public class CreateOrder {

    private long tableId;
    private String comment;
//    private ArrayList<CreateOrderList> mealOrders;
    private List<Basket2> mealOrders;

//    public CreateOrder(long tableId, String comment, ArrayList<Basket2> mealOrders) {
//        this.tableId = tableId;
//        this.comment = comment;
//        this.mealOrders = mealOrders;
//    }

    public CreateOrder(long tableId, String comment, List<Basket2> mealOrders) {
        this.tableId = tableId;
        this.comment = comment;
        this.mealOrders = mealOrders;
    }
//    public CreateOrder(long tableId, String comment, ArrayList<CreateOrderList> mealOrders) {
//        this.tableId = tableId;
//        this.comment = comment;
//        this.mealOrders = mealOrders;
//    }


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

    public List<Basket2> getMealOrders() {
        return mealOrders;
    }

    public void setMealOrders(List<Basket2> mealOrders) {
        this.mealOrders = mealOrders;
    }
}
