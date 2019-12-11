package com.example.crmfood.barMenu;

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
import com.example.crmfood.adapters.BarAdapter;
import com.example.crmfood.adapters.MenuAdapter;
import com.example.crmfood.barMenu.BarMenuContract;
import com.example.crmfood.barMenu.BarMenuPresenter;
import com.example.crmfood.models.MenuBar;
import com.example.crmfood.models.MenuKitchen;
import com.example.crmfood.models.SubMenu;
import com.example.crmfood.subMenu.SubMenuActivity;

import java.util.List;

public class BarMenuActivity extends AppCompatActivity implements BarMenuContract.View {

    private BarMenuContract.Presenter presenter;
    private BarAdapter adapterBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_menu);

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
        RecyclerView recyclerView = findViewById(R.id.bar_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        adapterBar = new BarAdapter(new BarMenuContract.OnItemClickListener(){
            @Override
            public void onItemClick(MenuBar menuBar) {
                showPodMenu(menuBar);
            }

        });

//        if (menu.getDepartmentName().equals("Kitchen")) {
//        if (adapterBar == null) {
//            recyclerView.setVisibility(View.GONE);
////            emptyView.setVisibility(View.GONE);
////            progressBar.setVisibility(View.VISIBLE);
//            adapterBar = new MenuAdapter(new KitchenMenuContract.OnItemClickListener() {
//                @Override
//                public void onItemClick(MenuKitchen menu) {
//
//                }
//
//            });
//        }

        presenter = new BarMenuPresenter(this);
        presenter.displayBarCategoy();
        recyclerView.setAdapter(adapterBar);
//        }else {
//            recyclerView.setAdapter(adapterBar);
//        }
    }

    @Override
    public void getBarCategoy(List<MenuBar> body) {
        adapterBar.setValues(body);
    }

    public void showPodMenu(MenuBar menuBar){
        Intent intent = new Intent(this, SubMenuActivity.class);
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
            presenter.displayBarCategoy();
        }
    }
}
