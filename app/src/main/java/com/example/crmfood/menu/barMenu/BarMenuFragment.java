package com.example.crmfood.menu.barMenu;

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
import com.example.crmfood.adapters.BarAdapter;
import com.example.crmfood.basket.BasketActivity;
import com.example.crmfood.models.MenuBar;
import com.example.crmfood.subMenu.SubMenuActivity;

import java.util.List;

public class BarMenuFragment extends Fragment implements BarMenuContract.View {

    private BarMenuContract.Presenter presenter;
    private BarAdapter adapterBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.activity_bar_menu, container, false);

        initRecyclerViewWithAdapter(root);
        return root;
    }


    private void initRecyclerViewWithAdapter(View root) {
        RecyclerView recyclerView = root.findViewById(R.id.bar_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        adapterBar = new BarAdapter(new BarMenuContract.OnItemClickListener() {
            @Override
            public void onItemClick(MenuBar menuBar) {
                showPodMenu(menuBar);
            }

        });

////        if (menu.getDepartmentName().equals("Kitchen")) {
//        if (adapterBar == null) {
//            recyclerView.setVisibility(View.GONE);
//////            emptyView.setVisibility(View.GONE);
//////            progressBar.setVisibility(View.VISIBLE);
//            adapterBar = new BarAdapter(new BarMenuContract.OnItemClickListener() {
//                @Override
//                public void onItemClick(MenuBar menu) {
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

    public void showPodMenu(MenuBar menuBar) {
        Intent intent = new Intent(getActivity(), SubMenuActivity.class);
        intent.putExtra("categoryId", menuBar.getCategoryId());
        intent.putExtra("category", menuBar.getCategoryName());
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
            presenter.displayBarCategoy();
        }
    }
}
