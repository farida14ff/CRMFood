package com.example.crmfood.models;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "add_meal")
public class AddMealList {

    @PrimaryKey
    private long mealId;

    @ColumnInfo(name = "add_meal")
    private int addQuantity;

    public AddMealList(long mealId, int addQuantity) {
        this.mealId = mealId;
        this.addQuantity = addQuantity;
    }

    public long getMealId() {
        return mealId;
    }

    public void setMealId(long mealId) {
        this.mealId = mealId;
    }

    public int getAddQuantity() {
        return addQuantity;
    }

    public void setAddQuantity(int addQuantity) {
        this.addQuantity = addQuantity;
    }
}
