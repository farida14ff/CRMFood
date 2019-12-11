package com.example.crmfood.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PodMenu implements Serializable {

    @SerializedName("id")
    private long id;
    @SerializedName("name")
    private String podMenuName;
    @SerializedName("weight")
    private String podMenuWeight;
    @SerializedName("price")
    private double podMenuPrice;
    @SerializedName("description")
    private String podMenuDescription;
    @SerializedName("mealStatus")
    private int podMenuMealStatus;

    public PodMenu(long id, String podMenuName, String podMenuWeight, double podMenuPrice, String podMenuDescription, int podMenuMealStatus) {
        this.id = id;
        this.podMenuName = podMenuName;
        this.podMenuWeight = podMenuWeight;
        this.podMenuPrice = podMenuPrice;
        this.podMenuDescription = podMenuDescription;
        this.podMenuMealStatus = podMenuMealStatus;
    }

    public long getId() {
        return id;
    }

    public String getPodMenuName() {
        return podMenuName;
    }

    public String getPodMenuWeight() {
        return podMenuWeight;
    }

    public double getPodMenuPrice() {
        return podMenuPrice;
    }

    public String getPodMenuDescription() {
        return podMenuDescription;
    }

    public int getPodMenuMealStatus() {
        return podMenuMealStatus;
    }
}
