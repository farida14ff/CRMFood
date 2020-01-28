package com.example.crmfood.main;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.crmfood.BaseActivity;
import com.example.crmfood.R;
import com.example.crmfood.adapters.ActiveOrdersAdapter;
import com.example.crmfood.models.ActiveOrder;
import com.example.crmfood.models.ListMealInActiveOrder;
import com.example.crmfood.tables.TablesActivity;


import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment implements MainContract.View {

    private MainContract.Presenter presenter;
    private ActiveOrdersAdapter adapter;
    private List<ActiveOrder> acitiveOrderArray;
    ActiveOrder activeOrder = new ActiveOrder();
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayout emptyView;
    ProgressBar progressBar;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.activity_main, container, false);

        progressBar = root.findViewById(R.id.progress_bar);
        emptyView = root.findViewById(R.id.empty_view);
        setHasOptionsMenu(true);
        initRecyclerViewWithAdapter(root);
        initViews(root);
        initSwipeRefreshLayout(root);
        setData();
        return root;
    }

    private void initViews(View root) {
        final ImageView createNewOrderButton = root.findViewById(R.id.create_new_order_button);
        createNewOrderButton.setOnClickListener(e ->{
                Intent intent = new Intent(getActivity(), TablesActivity.class);
                startActivity(intent);
                //createNewOrderButton.setEnabled(false);

        });

        LinearLayout logutLL = root.findViewById(R.id.logout);
        logutLL.setOnClickListener(e ->{
                showConfirmLogoutDialog();

        });

    }

    private void initSwipeRefreshLayout(View root) {
        swipeRefreshLayout = root.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.rippleColor), getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout.setOnRefreshListener(() -> presenter.displayListOfActiveOrders());
    }

    @Override
    public void setListOfActiveOrders(List<ActiveOrder> body) {
        adapter.setValues(body);
    }

    private void initRecyclerViewWithAdapter(View root) {
        recyclerView = root.findViewById(R.id.list_of_active_orders_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (adapter == null) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            adapter = new ActiveOrdersAdapter(getActivity(), acitiveOrderArray, new MainContract.OnItemClickListener() {
                @Override
                public void onItemClick(ActiveOrder activeOrder) {
                }
            });
            presenter = new MainPresenter(this);
            presenter.displayListOfActiveOrders();
        }

        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

    }


    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        Toast.makeText(getActivity(), getString(R.string.orders_error), Toast.LENGTH_LONG).show();
        swipeRefreshLayout.setRefreshing(false);
        adapter.setValues(null);
        progressBar.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void stopRefreshingOrders() {
        swipeRefreshLayout.setRefreshing(false);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 100) {
            presenter.displayListOfActiveOrders();
        }
    }

    private void setData(){
        List<ListMealInActiveOrder> mealsList = new ArrayList<>();
        ListMealInActiveOrder listMealInActiveOrder = new ListMealInActiveOrder();
        ActiveOrder activeOrder = new ActiveOrder();
        activeOrder.setArrayList(acitiveOrderArray);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void showConfirmLogoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AlertDialogTheme);
        builder.setTitle(getString(R.string.logout_title));
        builder.setMessage(getString(R.string.logout_message));
        builder.setNegativeButton(R.string.cancel_order, null);
        builder.setPositiveButton(getString(R.string.action_logout), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                BaseActivity.authToken = null;
                SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("authToken").apply();
                new Handler().post(new Runnable() {

                    @Override
                    public void run()
                    {
                        Intent intent = getActivity().getIntent();
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        getActivity().overridePendingTransition(0, 0);
                        getActivity().finish();

                        getActivity().overridePendingTransition(0, 0);
                        startActivity(intent);
                    }
                });


            }

        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void showEmptyView() {
        progressBar.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        inflater.inflate(R.menu.menu_main, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_logout:
//                showConfirmLogoutDialog();
//                //getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
//                return true;
//        }
//        return false;
//    }

    @Override
    public boolean isConnected() {
        try {
            BaseActivity baseActivity = new BaseActivity();
            ConnectivityManager cm =
                    (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
//                (ConnectivityManager) baseActivity.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            assert cm != null;
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
//        return netInfo != null && netInfo.isConnectedOrConnecting();
            return (netInfo == null) ? false : true;
        }catch (NullPointerException e){
            return false;
        }



    }


}
