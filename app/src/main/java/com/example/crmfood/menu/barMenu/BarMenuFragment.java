package com.example.crmfood.menu.barMenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crmfood.R;
import com.example.crmfood.adapters.BarAdapter;
import com.example.crmfood.models.MenuBar;
import com.example.crmfood.subMenu.SubMenuActivity;

import java.util.List;

public class BarMenuFragment extends Fragment implements BarMenuContract.View {

    private BarMenuContract.Presenter presenter;
    private BarAdapter adapterBar;
    private LinearLayout emptyView;
    private RecyclerView recyclerView;
    ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_bar_menu, container, false);

        progressBar = root.findViewById(R.id.progressBar_bar);
        emptyView = root.findViewById(R.id.empty_view_bar);

        initRecyclerViewWithAdapter(root);
        return root;
    }


    private void initRecyclerViewWithAdapter(View root) {
        recyclerView = root.findViewById(R.id.bar_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        if (adapterBar == null) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            adapterBar = new BarAdapter(this::showPodMenu);
            presenter = new BarMenuPresenter(this);
            presenter.displayBarCategoy();
        }

        recyclerView.setAdapter(adapterBar);

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
        Toast.makeText(getActivity(), getString(R.string.categories_error), Toast.LENGTH_LONG).show();
        adapterBar.setValues(null);
        progressBar.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 100) {
            presenter.displayBarCategoy();
        }
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyView() {
        progressBar.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }
}
