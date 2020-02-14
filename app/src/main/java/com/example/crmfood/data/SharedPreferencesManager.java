package com.example.crmfood.data;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;


public class SharedPreferencesManager {

    private static SharedPreferencesManager sharePref;
    public static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
//    private static final String ACTIVE_ORDER_ID = "act_order_id";




    @SuppressLint("CommitPrefEdits")
    public static SharedPreferencesManager init(Context context) {
        if (sharePref == null) {
            sharedPreferences = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
        return sharePref;
    }

    public static void setTotalPrice(String key, Long value) {
        editor.putLong(key, value).apply();
    }

    public static Long getTotalPrice(String key, Long defValue) {
        return sharedPreferences.getLong(key, defValue);
    }


    public static void setValue(String key, Long value) {
        editor.putLong(key, value).apply();
    }

    public static Long getValue(String key, Long defValue) {
        return sharedPreferences.getLong(key, defValue);
    }




}
