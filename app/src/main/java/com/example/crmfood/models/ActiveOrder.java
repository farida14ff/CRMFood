package com.example.crmfood.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ActiveOrder implements Serializable {


    @SerializedName("id")
    public long idAcOr;
    @SerializedName("tableName")
    private int tableName;
    @SerializedName("mealOrders")
    private List<ListMealInActiveOrder> listMealInActiveOrders;

    public ActiveOrder(long idAcOr) {
        this.idAcOr = idAcOr;
    }

    public ActiveOrder(int id, List<ListMealInActiveOrder> listMealInActiveOrders) {
        this.idAcOr = id;
        this.listMealInActiveOrders = listMealInActiveOrders;
    }


    public ActiveOrder() {
    }

    public long getId() {
        return idAcOr;
    }


    public int getTableName() {
        return tableName;
    }

    public void setTableName(int tableName) {
        this.tableName = tableName;
    }

    public List<ListMealInActiveOrder> getListMealInActiveOrders() {
        return listMealInActiveOrders;
    }

    public void setArrayList(List<ActiveOrder> acitiveOrderArray) {
    }
}
