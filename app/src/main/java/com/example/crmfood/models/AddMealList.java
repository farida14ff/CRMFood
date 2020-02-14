package com.example.crmfood.models;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//@Entity(tableName = "add_meal")
public class AddMealList {

    //@PrimaryKey
    private long mealId;

    //@ColumnInfo(name = "add_meal_name")
    private String add_meal_name;

    //@ColumnInfo(name = "add_meal_price")
    private double add_meal_price;

    //@ColumnInfo(name = "add_meal_counter")
    private int addQuantity;

    public AddMealList(long mealId, int addQuantity) {
        this.mealId = mealId;
        this.addQuantity = addQuantity;
    }

    public AddMealList() {

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

    public String getAdd_meal_name() {
        return add_meal_name;
    }

    public void setAdd_meal_name(String add_meal_name) {
        this.add_meal_name = add_meal_name;
    }

    public double getAdd_meal_price() {
        return add_meal_price;
    }

    public void setAdd_meal_price(double add_meal_price) {
        this.add_meal_price = add_meal_price;
    }

    public void decrease(){
        if(addQuantity > 0){
            addQuantity--;
        }
    }
    public void increase(){
        addQuantity++;
    }

    @Override
    public String toString() {
        return "AddMealList{" +
                "mealId=" + mealId +
                ", add_meal_name='" + add_meal_name + '\'' +
                ", add_meal_price=" + add_meal_price +
                ", addQuantity=" + addQuantity +
                '}';
    }


}
