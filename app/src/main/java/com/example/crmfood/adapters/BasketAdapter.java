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
import com.example.crmfood.basket.BasketContract;

import java.util.ArrayList;
import java.util.List;

import com.example.crmfood.data.ToBasketRoomDatabase;
import com.example.crmfood.models.Basket2;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.BasketViewHolder> {

    private List<Basket2> basketList;
    private BasketContract.OnItemClickListener onItemClickListener;

    public BasketAdapter(BasketContract.OnItemClickListener onItemClickListener) {
        basketList = new ArrayList<>();
        this.onItemClickListener = onItemClickListener;
    }

    public BasketAdapter(List<Basket2> basketList,BasketContract.OnItemClickListener onItemClickListener) {
        this.basketList = basketList;
        this.onItemClickListener = onItemClickListener;
    }

    public void setValues(List<Basket2> values) {
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

        int number;
        double inPrice;
        double totPrice;

        BasketViewHolder(@NonNull View itemView) {
            super(itemView);
            db  = ToBasketRoomDatabase.getDatabase(itemView.getContext());

            name = itemView.findViewById(R.id.poduct_name_basket);
            price = itemView.findViewById(R.id.item_price_basket);
            increase = itemView.findViewById(R.id.increase_menu_basket);
            counter = itemView.findViewById(R.id.counter_menu_basket);
            decrease = itemView.findViewById(R.id.decrease_menu_basket);
            delete_from_basket = itemView.findViewById(R.id.delete_button_basket);

            number = 1;
            inPrice = 0;
            totPrice = 0;

        }

        void bind(final Basket2 basket, final BasketContract.OnItemClickListener onItemClickListener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(basket);
                }
            });


            name.setText(basket.getBasket_name());
            inPrice = basket.getBasket_price();
            price.setText(String.valueOf(inPrice));
            counter.setText(String.valueOf(number));


            increase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    number++;
                    totPrice = inPrice *number;
                    price.setText(String.valueOf(totPrice));
                    counter.setText(String.valueOf(number));
//                    Basket2 basketObj = new Basket2(basket.getMealId(), name.getText().toString(), Double.parseDouble(price.getText().toString()), Integer.parseInt(counter.getText().toString()));
//
//                    db.toBasketDao().addItems(basketObj);
//                    basket.setVisibility(View.GONE);
//                    basket2.setVisibility(View.VISIBLE);
                }
            });

            decrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (number>1) {
                        number--;
                        totPrice = totPrice-inPrice ;
                        if (totPrice < inPrice){
                            price.setText(String.valueOf(inPrice));
                            number = 0;

                        }else {
                            price.setText(String.valueOf(totPrice));
                        }
                        if (number == 0) {
//                            basket2.setVisibility(View.GONE);
//                            basket.setVisibility(View.VISIBLE);
                        }
                        counter.setText(String.valueOf(number));
                    } else {
                        number = 0;
                        counter.setText(String.valueOf(number));
//                        basket2.setVisibility(View.GONE);
//                        basket.setVisibility(View.VISIBLE);

                    }

                }
            });


            delete_from_basket.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Basket2 basketObj = new Basket2(basket.getMealId(),name.getText().toString(),Double.parseDouble(price.getText().toString()),Integer.parseInt(counter.getText().toString()));
                    db.toBasketDao().deleteItem(basketObj.getMealId());
                    Log.e("getSub_id", "onClick: " + basketObj.getMealId());
                }
            });
        }
    }
}
