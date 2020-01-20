package com.example.crmfood.data;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.crmfood.models.AddMealList;
import com.example.crmfood.models.Basket2;

import java.util.ArrayList;
import java.util.List;


@Dao
public interface ToBasketDao {

    @Insert
    void addItems(Basket2... basket);

    @Query("SELECT * FROM basket2")
    List<Basket2> getAllItems();

    @Query("DELETE FROM basket2 WHERE mealId = :id")
    void deleteItem(long id);

    @Delete
    void deleteAllItems(List<Basket2> basket2s);



    @Insert
    void addItemsAddMeal(AddMealList... addMealLists);

    @Query("SELECT * FROM add_meal")
    List<AddMealList> getAllItemsAddMeal();

    @Query("DELETE FROM add_meal WHERE mealId = :id")
    void deleteItemAddMeal(long id);
}
