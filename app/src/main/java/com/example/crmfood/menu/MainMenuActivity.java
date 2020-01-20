package com.example.crmfood.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.crmfood.R;
import com.example.crmfood.adapters.FixedTabsPagerAdapter;
import com.example.crmfood.basket.BasketActivity;
import com.example.crmfood.menu.barMenu.BarMenuFragment;
import com.example.crmfood.menu.kitchenMenu.KitchenMenuFragment;
import com.example.crmfood.subMenu.SubMenuActivity;
import com.example.crmfood.tables.TablesActivity;
import com.google.android.material.tabs.TabLayout;

public class MainMenuActivity extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private KitchenMenuFragment kitchenMenuFragment;
    private BarMenuFragment barMenuFragment;
    private FixedTabsPagerAdapter adapter;
    long my_tableId;
    Intent intent1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        initIntents();
        initViews();
        initTabLayoutSelection();

    }

    private void initIntents() {

        Intent intent3 = getIntent();
        long my_activeOrdersId = intent3.getLongExtra("activeOrdersId",205);
        Log.e("activeOrdersId", "MainMenuActivity getExtra my_activeOrdersId: "+ my_activeOrdersId);


        Intent intent = getIntent();
        my_tableId = intent.getLongExtra("tableId",1);
        Log.e("tableId", "MainMenuActivity getExtra my_tbl: "+ my_tableId);


        intent1 = new Intent(MainMenuActivity.this, BasketActivity.class);
        intent1.putExtra("tableId",my_tableId);
        intent1.putExtra("activeOrdersId",my_activeOrdersId);
        Log.e("tableId", "MainMenuActivity putExtra my_tbl to basket: "+ my_tableId);
        Log.e("activeOrdersId", "MainMenuActivity putExtra to basket my_activeOrdersId to basket: "+ my_activeOrdersId);

        Intent intent2 = new Intent(MainMenuActivity.this, SubMenuActivity.class);
        intent2.putExtra("tableId",my_tableId);
        intent2.putExtra("activeOrdersId",my_activeOrdersId);
        Log.e("tableId", "MainMenuActivity putExtra my_tbl to sub: "+ my_tableId);
        Log.e("activeOrdersId", "MainMenuActivity putExtra to sub my_activeOrdersId to basket: "+ my_activeOrdersId);

    }


    private void initViews() {

        final LinearLayout basket_LL = findViewById(R.id.basket_sub);
        basket_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent1);
//                finish();
                //basket_LL.setEnabled(false);
            }
        });

        LinearLayout goBackIM = findViewById(R.id.go_back_icon);
        goBackIM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(MainMenuActivity.this, TablesActivity.class);
//                startActivity(intent);

                finish();
            }
        });

        Toolbar toolbar = findViewById(R.id.history_app_bar);
        (this).setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.tabs);

        viewPager = findViewById(R.id.viewPager);

        kitchenMenuFragment = new KitchenMenuFragment();

        barMenuFragment = new BarMenuFragment();

        adapter = new FixedTabsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(kitchenMenuFragment,"Кухня");
        adapter.addFragment(barMenuFragment,"Бар");
        viewPager.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setText("Кухня"));
        tabLayout.addTab(tabLayout.newTab().setText("Бар"));

        tabLayout.setupWithViewPager(viewPager);
    }

    public void initTabLayoutSelection(){
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
