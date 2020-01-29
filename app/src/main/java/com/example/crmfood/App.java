package com.example.crmfood;

import android.app.Application;

import com.example.crmfood.data.SharedPreferencesManager;
import com.example.crmfood.data.ToBasketRoomDatabase;

public class App extends Application {
    ToBasketRoomDatabase db;
    SharedPreferencesManager myPref;

    @Override
    public void onCreate() {
        super.onCreate();
        db = ToBasketRoomDatabase.getDatabase(getApplicationContext());
        myPref = SharedPreferencesManager.init(getApplicationContext());
//        myPref.sharedPreferences;
    }



    public ToBasketRoomDatabase getDb() {
        return db;
    }

    public SharedPreferencesManager getMyPref() {
        return myPref;
    }
}

//old