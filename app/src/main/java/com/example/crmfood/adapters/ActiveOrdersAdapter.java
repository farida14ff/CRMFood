package com.example.crmfood.adapters;

import android.annotation.SuppressLint;
import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crmfood.R;
import com.example.crmfood.main.MainContract;
import com.example.crmfood.models.ActiveOrder;
import com.example.crmfood.models.ListMealInActiveOrder;

import java.util.ArrayList;
import java.util.List;

public class ActiveOrdersAdapter extends RecyclerView.Adapter<ActiveOrdersAdapter.AciveOdresViewHolder> {

    private Context context;
    private List<ActiveOrder> activeOrderList;
    private MainContract.OnItemClickListener clickListener;


    public ActiveOrdersAdapter(Context context, List<ActiveOrder> activeOrderList, MainContract.OnItemClickListener clickListener) {
        this.context = context;
        this.activeOrderList =  new ArrayList<>();
        this.clickListener = clickListener;
    }


    public void setValues(List<ActiveOrder> values) {
        activeOrderList.clear();
        if (values != null) {
            activeOrderList.addAll(values);
        }
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AciveOdresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.active_arders_list_item, parent,false);
        return new AciveOdresViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AciveOdresViewHolder holder, int position) {
        holder.bind(activeOrderList.get(position),clickListener);

    }

    @Override
    public int getItemCount() {
        return activeOrderList.size();
    }

     class AciveOdresViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView2;
        List<ListMealInActiveOrder> listMealInActiveOrders;
        TextView ordersNumTextView;
        Button addBtn;
        Button closeAccountBtn;
        Button deleteBtn;
        TextView ordersStatus;


         AciveOdresViewHolder(@NonNull View itemView) {
            super(itemView);

            listMealInActiveOrders = new ArrayList<>();
            recyclerView2 = itemView.findViewById(R.id.list_of_order_items_rv);
            ordersNumTextView = itemView.findViewById(R.id.orders_number_textView);
            closeAccountBtn = itemView.findViewById(R.id.close_account_button);
            deleteBtn = itemView.findViewById(R.id.delete_button);
            addBtn = itemView.findViewById(R.id.add_button);
            ordersStatus = itemView.findViewById(R.id.orders_status_text);

        }


        @SuppressLint({"SetTextI18n", "ResourceAsColor"})
         void bind(final ActiveOrder activeOrder, final MainContract.OnItemClickListener onItemClickListener) {


             ordersNumTextView.setOnClickListener(new View.OnClickListener() {
                 int a = View.GONE;
                 @Override
                 public void onClick(View view) {
                     if (a == View.VISIBLE) {
                         recyclerView2.setVisibility(a);
                         addBtn.setVisibility(View.VISIBLE);
                         closeAccountBtn.setVisibility(View.VISIBLE);
                         deleteBtn.setVisibility(View.VISIBLE);

                         a = View.GONE;
                     }else {
                         recyclerView2.setVisibility(a);
                         addBtn.setVisibility(View.GONE);
                         closeAccountBtn.setVisibility(View.GONE);
                         deleteBtn.setVisibility(View.GONE);
                         a = View.VISIBLE;
                     }
                 }
             });
            itemView.setOnClickListener(new View.OnClickListener() {
                int a = View.GONE;
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(activeOrder);
                    if (a == View.VISIBLE) {
                        recyclerView2.setVisibility(a);
                        addBtn.setVisibility(View.VISIBLE);
                        closeAccountBtn.setVisibility(View.VISIBLE);
                        deleteBtn.setVisibility(View.VISIBLE);

                        a = View.GONE;
                    }else {
                        recyclerView2.setVisibility(a);
                        addBtn.setVisibility(View.GONE);
                        closeAccountBtn.setVisibility(View.GONE);
                        deleteBtn.setVisibility(View.GONE);
                        a = View.VISIBLE;
                    }

                }
            });

            List<ListMealInActiveOrder> listMealInActiveOrders = activeOrder.getListMealInActiveOrders();
            ListMealInActiveOrderAdapter listMealInActiveOrderAdapter = new ListMealInActiveOrderAdapter
                    (context,listMealInActiveOrders);

            recyclerView2.setHasFixedSize(true);
            recyclerView2.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false));
            recyclerView2.setAdapter(listMealInActiveOrderAdapter);


//            switch (activeOrder.getMainStatus()){
//                case "NotReady":
//                    mainStatus.setText("В ПРОЦЕССЕ");
//                    mainStatus.setTextColor(Color.parseColor("#928C8C"));
//                    break;
//                case "Ready":
//                    mainStatus.setText(R.string.redy_t);
//                    mainStatus.setTextColor(Color.parseColor("#04932C"));
//                    break;
//                case "Freezed":
//                    mainStatus.setText("В ПРОЦЕССЕ");
//                    mainStatus.setTextColor(Color.parseColor("#928C8C"));
//                    break;
//
//            }

            ordersNumTextView.setText("Заказ #"+activeOrder.getId());

            Log.e("tableID",String.valueOf(activeOrder.getId()));
        }
    }
}
