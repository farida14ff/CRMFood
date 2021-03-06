package com.example.crmfood.basket;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crmfood.BaseActivity;
import com.example.crmfood.R;
import com.example.crmfood.data.SharedPreferencesManager;
import com.example.crmfood.adapters.BasketAdapter;
import com.example.crmfood.menu.MainMenuActivity;
import com.example.crmfood.models.ActiveOrder;


import java.util.ArrayList;
import java.util.List;

import com.example.crmfood.data.ToBasketRoomDatabase;
import com.example.crmfood.models.AddMealList;
import com.example.crmfood.models.Basket;

public class BasketActivity extends AppCompatActivity implements BasketContract.View {

    BasketContract.Presenter presenter;
    BasketAdapter adapter;


    List<Basket> items_b;
    List<AddMealList> items_b_add;

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayout emptyView;
    ProgressBar progressBar;

    private ToBasketRoomDatabase db;
    public static long act_ored_id;
    long def_val = 0;

    TextView total_price_tv;



    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        initRoomAndShPref();

        Log.e("BasketActivity", "items_b: " + items_b);
        items_b_add = arrayParse(items_b);
        Log.e("BasketActivity", "items_b_add: " + items_b_add);

        initBasketItems();
        initSwipeRefreshLayout();
        initRecyclerView();

    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.basket_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (adapter == null) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);

            adapter = new BasketAdapter(items_b, e -> {
                //TODO: modalWindow "about"

            });
            presenter = new BasketPresenter(this);
            hideProgressBar();

        }

        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }

    private void initSwipeRefreshLayout() {
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout_basket);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.rippleColor), getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout.setOnRefreshListener(() -> {
            initRoomAndShPref();
            stopRefreshingOrders();
        });

    }

    @SuppressLint("SetTextI18n")
    private void initBasketItems() {
        TextView meals_quantity_tv = findViewById(R.id.meals_quantity);
        total_price_tv = findViewById(R.id.total_price);
        Button confirm_button_b = findViewById(R.id.confirm_button);
        Button add_meal_button = findViewById(R.id.add_meal_button);


        LinearLayout goBackIM = findViewById(R.id.go_back_icon);
        goBackIM.setOnClickListener(e ->{
            Intent intent = new Intent(this, MainMenuActivity.class);
            startActivity(intent);
            finish();
        });

        emptyView = findViewById(R.id.empty_view_basket);
        progressBar = findViewById(R.id.progress_bar_basket);
        add_meal_button.setOnClickListener(e -> finish());

        confirm_button_b.setOnClickListener(e -> {
            showConfirmLogoutDialog();

            //TODO: clear addMealList
            //TODO: connect addMealList to the tables id

        });


    }

    public void initRoomAndShPref() {
        act_ored_id = SharedPreferencesManager.getValue("ORDER_ID", def_val);

        db = ToBasketRoomDatabase.getDatabase(this);

        items_b = db.toBasketDao().getAllItems();
        countMealsQuantity();

    }

    @Override
    public void showConfirmLogoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(BasketActivity.this, R.style.AlertDialogTheme);
        builder.setTitle(getString(R.string.do_order_title));
        builder.setMessage(getString(R.string.do_order_message));
        builder.setNegativeButton(R.string.cancel_order, null);
        builder.setPositiveButton(getString(R.string.action_conf), (dialog, which) -> {

            Intent intent = getIntent();
            long tableID = intent.getLongExtra("tableId", 1);

            if (act_ored_id == def_val) {
                presenter.sendCreatedOrder(tableID, " ", items_b);
                db.toBasketDao().deleteAllItems(items_b);
            } else {
                items_b_add = arrayParse(items_b);
                presenter.sendAddMealOrder(act_ored_id, items_b_add);
                db.toBasketDao().deleteAllItems(items_b);
            }

            Intent intent1 = new Intent(BasketActivity.this, BaseActivity.class);
            startActivity(intent1);
            finish();
            Log.e("tableId", "BasketActivity tableId: " + tableID);
            Log.e("BasketActivity", "items_b2: " + items_b);


        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public List<AddMealList> arrayParse(List<Basket> items_b) {
        ArrayList<AddMealList> g = new ArrayList<>();
        for (Basket basket: items_b){
            AddMealList addMealList = new AddMealList();
            addMealList.setAdd_meal_name(basket.getBasket_name());
            addMealList.setMealId(basket.getMealId());
            addMealList.setAddQuantity(basket.getOrderedQuantity());
            addMealList.setAdd_meal_price(basket.getBasket_price());
            g.add(addMealList);
        }
        return g;
    }



    @SuppressLint("LongLogTag")
    void saveTotalPrice(Long tot_price) {
        SharedPreferencesManager.setTotalPrice("total_price",tot_price);
        Log.i("SharedPreferencesManager", "total_price: " +tot_price);
    }


    @Override
    public void openActiveOrderList(List<Basket> body) {
        adapter.setValues(body);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
    }

    @Override
    public void stopRefreshingOrders() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showEmptyView() {
        progressBar.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        Toast.makeText(this, getString(R.string.basket_orders_error), Toast.LENGTH_LONG).show();
        swipeRefreshLayout.setRefreshing(false);
        adapter.setValues(null);
        progressBar.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }


    public double calculateTotalPrice() {
        double totPrice = 0;
        for (int i = 0; i < items_b.size(); i++) {
                totPrice += items_b.get(i).getBasket_price()*items_b.get(i).getOrderedQuantity();
        }
        Log.e("calculateTotalPrice: ", String.valueOf(totPrice));
        saveTotalPrice((long) totPrice);
        return totPrice;

    }


    @Override
    public boolean isConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public void saveBasket(Basket b) {
        ToBasketRoomDatabase db = ToBasketRoomDatabase.getDatabase(this);

        Basket basket = db.toBasketDao().getItem(b.getMealId());

        if (basket == null) {
            db.toBasketDao().addItems(b);
            Log.e("SubMenuActivity","basket created");

        } else {
            db.toBasketDao().update(b.getOrderedQuantity(), b.getMealId());
            Log.e("SubMenu activity","basket updated");
        }

    }


    public void deleteFromBasket(Basket b){
        ToBasketRoomDatabase db = ToBasketRoomDatabase.getDatabase(this);

        db.toBasketDao().deleteItem(b.getMealId());

    }



    @SuppressLint("SetTextI18n")
    public void countMealsQuantity(){
        TextView meals_quantity_tv = findViewById(R.id.meals_quantity);
        TextView total_price_tv = findViewById(R.id.total_price);

        int arraysSize;
        String wordsEnd;
        arraysSize = items_b.size();
        if (arraysSize == 1) {
            wordsEnd = "";
        } else if (arraysSize <= 4) {
            wordsEnd = "а";
        } else {
            wordsEnd = "ов";
        }

        meals_quantity_tv.setText(arraysSize + " товар" + wordsEnd);

        String tp = String.valueOf(calculateTotalPrice());
        total_price_tv.setText(tp);

    }



}
