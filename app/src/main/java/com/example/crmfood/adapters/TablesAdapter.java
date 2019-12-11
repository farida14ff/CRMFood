package com.example.crmfood.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crmfood.R;
import com.example.crmfood.models.Table;
import com.example.crmfood.tables.TablesContract;

import java.util.ArrayList;
import java.util.List;


public class TablesAdapter extends RecyclerView.Adapter<TablesAdapter.TablesViewHolder> {

    private List<Table> tablesList;
    private TablesContract.OnItemClickListener clickListener;

    public TablesAdapter(TablesContract.OnItemClickListener clickListener) {
        tablesList = new ArrayList<>();
        this.clickListener = clickListener;
    }

    public void setValues(List<Table> values) {
        tablesList.clear();
        if (values != null) {
            tablesList.addAll(values);
        }
        this.notifyDataSetChanged();
    }


    @NonNull
    @Override
    public TablesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.card_view_item_tables,parent,false);
        return new TablesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TablesViewHolder holder, int position) {
        holder.bind(tablesList.get(position), clickListener);

    }


    @Override
    public int getItemCount() {
        return tablesList.size();
    }


    class TablesViewHolder extends RecyclerView.ViewHolder {

        TextView tableId;
        CardView cardView;

        TablesViewHolder(@NonNull View itemView) {
            super(itemView);
            tableId = itemView.findViewById(R.id.tables_id);
            cardView = itemView.findViewById(R.id.card_view);
        }

        @SuppressLint("LongLogTag")
        void bind(final Table table, final TablesContract.OnItemClickListener onItemClickListener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(table);
                }
            });

            tableId.setText(String.valueOf(table.getId()));
            int colorStatus = table.getStatus();
            Log.e("colorStatus",String.valueOf(colorStatus));
            Log.e("tableID",String.valueOf(table.getId()));
            switch (colorStatus) {
                case 0:
                    cardView.setCardBackgroundColor(Color.parseColor("#2D4DCE"));
                    break;
                case 1:
                    cardView.setCardBackgroundColor(Color.parseColor("#04932C"));
                    break;
                case 2:
                    cardView.setCardBackgroundColor(Color.parseColor("#B80505"));
                    break;
            }
        }
    }
}
