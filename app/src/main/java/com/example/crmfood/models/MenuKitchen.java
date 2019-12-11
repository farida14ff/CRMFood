package com.example.crmfood.models;

import android.content.Intent;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MenuKitchen implements Serializable {


    @SerializedName("catetegoryId")
    private long categoryId;
    @SerializedName("category")
    private String categoryName;
    @SerializedName("image")
    private String categoryImage;



    public MenuKitchen(String categoryName, String categoryImage, int categoryId) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryImage = categoryImage;
    }

    public MenuKitchen(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }



    public String getCategoryImage() {
        return categoryImage;
    }


}
