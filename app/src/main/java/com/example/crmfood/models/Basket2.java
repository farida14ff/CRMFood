package com.example.crmfood.models;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "basket2")
public class Basket2 {

    @PrimaryKey(autoGenerate = true)
    private long mealId;

    @ColumnInfo(name = "basket_name")
    private String basket_name;

    @ColumnInfo(name = "basket_price")
    private double basket_price;

    @ColumnInfo(name = "basket_counter_menu")
    private int orderedQuantity;


    public Basket2(long mealId, String basket_name, double basket_price, int orderedQuantity) {
        this.mealId = mealId;
        this.basket_name = basket_name;
        this.basket_price = basket_price;
        this.orderedQuantity = orderedQuantity;
    }
 //   public Basket2(long mealId, int orderedQuantity) {
//        this.mealId = mealId;
//        this.orderedQuantity = orderedQuantity;
//    }

    @Ignore
    public Basket2() {
        super();
    }

    public long getMealId() {
        return mealId;
    }

    public void setMealId(long mealId) {
        this.mealId = mealId;
    }

    public String getBasket_name() {
        return basket_name;
    }

    public void setBasket_name(String basket_name) {
        this.basket_name = basket_name;
    }

    public double getBasket_price() {
        return basket_price;
    }

    public void setBasket_price(double basket_price) {
        this.basket_price = basket_price;
    }

    public int getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(int orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }
}
