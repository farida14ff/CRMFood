package com.example.crmfood.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crmfood.R;
import com.example.crmfood.models.MenuKitchen;
import com.example.crmfood.models.SubMenu;
import com.example.crmfood.subMenu.SubMenuContract;

import java.util.ArrayList;
import java.util.List;

public class SubMenuAdapter extends RecyclerView.Adapter<SubMenuAdapter.SubMenuViewHolder> {

    private List<SubMenu> subMenuList;
    private SubMenuContract.OnItemClickListener onItemClickListener;

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
        ImageView basket;
        ImageView basket2;

        int number;

         SubMenuViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.poduct_name_sub);
            price = itemView.findViewById(R.id.item_price_sub);
            increase = itemView.findViewById(R.id.increase_menu_sub);
            counter = itemView.findViewById(R.id.counter_menu_sub);
            decrease = itemView.findViewById(R.id.decrease_menu_sub);
            basket = itemView.findViewById(R.id.to_basket_cv_sub);
            basket2 = itemView.findViewById(R.id.to_basket_cv2_sub);

            number = 0;

         }

         void bind(final SubMenu subMenu, final SubMenuContract.OnItemClickListener onItemClickListener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(subMenu);
                }
            });

             name.setText(subMenu.getSub_name());
             price.setText(String.valueOf(subMenu.getSub_price()));

             increase.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     number++;
                     counter.setText(String.valueOf(number));
                     basket.setVisibility(View.GONE);
                     basket2.setVisibility(View.VISIBLE);
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
                         basket2.setVisibility(View.GONE);
                         basket.setVisibility(View.VISIBLE);
                     }
                 }
             });

             basket.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     //TODO: add meal to the to_basket
                     basket.setVisibility(View.GONE);
                     basket2.setVisibility(View.VISIBLE);
                     counter.setText("1");
                 }
             });

             basket2.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     //TODO: delete meal from to_basket
                     basket2.setVisibility(View.GONE);
                     basket.setVisibility(View.VISIBLE);
                     counter.setText("0");
                 }
             });
         }
    }
}
