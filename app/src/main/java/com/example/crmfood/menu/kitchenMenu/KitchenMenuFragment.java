package com.example.crmfood.menu.kitchenMenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crmfood.R;
import com.example.crmfood.adapters.MenuAdapter;
import com.example.crmfood.basket.BasketActivity;
import com.example.crmfood.models.MenuKitchen;
import com.example.crmfood.subMenu.SubMenuActivity;

import java.util.List;

public class KitchenMenuFragment extends Fragment implements KitchenMenuContract.View {

    private KitchenMenuContract.Presenter presenter;
    private MenuAdapter adapterMenu;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.activity_kitchen_menu, container, false);




        initRecyclerViewWithAdapter(root);

        return root;
    }



    private void initRecyclerViewWithAdapter(View root) {
        RecyclerView recyclerView = root.findViewById(R.id.menu_catigories_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        adapterMenu = new MenuAdapter(new KitchenMenuContract.OnItemClickListener(){
            @Override
            public void onItemClick(MenuKitchen menuKitchen) {
                showPodMenu(menuKitchen);
            }

        });

////        if (menu.getDepartmentName().equals("Kitchen")) {
//        if (adapterMenu == null) {
//            recyclerView.setVisibility(View.GONE);
////            emptyView.setVisibility(View.GONE);
////            progressBar.setVisibility(View.VISIBLE);
//            adapterMenu = new MenuAdapter(new KitchenMenuContract.OnItemClickListener() {
//                @Override
//                public void onItemClick(MenuKitchen menu) {
//
//                }
//
//            });
//        }

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
        Intent intent = new Intent(getActivity(), SubMenuActivity.class);
        intent.putExtra("categoryId",menuKitchen.getCategoryId());
        intent.putExtra("category",menuKitchen.getCategoryName());
        startActivity(intent);
    }

    @Override
    public void showError() {
        Toast.makeText(getActivity(), getString(R.string.tables_error), Toast.LENGTH_LONG).show();
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 100) {
            presenter.displayMenuCategoy();
        }
    }
}
