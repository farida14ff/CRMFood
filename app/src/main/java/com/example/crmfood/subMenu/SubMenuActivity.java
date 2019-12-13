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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.crmfood.R;
import com.example.crmfood.adapters.SubMenuAdapter;
import com.example.crmfood.menu.barMenu.BarMenuFragment;
import com.example.crmfood.basket.BasketActivity;
import com.example.crmfood.models.SubMenu;

import java.util.List;

public class SubMenuActivity extends AppCompatActivity implements SubMenuContract.View {

    private SubMenuContract.Presenter presenter;
    private SubMenuAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_menu);


        initRecyclerView();
        initViews();

    }

    private void initViews() {

        LinearLayout goBackIM = findViewById(R.id.go_back_icon);
        goBackIM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        final LinearLayout basket_LL = findViewById(R.id.basket_sub);
        basket_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubMenuActivity.this, BasketActivity.class);
                startActivity(intent);
                basket_LL.setEnabled(false);
            }
        });




        final Button next_btn = findViewById(R.id.next_button);
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubMenuActivity.this, BasketActivity.class);
                startActivity(intent);
                next_btn.setEnabled(false);
            }
        });

//        next_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(SubMenuActivity.this, BasketActivity.class);
//                startActivity(intent);
//
//                next_btn.setVisibility(View.VISIBLE);
//                go_to_the_basket_btn.setVisibility(View.GONE);
//            }
//        });
    }


    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.sub_menu_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        presenter = new SubMenuPresenter(this);

        Intent intent = getIntent();
        String categoryName = intent.getStringExtra("category");
        long id = intent.getLongExtra("categoryId",2);


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
