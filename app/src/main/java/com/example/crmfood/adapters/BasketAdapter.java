package com.example.crmfood.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crmfood.R;
import com.example.crmfood.basket.BasketContract;
import com.example.crmfood.models.Basket;

import java.util.ArrayList;
import java.util.List;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.BasketViewHolder> {

    private List<Basket> basketList;
    private BasketContract.OnItemClickListener onItemClickListener;

    public BasketAdapter(BasketContract.OnItemClickListener onItemClickListener) {
        basketList = new ArrayList<>();
        this.onItemClickListener = onItemClickListener;
    }

    public void setValues(List<Basket> values) {
        basketList.clear();
        if (values != null) {
            basketList.addAll(values);
        }
        this.notifyDataSetChanged();
    }


    @NonNull
    @Override
    public BasketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.basket_item,parent,false);
        return new BasketViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull BasketViewHolder holder, int position) {
        holder.bind(basketList.get(position),onItemClickListener);

    }


    @Override
    public int getItemCount() {
        return basketList.size();
    }

    class BasketViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView price;
        CardView increase;
        CardView decrease;
        TextView counter;
        Button delete_from_basket;

        int number;

        BasketViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.poduct_name_basket);
            price = itemView.findViewById(R.id.item_price_basket);
            increase = itemView.findViewById(R.id.increase_menu_basket);
            counter = itemView.findViewById(R.id.counter_menu_basket);
            decrease = itemView.findViewById(R.id.decrease_menu_basket);
            delete_from_basket = itemView.findViewById(R.id.delete_button_basket);

            number = 0;

        }

        void bind(final Basket basket, final BasketContract.OnItemClickListener onItemClickListener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(basket);
                }
            });


            increase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    number++;
                    counter.setText(String.valueOf(number));

                }
            });

            decrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (number>0) {
                        number--;
                    }else {
                        number = 0;
                    }
                    counter.setText(String.valueOf(number));
                    if (number == 0) {

                    }
                }
            });


            delete_from_basket.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO: implement deleting from db
                }
            });
        }
    }
}
