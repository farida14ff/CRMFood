package com.example.crmfood.adapters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crmfood.BaseActivity;
import com.example.crmfood.R;
import com.example.crmfood.models.Basket2;
import com.example.crmfood.models.SubMenu;
import com.example.crmfood.subMenu.SubMenuActivity;
import com.example.crmfood.subMenu.SubMenuContract;

import java.util.ArrayList;
import java.util.List;

import com.example.crmfood.data.ToBasketRoomDatabase;

public class SubMenuAdapter extends RecyclerView.Adapter<SubMenuAdapter.SubMenuViewHolder> {

    private List<SubMenu> subMenuList;
    private SubMenuContract.OnItemClickListener onItemClickListener;
    private SubMenuActivity subMenuActivity = new SubMenuActivity();



    public SubMenuAdapter(SubMenuContract.OnItemClickListener onItemClickListener) {
        subMenuList = new ArrayList<>();
        this.onItemClickListener = onItemClickListener;
    }

    public void setValues(List<SubMenu> values) {
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

         long id;
         View view1;
         long my_subId;
         String my_name;
         double my_price;
         int my_counter;
         ToBasketRoomDatabase db;
         ImageView basket;
         ImageView basket2;


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
             basket = itemView.findViewById(R.id.to_basket_cv_sub);
             basket2 = itemView.findViewById(R.id.to_basket_cv2_sub);

             number = 0;
             inPrice = 0;
             totPrice = 0;


         }

         void bind(final SubMenu subMenu, final SubMenuContract.OnItemClickListener onItemClickListener) {

             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     onItemClickListener.onItemClick(subMenu);
                 }
             });


             name.setText(subMenu.getSub_name());
             inPrice = subMenu.getSub_price();
             price.setText(String.valueOf(inPrice));
             toBasketPrice = price.getText().toString();

