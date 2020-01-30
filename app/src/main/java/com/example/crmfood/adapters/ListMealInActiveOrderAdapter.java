package com.example.crmfood.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crmfood.R;
import com.example.crmfood.main.MainContract;
import com.example.crmfood.models.ListMealInActiveOrder;

import java.util.List;

public class ListMealInActiveOrderAdapter extends RecyclerView.Adapter<ListMealInActiveOrderAdapter.ListMealInActiveOrderViewHolder> {

    private Context context;
    private List<ListMealInActiveOrder> listMealInActiveOrdersList;
    private MainContract.OnItemClickListener clickListener;


    public ListMealInActiveOrderAdapter(Context context, List<ListMealInActiveOrder> listMealInActiveOrders) {
        this.context = context;
        this.listMealInActiveOrdersList  = listMealInActiveOrders;


    }

    @NonNull
    @Override
    public ListMealInActiveOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.oreders_items_item, parent,false);
        return new ListMealInActiveOrderViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ListMealInActiveOrderViewHolder holder, int position) {
        ListMealInActiveOrder listMealInActiveOrder = listMealInActiveOrdersList.get(position);
        holder.bind(listMealInActiveOrdersList.get(position),clickListener);
    }

    @Override
    public int getItemCount() {
        return listMealInActiveOrdersList.size();
    }

     class ListMealInActiveOrderViewHolder extends RecyclerView.ViewHolder {

         TextView ordersName;
         ImageView ordersStatusIcon;
         TextView ordersStatus;
         RecyclerView recyclerView2;
         TextView ordersQuantity;


         ListMealInActiveOrderViewHolder(@NonNull View itemView) {
             super(itemView);

             ordersName = itemView.findViewById(R.id.ordes_name);
             ordersStatusIcon = itemView.findViewById(R.id.orders_status_icon);
             ordersStatus = itemView.findViewById(R.id.orders_status_text);
             recyclerView2 = itemView.findViewById(R.id.list_of_order_items_rv);
             ordersQuantity = itemView.findViewById(R.id.orders_quantity_active);

         }

          @SuppressLint("ResourceAsColor")
          void bind(ListMealInActiveOrder listMealInActiveOrder, MainContract.OnItemClickListener clickListener) {



             switch (listMealInActiveOrder.getStatus()){
                 case "NotReady":
                     ordersStatus.setVisibility(View.GONE);
                     ordersStatusIcon.setImageResource(R.drawable.no_ready_ic);
                     break;
                 case "Ready":
                     ordersStatus.setVisibility(View.GONE);
                     ordersStatusIcon.setImageResource(R.drawable.redy_ic);
                     break;
                 case "Freezed":
                     ordersStatus.setText(R.string.freezed);
                     ordersStatus.setTextColor(Color.parseColor("#2D4DCE"));
                     ordersName.setTextColor(Color.parseColor("#2D4DCE"));
                     ordersStatusIcon.setImageResource(R.drawable.freezed_ic);
                     break;

             }
              ordersName.setText(listMealInActiveOrder.getMeal());
              ordersQuantity.setText("x"+listMealInActiveOrder.getOrderedQuantity());




          }
     }


}
