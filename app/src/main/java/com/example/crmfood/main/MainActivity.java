package com.example.crmfood.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crmfood.R;
import com.example.crmfood.tables.TablesActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //        sharedPreferences = getApplicationContext().getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
//        authToken = "Token " + sharedPreferences.getString("authToken", null);
//        if (sharedPreferences.getString("authToken", null) == null) {
//            Intent intent = new Intent(BaseActivity.this, LoginActivity.class);
//            startActivity(intent);
//            finish();
//            return;
//        }



        Button createNewOrderButton = findViewById(R.id.create_new_order_button);
        Button listOfActiveOrdersButton = findViewById(R.id.list_of_active_orders_button);


        createNewOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TablesActivity.class);
                startActivity(intent);

            }
        });

        listOfActiveOrdersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
