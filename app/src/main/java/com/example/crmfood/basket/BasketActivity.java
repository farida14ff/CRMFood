package com.example.crmfood.basket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.crmfood.R;
import com.example.crmfood.adapters.BasketAdapter;
import com.example.crmfood.adapters.SubMenuAdapter;
import com.example.crmfood.models.Basket;
import com.example.crmfood.models.SubMenu;
import com.example.crmfood.subMenu.SubMenuContract;
import com.example.crmfood.subMenu.SubMenuPresenter;

public class BasketActivity extends AppCompatActivity  implements BasketContract.View {
    BasketContract.Presenter presenter;
    BasketAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        LinearLayout goBackIM = findViewById(R.id.go_back_icon);
        goBackIM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        initRecyclerView();
        initBasketItems();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.basket_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        presenter = new BasketPresenter(this);

        adapter = new BasketAdapter(new BasketContract.OnItemClickListener() {
            @Override
            public void onItemClick(Basket basket) {

            }
        });


        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }

    private void initBasketItems(){
        TextView meals_quantity_tv = findViewById(R.id.meals_quantity);
        TextView total_price_tv = findViewById(R.id.total_price);
        Button confirm_button_b = findViewById(R.id.confirm_button);
        Button add_meal_button = findViewById(R.id.add_meal_button);

        //TODO: set values from room
        //TODO: createNewOrder query

    }
}
