package com.example.crmfood.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ListMealInActiveOrder  implements Serializable {

    @SerializedName("meal")
    private String meal;
    @SerializedName("status")
    private String status;



    public ListMealInActiveOrder(String meal, String status) {
        this.meal = meal;
        this.status = status;
    }

    public ListMealInActiveOrder() {

    }

    public String getMeal() {
        return meal;
    }

    public String getStatus() {
        return status;
    }


}
