package com.example.crmfood.adapters;


import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crmfood.R;
import com.example.crmfood.models.Basket;
import com.example.crmfood.subMenu.SubMenuActivity;
import com.example.crmfood.subMenu.SubMenuContract;

import java.util.ArrayList;
import java.util.List;

import com.example.crmfood.data.ToBasketRoomDatabase;

public class SubMenuAdapter extends RecyclerView.Adapter<SubMenuAdapter.SubMenuViewHolder> {

    private List<Basket> subMenuList;
    private SubMenuContract.OnItemClickListener onItemClickListener;
    private SubMenuActivity subMenuActivity = new SubMenuActivity();
    public Basket basket2 ;


    public SubMenuAdapter(SubMenuContract.OnItemClickListener onItemClickListener) {
        subMenuList = new ArrayList<>();
        this.onItemClickListener = onItemClickListener;
    }

    public void setValues(List<Basket> values) {
        subMenuList.clear();
        if (values != null) {
            subMenuList.addAll(values);
        }
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubMenuAdapter.SubMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.sub_menu_item,parent,false);
        return new SubMenuViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull SubMenuAdapter.SubMenuViewHolder holder, int position) {
        holder.bind(subMenuList.get(position),onItemClickListener);
        basket2 = holder.basket;
    }

    @Override
    public int getItemCount() {
        return subMenuList.size();
    }

     class SubMenuViewHolder extends RecyclerView.ViewHolder {

         TextView name;
         TextView price;
         CardView increase;
         CardView decrease;
         TextView counter;

         ImageView ImageBasket;
         ImageView ImageBasket2;

         ToBasketRoomDatabase db;
         public Basket basket;

         int number;
         double inPrice;
         double totPrice;


         String toBasketPrice = " ";

         SubMenuViewHolder(@NonNull View itemView) {
             super(itemView);

             db = ToBasketRoomDatabase.getDatabase(itemView.getContext());

             name = itemView.findViewById(R.id.poduct_name_sub);
             price = itemView.findViewById(R.id.item_price_sub);
             increase = itemView.findViewById(R.id.increase_menu_sub);
             counter = itemView.findViewById(R.id.counter_menu_sub);
             decrease = itemView.findViewById(R.id.decrease_menu_sub);
             ImageBasket = itemView.findViewById(R.id.to_basket_cv_sub);
             ImageBasket2 = itemView.findViewById(R.id.to_basket_cv2_sub);

//             number = 0;
             inPrice = 0;
             totPrice = 0;


         }

         void bind(final Basket basket2, final SubMenuContract.OnItemClickListener onItemClickListener) {

             itemView.setOnClickListener(e ->
                 onItemClickListener.onItemClick(basket2));

             name.setText(basket2.getBasket_name());
             inPrice = basket2.getBasket_price();
             price.setText(String.valueOf(inPrice));
             toBasketPrice = price.getText().toString();

             basket = basket2;

             increase.setOnClickListener(e ->{

                 basket.increase();
                 totPrice = inPrice * basket.getOrderedQuantity();
                 price.setText(String.valueOf(totPrice));
                 counter.setText(String.valueOf(basket.getOrderedQuantity()));
                 subMenuActivity.saveBasket(basket);
                 inBasketView();

                 Log.e("basketObj: increase", "name: " + name.getText().toString());
                 Log.e("basketObj: increase", "price: " + Double.parseDouble(price.getText().toString()));
                 Log.e("basketObj: increase", "counter: " + Integer.parseInt(counter.getText().toString()));
                 Log.e("basketObj: increase", "id: " + basket2.getMealId());

                 Log.e("toBasketPrice increase:", toBasketPrice);
             });

             decrease.setOnClickListener(e ->{
                 number = basket.getOrderedQuantity();

                 basket.decrease();
                 counter.setText(String.valueOf(basket.getOrderedQuantity()));
                 Log.e("SubMenuAdepter","bsaket quantity"+basket.getOrderedQuantity());


                 if (basket.getOrderedQuantity()>0){
                     Log.e("SubMenuAdepter","number>0");
                     totPrice = inPrice *basket.getOrderedQuantity();
                     price.setText(String.valueOf(totPrice));
                     subMenuActivity.saveBasket(basket);
                 }
                 else {
                     Log.e("SubMenuAdepter","fromBasketView");
                     fromBasketView();
                     subMenuActivity.deleteFromBasket(basket);
                     price.setText(String.valueOf(inPrice));
                 }

             });

             ImageBasket.setOnClickListener(e -> {
                 try {
                     basket.increase();
                     counter.setText(String.valueOf(basket.getOrderedQuantity()));
                     subMenuActivity.saveBasket(basket);
                     inBasketView();

                 }catch (SQLiteConstraintException ex){
                     Log.e("SubMenuAdapter", "SQLiteConstraintException");
                 }

             });

             ImageBasket2.setOnClickListener(e ->{
                 basket.decrease();
                 subMenuActivity.deleteFromBasket(basket);
                 fromBasketView();
                 basket.setOrderedQuantity(0);
                 counter.setText(String.valueOf(basket.getOrderedQuantity()));
                 price.setText(String.valueOf(inPrice));


             });

         }

         void inBasketView() {
             ImageBasket.setVisibility(View.GONE);
             ImageBasket2.setVisibility(View.VISIBLE);
         }

         void fromBasketView() {
             ImageBasket2.setVisibility(View.GONE);
             ImageBasket.setVisibility(View.VISIBLE);
         }

     }
}
