package com.example.crmfood.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.crmfood.models.AddMealList;
import com.example.crmfood.models.Basket;


@Database(entities = {Basket.class}, version = 6,exportSchema = false)
//@Database(entities = {Basket.class, AddMealList.class}, version = 5,exportSchema = false)
//@Database(entities = Basket.class, version = 1, exportSchema = false)
public abstract class ToBasketRoomDatabase extends RoomDatabase {

    public abstract ToBasketDao toBasketDao();

    private static volatile ToBasketRoomDatabase toBasketRoomInterface;

    public static ToBasketRoomDatabase getDatabase(final Context context){

        if (toBasketRoomInterface == null) {
            synchronized (ToBasketRoomDatabase.class) {
                toBasketRoomInterface = Room.databaseBuilder(context.getApplicationContext(),
                        ToBasketRoomDatabase.class, "basket2")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return toBasketRoomInterface;

    }

}

