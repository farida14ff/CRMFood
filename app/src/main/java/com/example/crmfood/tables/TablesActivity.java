package com.example.crmfood.tables;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crmfood.R;
import com.example.crmfood.adapters.TablesAdapter;
import com.example.crmfood.kitchenMenu.KitchenMenuActivity;
import com.example.crmfood.models.Table;

import java.util.List;

public class TablesActivity extends AppCompatActivity implements TablesContract.View{

    private TablesContract.Presenter presenter;
    private TablesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);

        LinearLayout goBackIM = findViewById(R.id.go_back_icon);
        goBackIM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        initRecyclerViewWithAdapter();
    }

    @Override
    public void setTables(List<Table> tables) {
        adapter.setValues(tables);
    }

    private void initRecyclerViewWithAdapter() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));

        adapter = new TablesAdapter(new TablesContract.OnItemClickListener(){
            @Override
            public void onItemClick(Table table) {
                showMenu(table);
            }
        });

        presenter = new TablesPresenter(this);
        presenter.displayTables();

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showMenu(Table table) {
        Intent intent = new Intent(this, KitchenMenuActivity.class);
        startActivityForResult(intent, 100);
    }

    @Override
    public void showError() {
        Toast.makeText(this, getString(R.string.tables_error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 100) {
            presenter.displayTables();
        }
    }




}
