package com.example.crmfood.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ActiveOrder implements Serializable {


    @SerializedName("id")
    private int id;
//    @SerializedName("tableName")
//    private int tableName;
    @SerializedName("mealOrders")
    private List<ListMealInActiveOrder> listMealInActiveOrders;

    public ActiveOrder(int id, List<ListMealInActiveOrder> listMealInActiveOrders) {
        this.id = id;
        this.listMealInActiveOrders = listMealInActiveOrders;
    }


    public ActiveOrder() {
    }

    public int getId() {
        return id;
    }


//    public int getTableName() {
//        return tableName;
//    }

    public List<ListMealInActiveOrder> getListMealInActiveOrders() {
        return listMealInActiveOrders;
    }

    public void setArrayList(List<ActiveOrder> acitiveOrderArray) {
    }
}
