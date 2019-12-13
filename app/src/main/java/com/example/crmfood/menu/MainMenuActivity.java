package com.example.crmfood.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.crmfood.R;
import com.example.crmfood.adapters.FixedTabsPagerAdapter;
import com.example.crmfood.basket.BasketActivity;
import com.example.crmfood.menu.barMenu.BarMenuFragment;
import com.example.crmfood.menu.kitchenMenu.KitchenMenuFragment;
import com.google.android.material.tabs.TabLayout;

public class MainMenuActivity extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private KitchenMenuFragment kitchenMenuFragment;
    private BarMenuFragment barMenuFragment;
    private FixedTabsPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        initViews();
        initTabLayoutSelection();

    }


    private void initViews() {

        final LinearLayout basket_LL = findViewById(R.id.basket_sub);
        basket_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, BasketActivity.class);
                startActivity(intent);
                //basket_LL.setEnabled(false);
            }
        });

        LinearLayout goBackIM = findViewById(R.id.go_back_icon);
        goBackIM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
