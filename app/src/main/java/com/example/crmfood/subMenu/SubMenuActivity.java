package com.example.crmfood.subMenu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.crmfood.R;
import com.example.crmfood.adapters.SubMenuAdapter;
import com.example.crmfood.basket.BasketActivity;
import com.example.crmfood.menu.MainMenuActivity;
import com.example.crmfood.models.SubMenu;

import java.util.List;

import com.example.crmfood.data.ToBasketRoomDatabase;

public class SubMenuActivity extends AppCompatActivity implements SubMenuContract.View {

    private SubMenuContract.Presenter presenter;
    private SubMenuAdapter adapter ;

    public long activeOrdersId;

    private long sb_id;
    private String sb_name;
    private double sb_price;
    private int counter_menu_sb;

    SubMenu subMenu = new SubMenu();
    Intent intent1;
    long my_tableId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_menu);


        initRecyclerView();
        initViews();

    }

    @SuppressLint("WrongViewCast")
    private void initViews() {

        LinearLayout goBackIM = findViewById(R.id.go_back_icon);
        goBackIM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        final LinearLayout basket_LL = findViewById(R.id.basket_sub);
//        basket_LL.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(intent1);
//                //basket_LL.setEnabled(false);
//            }
//        });


        final Button next_btn = findViewById(R.id.next_button);
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(SubMenuActivity.this, MainMenuActivity.class);
//                startActivity(intent);
                //TODO: send login to the presenter
                //adapter.basketObjToRoom(adapter.id,adapter.view1,adapter.my_name,adapter.my_price,adapter.my_counter,adapter.db,adapter.basket);
                finish();

                //next_btn.setEnabled(false);
            }
        });




    }


    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.sub_menu_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        presenter = new SubMenuPresenter(this);

        Intent intent = getIntent();
        String categoryName = intent.getStringExtra("category");
        long id = intent.getLongExtra("categoryId",3);
        activeOrdersId = intent.getLongExtra("activeOrdersId",1);
        Log.e("SubMenuActivity", "activeOrdersId: "+ activeOrdersId);
//        my_tableId = intent.getLongExtra("tableId",1);
//        Log.e("tableId", "SubMenuActivity getExtra my_tbl: "+ my_tableId);
//
//
//        intent1 = new Intent(SubMenuActivity.this, BasketActivity.class);
//        intent1.putExtra("tableId",my_tableId);
//        Log.e("tableId", "SubMenuActivity putExtra my_tbl to basket: "+ my_tableId);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.app_bar_sub_menu);
        toolbar.setTitle(categoryName);

        presenter.displayMeals(id);

        adapter = new SubMenuAdapter(new SubMenuContract.OnItemClickListener() {
            @Override
            public void onItemClick(SubMenu subMenu) {

            }
        });


        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


    }


    @Override
    public void getListOfMeals(List<SubMenu> body) {
        adapter.setValues(body);
    }

    @Override
    public void showError() {
        Toast.makeText(this, getString(R.string.tables_error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 100) {
            Intent intent = getIntent();
            long id = intent.getLongExtra("categoryId",2);
            presenter.displayMeals(id);
        }
    }
}
