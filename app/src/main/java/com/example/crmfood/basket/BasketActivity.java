package com.example.crmfood.basket;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.crmfood.BaseActivity;
import com.example.crmfood.R;
import com.example.crmfood.adapters.BasketAdapter;
import com.example.crmfood.menu.MainMenuActivity;
import com.example.crmfood.models.ActiveOrder;


import java.util.List;

import com.example.crmfood.data.ToBasketDao;
import com.example.crmfood.data.ToBasketRoomDatabase;
import com.example.crmfood.models.AddMealList;
import com.example.crmfood.models.Basket2;

public class BasketActivity extends AppCompatActivity  implements BasketContract.View {

    BasketContract.Presenter presenter;
    BasketAdapter adapter;

    List<Basket2> items_b;
//    List<AddMealList> items_b_add;

    private ToBasketRoomDatabase db;
    private ToBasketDao dao;

    private int sub_id;
    private String basket_name;
    private double basket_price;
    private int counter_menu_basket;
    private int basket_status;



//    @Override
//    protected void onResume() {
//        super.onResume();
//        int count = db.toBasketDao().getAllItems().size();
//        if(adapter != null)
//            adapter.notifyDataSetChanged();
//        Log.e("onResume count basket:",String.valueOf(count));
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        db = ToBasketRoomDatabase.getDatabase(this);
//        dao = db.toBasketDao();

        items_b = db.toBasketDao().getAllItems();
//        items_b_add = db.toBasketDao().getAllItemsAddMeal();



//        for (int i = 0; i <items_b2.size() ; i++) {
//            Log.e("BasketActivity", "items_b2: "+ items_b2);
//        }


        initRecyclerView();
        initBasketItems();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.basket_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        presenter = new BasketPresenter(this);


        adapter = new BasketAdapter(items_b,new  BasketContract.OnItemClickListener() {
            @Override
            public void onItemClick(Basket2 basket) {
                //TODO: modalWindow "about"
            }

        });

        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }

    @SuppressLint("SetTextI18n")
    private void initBasketItems(){
        TextView meals_quantity_tv = findViewById(R.id.meals_quantity);
        TextView total_price_tv = findViewById(R.id.total_price);
        Button confirm_button_b = findViewById(R.id.confirm_button);
        Button add_meal_button = findViewById(R.id.add_meal_button);


        LinearLayout goBackIM = findViewById(R.id.go_back_icon);
        goBackIM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(BasketActivity.this,MainMenuActivity.class);
//                startActivity(intent);
                finish();
            }
        });

        int arraysSize = 0;
        String wordsEnd = "";
//        if (items_b_add.size() < 1){
            arraysSize = items_b.size();
            if (arraysSize == 1){
                wordsEnd = "";
            } else if (arraysSize <= 4 ) {
                wordsEnd = "а";
            }
            else {
                wordsEnd = "ов";
            }
//        }else {
//            arraysSize = items_b_add.size();
//            if (arraysSize == 1){
//                wordsEnd = "";
//            } else if (arraysSize <= 4 ) {
//                wordsEnd = "а";
//            }
//            else {
//                wordsEnd = "ов";
//            }
//        }

         meals_quantity_tv.setText(arraysSize+" товар"+wordsEnd);


         String tp = String.valueOf(calculateTotalPrice());
         total_price_tv.setText(tp);

         add_meal_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(BasketActivity.this,MainMenuActivity.class);
//                startActivity(intent);
                finish();
            }
        });

        confirm_button_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConfirmLogoutDialog();

                //TODO: clear basket
                //TODO: connect basket to the tables id

            }
        });


    }

    @Override
    public void showConfirmLogoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(BasketActivity.this, R.style.AlertDialogTheme);
        builder.setTitle(getString(R.string.do_order_title));
        builder.setMessage(getString(R.string.do_order_message));
        builder.setNegativeButton(R.string.cancel_order, null);
        builder.setPositiveButton(getString(R.string.action_conf), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActiveOrder activeOrder = new ActiveOrder();

                Intent intent = getIntent();
                long tableID = intent.getLongExtra("tableId",1);
//                long activeOrdersID = intent.getLongExtra("activeOrdersId",1);
//                if (activeOrdersID == 1){
                presenter.sendCreatedOrder(tableID," ", items_b);
                db.toBasketDao().deleteAllItems(items_b);
               // }else {
               //     presenter.sendAddMealOrder(activeOrdersID,items_b_add);
               // }

//                presenter.sendCreatedOrder(tableID," ", items_b2);
                Intent intent1 = new Intent(BasketActivity.this,BaseActivity.class);
                startActivity(intent1);
                finish();
//                presenter.sendCreatedOrder(activeOrder.getId(),"",items_b);
                Log.e("tableId", "BasketActivity getLongExtra: "+ tableID);
                Log.e("BasketActivity", "items_b2: "+ items_b);


            }

        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }


    @Override
    public void openActiveOrderList(List<Basket2> body) {
        adapter.setValues(body);
    }


    public double calculateTotalPrice(){
        double totPrice = 0;
        for (int i = 0; i < items_b.size(); i++) {
           totPrice += items_b.get(i).getBasket_price();
        }
        Log.e("calculateTotalPrice: ", String.valueOf(totPrice));
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
}
