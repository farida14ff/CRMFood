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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crmfood.BaseActivity;
import com.example.crmfood.R;
import com.example.crmfood.adapters.ActiveOrdersAdapter;
//import com.example.crmfood.login.LoginActivity;
import com.example.crmfood.menu.MainMenuActivity;
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



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.activity_main, container, false);

        initRecyclerViewWithAdapter(root);
        initViews(root);
        setData();


        return root;
    }

    private void initViews(View root) {
        final ImageView createNewOrderButton = root.findViewById(R.id.create_new_order_button);
        createNewOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TablesActivity.class);
                startActivity(intent);
                //createNewOrderButton.setEnabled(false);

            }
        });

        LinearLayout logutLL = root.findViewById(R.id.logout);
        logutLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConfirmLogoutDialog();
            }
        });


//        Button addToOrderButton = findViewById(R.id.add_button);
//        addToOrderButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainFragment.this, MainMenuActivity.class);
//                startActivity(intent);
//                finish();
//
//                //TODO: add login for adding order
//            }
//        });
//
//        Button closeChequeButton = findViewById(R.id.close_account_button);
//        closeChequeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                presenter.closeCheque(activeOrder.getId());
//            }
//        });


    }

    @Override
    public void setListOfActiveOrders(List<ActiveOrder> body) {
        adapter.setValues(body);
    }

    private void initRecyclerViewWithAdapter(View root) {
        RecyclerView recyclerView = root.findViewById(R.id.list_of_active_orders_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        adapter = new ActiveOrdersAdapter(getActivity(), acitiveOrderArray, new MainContract.OnItemClickListener() {
            @Override
            public void onItemClick(ActiveOrder activeOrder) {
            }
        });



//        adapter = new ActiveOrdersAdapter(new MainContract.OnItemClickListener() {
//            int a = View.GONE;
//            @Override
//            public void onItemClick(ActiveOrder activeOrder) {
////                if (a == View.VISIBLE) {
////
////                    recyclerView.setVisibility(a);
////                    a = View.GONE;
////                }else {
////                    recyclerView.setVisibility(a);
////                    a = View.VISIBLE;
////                }
//            }
//
//        });

        presenter = new MainPresenter(this);
        presenter.displayListOfActiveOrders();

        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

    }

    @Override
    public void showError() {
        Toast.makeText(getActivity(), getString(R.string.orders_error), Toast.LENGTH_LONG).show();

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
