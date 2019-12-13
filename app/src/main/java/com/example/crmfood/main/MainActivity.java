package com.example.crmfood.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crmfood.R;
import com.example.crmfood.adapters.ActiveOrdersAdapter;
//import com.example.crmfood.login.LoginActivity;
import com.example.crmfood.models.ActiveOrder;
import com.example.crmfood.models.ListMealInActiveOrder;
import com.example.crmfood.tables.TablesActivity;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainContract.Presenter presenter;
    private ActiveOrdersAdapter adapter;
    private List<ActiveOrder> acitiveOrderArray;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerViewWithAdapter();


        final ImageView createNewOrderButton = findViewById(R.id.create_new_order_button);
        createNewOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TablesActivity.class);
                startActivity(intent);
                //createNewOrderButton.setEnabled(false);

            }
        });

        setData();

    }

    @Override
    public void setListOfActiveOrders(List<ActiveOrder> body) {
        adapter.setValues(body);
    }

    private void initRecyclerViewWithAdapter() {
        RecyclerView recyclerView = findViewById(R.id.list_of_active_orders_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        adapter = new ActiveOrdersAdapter(this, acitiveOrderArray, new MainContract.OnItemClickListener() {
            @Override
            public void onItemClick(ActiveOrder activeOrder) {
            }
        });



//        adapter = new ActiveOrdersAdapter(new MainContract.OnItemClickListener() {
//            int a = View.GONE;
//            @Override
//            public void onItemClick(ActiveOrder activeOrder) {
////                if (a == View.VISIBLE) {
////
////                    recyclerView.setVisibility(a);
////                    a = View.GONE;
////                }else {
////                    recyclerView.setVisibility(a);
////                    a = View.VISIBLE;
////                }
//            }
//
//        });

        presenter = new MainPresenter(this);
        presenter.displayListOfActiveOrders();

        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }

    @Override
    public void showError() {
        Toast.makeText(this, getString(R.string.orders_error), Toast.LENGTH_LONG).show();

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 100) {
            presenter.displayListOfActiveOrders();
        }
    }

    private void setData(){
        List<ListMealInActiveOrder> mealsList = new ArrayList<>();
        ListMealInActiveOrder listMealInActiveOrder = new ListMealInActiveOrder();
        ActiveOrder activeOrder = new ActiveOrder();
        activeOrder.setArrayList(acitiveOrderArray);

        adapter.notifyDataSetChanged();
    }
}
