package com.example.crmfood.data;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.crmfood.models.AddMealList;
import com.example.crmfood.models.Basket;

import java.util.List;


@Dao
public interface ToBasketDao {

    @Insert
    void addItems(Basket... basket);

    @Query("SELECT * FROM Basket")
    List<Basket> getAllItems();

    @Query("SELECT * FROM Basket WHERE mealId = :id")
    Basket getItem(long id);

    @Query("DELETE FROM Basket WHERE mealId = :id")
    void deleteItem(long id);

    @Delete
    void deleteAllItems(List<Basket> basket2s);

    @Query("UPDATE Basket SET basket_counter_menu = :num WHERE mealId = :id")
    void update(int num,long id);




    @Insert
    void addItemsAddMeal(AddMealList... addMealLists);

    @Query("SELECT * FROM add_meal WHERE mealId = :id")
    AddMealList getAddItem(long id);

    @Query("SELECT * FROM add_meal")
    List<AddMealList> getAllItemsAddMeal();

    @Query("DELETE FROM add_meal WHERE mealId = :id")
    void deleteItemAddMeal(long id);

    @Delete
    void deleteAllAddItems(List<AddMealList> addMealLists);

    @Query("UPDATE add_meal SET add_meal_counter = :num WHERE mealId = :id")
    void updateAddMeal(int num,long id);

}