//             id = subMenu.getSub_id();
//             my_subId = subMenu.getSub_id();
//             my_name = name.getText().toString();
//             my_price = Double.parseDouble(toBasketPrice);
//             my_counter = Integer.parseInt(counter.getText().toString());

             increase.setOnClickListener(new View.OnClickListener() {
                 @SuppressLint("ShowToast")
                 @Override
                 public void onClick(View view) {
                     number++;
                     totPrice = inPrice * number;
                     price.setText(String.valueOf(totPrice));
                     counter.setText(String.valueOf(number));
                     inBasketView();
                     toBasketPrice = price.getText().toString();


                     id = subMenu.getSub_id();
                     try {
                         Basket2 basketObj = new Basket2(subMenu.getSub_id(), name.getText().toString(), Double.parseDouble(price.getText().toString()), Integer.parseInt(counter.getText().toString()));

////                         Log.e("SubMenuAdapter", "activeOrdersId: "+ subMenuActivity.activeOrdersId);
////                         if (subMenuActivity.activeOrdersId == 1) {
                             db.toBasketDao().addItems(basketObj);
////                         }else {
////                             AddMealList addMealList = new AddMealList(subMenu.getSub_id(), Integer.parseInt(counter.getText().toString()));
////                             db.toBasketDao().addItemsAddMeal(addMealList);
////                         }
//
                     }catch (SQLiteConstraintException ex){
                         Toast.makeText(view.getContext(),"Уже в корзине ",Toast. LENGTH_SHORT);
                     }
                     Log.e("basketObj: increase", "name: " + name.getText().toString());
                     Log.e("basketObj: increase", "price: " + Double.parseDouble(price.getText().toString()));
                     Log.e("basketObj: increase", "counter: " + Integer.parseInt(counter.getText().toString()));
                     Log.e("basketObj: increase", "id: " + subMenu.getSub_id());

                     Log.e("toBasketPrice increase:", toBasketPrice);
                 }
             });

             decrease.setOnClickListener(new View.OnClickListener() {
                 @SuppressLint("ShowToast")
                 @Override
                 public void onClick(View view) {
                     if (number > 1) {
                         number--;
                         totPrice = totPrice - inPrice;
                         if (totPrice < inPrice) {
                             price.setText(String.valueOf(inPrice));
                             number = 0;
                             Basket2 basketObj = new Basket2(subMenu.getSub_id(), name.getText().toString(), Double.parseDouble(price.getText().toString()), Integer.parseInt(counter.getText().toString()));
//                             if (subMenuActivity.activeOrdersId == 1) {
                             db.toBasketDao().deleteItem(basketObj.getMealId());
//                             }else {
//                                 AddMealList addMealList = new AddMealList(subMenu.getSub_id(), Integer.parseInt(counter.getText().toString()));
//                                 db.toBasketDao().deleteItemAddMeal(addMealList.getMealId());
                             //}
//                             Log.e("getSub_id", "onClick: " + basketObj.getMealId());

                         } else {
                             price.setText(String.valueOf(totPrice));
                             try {
                                 Basket2 basketObj = new Basket2(subMenu.getSub_id(), name.getText().toString(), Double.parseDouble(toBasketPrice), Integer.parseInt(counter.getText().toString()));
                                 db.toBasketDao().addItems(basketObj);
                             }catch (SQLiteConstraintException ex){
                                 Toast.makeText(view.getContext(),"Уже в корзине ",Toast. LENGTH_SHORT);
                             }
                             Log.e("toBasketPrice:",  toBasketPrice);
                         }
                         if (number == 0) {
                             fromBasketView();
                             Basket2 basketObj = new Basket2(subMenu.getSub_id(), name.getText().toString(), Double.parseDouble(price.getText().toString()), Integer.parseInt(counter.getText().toString()));
                             db.toBasketDao().deleteItem(basketObj.getMealId());
                             Log.e("getSub_id", "onClick: " + basketObj.getMealId());
                         }
                         counter.setText(String.valueOf(number));
                     } else {
                         number = 0;
                         Basket2 basketObj = new Basket2(subMenu.getSub_id(), name.getText().toString(), Double.parseDouble(price.getText().toString()), Integer.parseInt(counter.getText().toString()));
                         db.toBasketDao().deleteItem(basketObj.getMealId());
                         Log.e("getSub_id", "onClick: " + basketObj.getMealId());
                         counter.setText(String.valueOf(number));
                         fromBasketView();

                     }
                     toBasketPrice = price.getText().toString();
                     Log.e("basketObj: decrease", "name:  " + name.getText().toString());
                     Log.e("basketObj: decrease", "price: " + Double.parseDouble(price.getText().toString()));
                     Log.e("basketObj: decrease", "counter: " + Integer.parseInt(counter.getText().toString()));
                     Log.e("basketObj: decrease", "id: " + subMenu.getSub_id());

                     Log.e("toBasketPrice decrease:", toBasketPrice);

//                     SubMenu subMenu1 = new SubMenu(subMenu.getSub_id(),name.getText().toString(),Double.parseDouble(toBasketPrice),Integer.parseInt(counter.getText().toString()));


                 }
             });

             basket.setOnClickListener(new View.OnClickListener() {
                 @SuppressLint({"LongLogTag", "ShowToast"})
                 @Override
                 public void onClick(View view) {
//                     id = subMenu.getSub_id();
//                     my_subId = subMenu.getSub_id();
//                     my_name = name.getText().toString();
//                     my_price = Double.parseDouble(toBasketPrice);
//                     my_counter = Integer.parseInt(counter.getText().toString());
//                     Log.e("basketObj:", "name: "+ name.getText().toString());
//                     Log.e("basketObj:", "price: "+ Double.parseDouble(price.getText().toString()));
//                     Log.e("basketObj:", "counter: "+ Integer.parseInt(counter.getText().toString()));
//                     Log.e("basketObj:", "id: "+ subMenu.getSub_id());
//
//                     Log.e("toBasketPrice basket:",  toBasketPrice);
//                     Log.e("toBasketPrice:",  toBasketPrice);

                     try {
                         Basket2 basketObj = new Basket2(subMenu.getSub_id(), name.getText().toString(), Double.parseDouble(toBasketPrice), Integer.parseInt(counter.getText().toString()));
                         db.toBasketDao().addItems(basketObj);
                     }catch (SQLiteConstraintException ex){
                        Toast.makeText(view.getContext(),"Уже в корзине ",Toast. LENGTH_SHORT);
                     }


//                     basketObjToRoom(my_subId, view, my_name,my_price,my_counter,db);

//                     changeData(subMenu.getSub_id());
//                     changeData(subMenu.getSub_id(),view);
                     inBasketView();
                     counter.setText("1");
                 }
             });

             basket2.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {

                     Basket2 basketObj = new Basket2(subMenu.getSub_id(), name.getText().toString(), Double.parseDouble(price.getText().toString()), Integer.parseInt(counter.getText().toString()));
                     db.toBasketDao().deleteItem(basketObj.getMealId());
                     Log.e("getSub_id", "onClick: " + basketObj.getMealId());
                     fromBasketView();
                     counter.setText("0");

//                     id = subMenu.getSub_id();
//                     my_subId = subMenu.getSub_id();
//                     my_name = name.getText().toString();
//                     my_price = Double.parseDouble(toBasketPrice);
//                     my_counter = Integer.parseInt(counter.getText().toString());
                 }
             });


         }

         void inBasketView() {
             basket.setVisibility(View.GONE);
             basket2.setVisibility(View.VISIBLE);
         }


         void fromBasketView() {
             basket2.setVisibility(View.GONE);
             basket.setVisibility(View.VISIBLE);
         }


     }


//    @SuppressLint("ShowToast")
//    public void basketObjToRoom(long my_sub_id, View view, String my_name, double my_price, int my_counter, ToBasketRoomDatabase db, ImageView basket) {
//
//        if (basket.isSelected()) {
//            try {
//                Basket2 basketObj = new Basket2(my_sub_id, my_name, my_price, my_counter);
//                db.toBasketDao().addItems(basketObj);
//            } catch (SQLiteConstraintException ex) {
//                //Toast.makeText(view.getContext(), "Уже в корзине ", Toast.LENGTH_SHORT);
//            }
//            Log.e("changeData", "basket selected: ");
//            Log.e("changeData", "my_sub_id selected: "+my_sub_id);
//            Log.e("changeData", "my_name selected: "+my_name);
//            Log.e("changeData", "my_price selected: "+my_price);
//            Log.e("changeData", "my_counter selected: "+my_counter);
//
//
//        } else {
//            Basket2 basketObj = new Basket2(my_sub_id, my_name, my_price, my_counter);
//            db.toBasketDao().deleteItem(basketObj.getMealId());
//            Log.e("changeData", "basket is not selected: ");
//        }
//    }
}
