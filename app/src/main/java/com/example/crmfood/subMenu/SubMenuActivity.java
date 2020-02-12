package com.example.crmfood.subMenu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.crmfood.R;
import com.example.crmfood.data.SharedPreferencesManager;
import com.example.crmfood.adapters.SubMenuAdapter;
import com.example.crmfood.models.AddMealList;
import com.example.crmfood.models.Basket;

import java.util.List;

import com.example.crmfood.data.ToBasketRoomDatabase;

public class SubMenuActivity extends AppCompatActivity implements SubMenuContract.View {


    private SubMenuContract.Presenter presenter;
    private SubMenuAdapter adapter;
    private LinearLayout emptyView;
    private RecyclerView recyclerView;
    ProgressBar progressBar;

    public long activeOrdersId;

    public static long act_ored_id;
    long def_val = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_menu);


        act_ored_id = SharedPreferencesManager.getValue("ORDER_ID",def_val);

        initViews();
        initRecyclerView();

    }

    private void initViews() {

        LinearLayout goBackIM = findViewById(R.id.go_back_icon);
        goBackIM.setOnClickListener(e -> {
            finish();
        });

        progressBar = findViewById(R.id.subMenu_progress_bar);
        emptyView = findViewById(R.id.empty_view_subMenu);

//        final LinearLayout basket_LL = findViewById(R.id.basket_sub);
//        basket_LL.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(intent1);
//                //basket_LL.setEnabled(false);
//            }
//        });


        final Button next_btn = findViewById(R.id.next_button);
        next_btn.setOnClickListener(e -> finish());

    }

    public void saveBasket(Basket b) {
        ToBasketRoomDatabase db = ToBasketRoomDatabase.getDatabase(this);

        Basket basket = db.toBasketDao().getItem(b.getMealId());

        if (basket == null) {
            db.toBasketDao().addItems(b);
            Log.e("SubMenuActivity","addMealList created");

        } else {
            db.toBasketDao().update(b.getOrderedQuantity(), b.getMealId());
            Log.e("SubMenu activity","addMealList updated");
        }

    }

    public void saveAddMeal(AddMealList aml) {
        ToBasketRoomDatabase db = ToBasketRoomDatabase.getDatabase(this);

        AddMealList addMealList = db.toBasketDao().getAddItem(aml.getMealId());

        if (addMealList == null) {
            db.toBasketDao().addItemsAddMeal(aml);
            Log.e("SubMenuActivity","addMealList created");

        } else {
            db.toBasketDao().updateAddMeal(aml.getAddQuantity(), aml.getMealId());
            Log.e("SubMenu activity","addMealList updated");
        }

    }

    public void deleteFromAddMealBasket(AddMealList addMealList){
        ToBasketRoomDatabase db = ToBasketRoomDatabase.getDatabase(this);

        db.toBasketDao().deleteItemAddMeal(addMealList.getMealId());

    }


    public void deleteFromBasket(Basket b){
        ToBasketRoomDatabase db = ToBasketRoomDatabase.getDatabase(this);
        db.toBasketDao().deleteItem(b.getMealId());

    }

    @Override
    public void initRecyclerView() {
        recyclerView = findViewById(R.id.sub_menu_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        String categoryName = intent.getStringExtra("category");
        long id = intent.getLongExtra("categoryId", 3);
        activeOrdersId = intent.getLongExtra("activeOrdersId", 1);
        Log.e("SubMenuActivity", "activeOrdersId: " + activeOrdersId);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.app_bar_sub_menu);
        toolbar.setTitle(categoryName);

        if (adapter == null) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);

            adapter = new SubMenuAdapter(e -> {

            });
            presenter = new SubMenuPresenter(this);
            presenter.displayMeals(id);
        }

        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


    }

    @Override
    public void getListOfMeals(List<Basket> body) {
        adapter.setValues(body);
    }

    @Override
    public void showError() {
        Toast.makeText(this, getString(R.string.meals_error), Toast.LENGTH_LONG).show();
        progressBar.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 100) {
            Intent intent = getIntent();
            long id = intent.getLongExtra("categoryId", 2);
            presenter.displayMeals(id);
        }
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyView() {
        progressBar.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }
}
