package com.example.crmfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.crmfood.login.LoginActivity;
import com.example.crmfood.main.MainFragment;

public class BaseActivity extends AppCompatActivity {

    MainFragment mainFragment = new MainFragment();
    final FragmentManager fragmentManager = getSupportFragmentManager();

    SharedPreferences sharedPreferences;
    public static String authToken;
//    public static String authToken = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVc2VySWQiOiIxNSIsImh0dHA6Ly9zY2hlbWFzLnhtbHNvYXAub3JnL3dzLzIwMDUvMDUvaWRlbnRpdHkvY2xhaW1zL25hbWUiOiJ3YWl0ZXIxIiwiaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS93cy8yMDA4LzA2L2lkZW50aXR5L2NsYWltcy9yb2xlIjoid2FpdGVyIiwibmJmIjoxNTc2OTM5MjQwLCJleHAiOjE1Nzg2NjcyNDAsImlzcyI6IkNSTVNlcnZlciIsImF1ZCI6IkNSTUZvb2QifQ.YMa7bq3eklm1btzwgaS616vEgu--VDKDOQaze9D0qq0";

    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getApplicationContext().getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        authToken = "Bearer " + sharedPreferences.getString("authToken", null);
        if (sharedPreferences.getString("authToken", null) == null) {
            Toast.makeText(this,getString(R.string.no_internet) , Toast.LENGTH_SHORT).show();

            Log.e("base.addAuthToken: ",String.format("%s",sharedPreferences.getString("authToken", null )));

            Intent intent = new Intent(BaseActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        Log.e("base.addAuthToken: afterif ",String.format("%s",sharedPreferences.getString("authToken", null )));

        setContentView(R.layout.activity_basic);

        if (savedInstanceState == null) {
            fragmentManager.beginTransaction().add(R.id.main_container, mainFragment, "1").commit();
        }


    }
}
