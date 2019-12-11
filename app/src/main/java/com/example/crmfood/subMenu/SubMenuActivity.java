package com.example.crmfood.subMenu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.crmfood.R;
import com.example.crmfood.adapters.MenuAdapter;
import com.example.crmfood.adapters.SubMenuAdapter;
import com.example.crmfood.kitchenMenu.KitchenMenuContract;
import com.example.crmfood.kitchenMenu.KitchenMenuPresenter;
import com.example.crmfood.models.MenuKitchen;
import com.example.crmfood.models.SubMenu;

import java.util.List;

public class SubMenuActivity extends AppCompatActivity implements SubMenuContract.View {

    private SubMenuContract.Presenter presenter;
    private SubMenuAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_menu);

        LinearLayout goBackIM = findViewById(R.id.go_back_icon);
        goBackIM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.sub_menu_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        presenter = new SubMenuPresenter(this);

        Intent intent = getIntent();

        long id = intent.getLongExtra("categoryId",1);
        String categoryName = intent.getStringExtra("category");

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
            presenter.displayMeals(1);
        }
    }
}
