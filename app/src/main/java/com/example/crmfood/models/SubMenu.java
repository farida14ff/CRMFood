package com.example.crmfood.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SubMenu implements Serializable {

    @SerializedName("id")
    private long sub_id;
    @SerializedName("name")
    private String sub_name;
    @SerializedName("price")
    private double sub_price;
    @SerializedName("mealStatus")
    private int sub_status;

    public SubMenu(long sub_id, String sub_name, double sub_price, int sub_status) {
        this.sub_id = sub_id;
        this.sub_name = sub_name;
        this.sub_price = sub_price;
        this.sub_status = sub_status;
    }

    public long getSub_id() {
        return sub_id;
    }

    public void setSub_id(long sub_id) {
        this.sub_id = sub_id;
    }

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    public double getSub_price() {
        return sub_price;
    }

    public void setSub_price(double sub_price) {
        this.sub_price = sub_price;
    }

    public int getSub_status() {
        return sub_status;
    }

    public void setSub_status(int sub_status) {
        this.sub_status = sub_status;
    }
}
