package com.example.crmfood.kitchenMenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crmfood.R;
import com.example.crmfood.adapters.MenuAdapter;
import com.example.crmfood.models.MenuKitchen;
import com.example.crmfood.subMenu.SubMenuActivity;

import java.util.List;

public class KitchenMenuActivity extends AppCompatActivity implements KitchenMenuContract.View {

    private KitchenMenuContract.Presenter presenter;
    private MenuAdapter adapterMenu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen_menu);

        LinearLayout goBackIM = findViewById(R.id.go_back_icon);
        goBackIM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        initRecyclerViewWithAdapter();
    }

    private void initRecyclerViewWithAdapter() {
        RecyclerView recyclerView = findViewById(R.id.menu_catigories_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        adapterMenu = new MenuAdapter(new KitchenMenuContract.OnItemClickListener(){
            @Override
            public void onItemClick(MenuKitchen menuKitchen) {
                showPodMenu(menuKitchen);
            }

        });

//        if (menu.getDepartmentName().equals("Kitchen")) {
        if (adapterMenu == null) {
            recyclerView.setVisibility(View.GONE);
//            emptyView.setVisibility(View.GONE);
//            progressBar.setVisibility(View.VISIBLE);
            adapterMenu = new MenuAdapter(new KitchenMenuContract.OnItemClickListener() {
                @Override
                public void onItemClick(MenuKitchen menu) {

                }

            });
        }

        presenter = new KitchenMenuPresenter(this);
        presenter.displayMenuCategoy();
        recyclerView.setAdapter(adapterMenu);
//        }else {
//            recyclerView.setAdapter(adapterMenu);
//        }
    }

    @Override
    public void getMenuCategoy(List<MenuKitchen> body) {
        adapterMenu.setValues(body);
    }

    public void showPodMenu(MenuKitchen menuKitchen){
        Intent intent = new Intent(this, SubMenuActivity.class);
        intent.putExtra("categoryId",menuKitchen.getCategoryId());
        intent.putExtra("category",menuKitchen.getCategoryName());
        startActivity(intent);
    }

    @Override
    public void showError() {
        Toast.makeText(this, getString(R.string.tables_error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 100) {
            presenter.displayMenuCategoy();
        }
    }
}
