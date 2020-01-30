package com.example.crmfood.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crmfood.R;
import com.example.crmfood.basket.BasketActivity;
import com.example.crmfood.basket.BasketContract;

import java.util.ArrayList;
import java.util.List;

import com.example.crmfood.data.ToBasketRoomDatabase;
import com.example.crmfood.models.Basket;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.BasketViewHolder> {

    private List<Basket> basketList;
    private BasketContract.OnItemClickListener onItemClickListener;
    private BasketActivity basketActivity = new BasketActivity();

    public BasketAdapter(BasketContract.OnItemClickListener onItemClickListener) {
        basketList = new ArrayList<>();
        this.onItemClickListener = onItemClickListener;
    }

    public BasketAdapter(List<Basket> basketList, BasketContract.OnItemClickListener onItemClickListener) {
        this.basketList = basketList;
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
        ToBasketRoomDatabase db;
        public Basket basket;

        int number;
        double inPrice;
        double totPrice;

        BasketViewHolder(@NonNull View itemView) {
            super(itemView);
            db = ToBasketRoomDatabase.getDatabase(itemView.getContext());

            name = itemView.findViewById(R.id.poduct_name_basket);
            price = itemView.findViewById(R.id.item_price_basket);
            increase = itemView.findViewById(R.id.increase_menu_basket);
            counter = itemView.findViewById(R.id.counter_menu_basket);
            decrease = itemView.findViewById(R.id.decrease_menu_basket);
            delete_from_basket = itemView.findViewById(R.id.delete_button_basket);

            inPrice = 0;
            totPrice = 0;

        }

        void bind(final Basket basket2, final BasketContract.OnItemClickListener onItemClickListener) {

            itemView.setOnClickListener(e -> onItemClickListener.onItemClick(basket2));


            basket = basket2;

            name.setText(basket2.getBasket_name());
            inPrice = basket2.getBasket_price();
            totPrice = inPrice * basket.getOrderedQuantity();
            price.setText(String.valueOf(totPrice));
            Log.i("Initial Price", "BasketAdapter " + inPrice);
            counter.setText(String.valueOf(basket2.getOrderedQuantity()));


            increase.setOnClickListener(e -> {
                basket.increase();
                totPrice = inPrice * basket.getOrderedQuantity();
                price.setText(String.valueOf(totPrice));
                counter.setText(String.valueOf(basket.getOrderedQuantity()));
                basketActivity.saveBasket(basket);

            });


            decrease.setOnClickListener(e -> {

                number = basket.getOrderedQuantity();
                basket.decrease();
                counter.setText(String.valueOf(basket.getOrderedQuantity()));
                Log.e("SubMenuAdepter", "bsaket quantity" + basket.getOrderedQuantity());

                if (basket.getOrderedQuantity() > 0) {
                    Log.e("SubMenuAdepter", "number>0");
                    totPrice = inPrice * basket.getOrderedQuantity();
                    price.setText(String.valueOf(totPrice));
                    basketActivity.saveBasket(basket);
                } else {
                    Log.e("SubMenuAdepter", "fromBasketView");
                    basketActivity.deleteFromBasket(basket);
                    price.setText(String.valueOf(inPrice));
                }

            });


            delete_from_basket.setOnClickListener(e -> {
                basketActivity.deleteFromBasket(basket);
                removeAt(getAdapterPosition());
                Log.e("getSub_id", "onClick: " + basket.getMealId());
            });
        }

        public void removeAt(int position) {
            basketList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, basketList.size());

        }
    }
}
