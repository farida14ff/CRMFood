package com.example.crmfood.tables;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crmfood.R;
import com.example.crmfood.adapters.TablesAdapter;
import com.example.crmfood.basket.BasketActivity;
import com.example.crmfood.menu.MainMenuActivity;
import com.example.crmfood.menu.kitchenMenu.KitchenMenuFragment;
import com.example.crmfood.models.Table;

import java.util.List;

public class TablesActivity extends AppCompatActivity implements TablesContract.View{

    private TablesContract.Presenter presenter;
    private TablesAdapter adapter;
    private LinearLayout emptyView;
    private RecyclerView recyclerView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);

        progressBar = findViewById(R.id.table_progress_bar);
        emptyView = findViewById(R.id.empty_view_table);

        LinearLayout goBackIM = findViewById(R.id.go_back_icon);
        goBackIM.setOnClickListener(e -> finish());

        initRecyclerViewWithAdapter();

    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
    }


    @Override
    public void setTables(List<Table> tables) {
        adapter.setValues(tables);
    }

    private void initRecyclerViewWithAdapter() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));

        if (adapter == null) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            adapter = new TablesAdapter(table -> {
                showMenu(table);
                Log.e("tableId TABLES: ", String.valueOf(table.getId()));
            });
            presenter = new TablesPresenter(this);
            presenter.displayTables();
        }

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showMenu(Table table) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        intent.putExtra("tableId",table.getId());
        Log.e("tableId", "TablesActivity putExtra: "+ table.getId());
        startActivityForResult(intent, 100);

    }

    @Override
    public void showError() {
        Toast.makeText(this, getString(R.string.tables_error), Toast.LENGTH_LONG).show();
        adapter.setValues(null);
        progressBar.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 100) {
            presenter.displayTables();
        }
    }


    @Override
    public void showEmptyView() {
        progressBar.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }



}
