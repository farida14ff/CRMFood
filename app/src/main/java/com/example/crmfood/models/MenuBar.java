package com.example.crmfood.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MenuBar implements Serializable {


    @SerializedName("category")
    private String categoryName;
    @SerializedName("image")
    private String categoryImage;



    public MenuBar(String categoryName, String categoryImage) {
        this.categoryName = categoryName;
        this.categoryImage = categoryImage;
    }

    public String getCategoryName() {
        return categoryName;
    }



    public String getCategoryImage() {
        return categoryImage;
    }


}
