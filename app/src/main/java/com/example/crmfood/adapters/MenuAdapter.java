package com.example.crmfood.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crmfood.R;
import com.example.crmfood.menu.kitchenMenu.KitchenMenuContract;
import com.example.crmfood.models.MenuKitchen;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuICategoriesViewHolder> {

    private List<MenuKitchen> menuKitchenList;
    private KitchenMenuContract.OnItemClickListener clickListener;
    private Context context;

    public MenuAdapter(KitchenMenuContract.OnItemClickListener clickListener) {
        menuKitchenList = new ArrayList<>();
        this.clickListener = clickListener;
    }



    public void setValues(List<MenuKitchen> values) {
        menuKitchenList.clear();
        if (values != null) {
            menuKitchenList.addAll(values);
        }
        this.notifyDataSetChanged();
    }



    @NonNull
    @Override
    public MenuICategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.menu_kitchen_item,parent,false);
        return new MenuICategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuICategoriesViewHolder holder, int position) {
        holder.bind(menuKitchenList.get(position), clickListener);
    }

    @Override
    public int getItemCount() {
        return menuKitchenList.size();
    }


     class MenuICategoriesViewHolder extends RecyclerView.ViewHolder {

        TextView category_title;
//        ImageView category_image;

         MenuICategoriesViewHolder(@NonNull View itemView) {
            super(itemView);


            category_title = itemView.findViewById(R.id.category_menu_cv_title);
//            category_image = itemView.findViewById(R.id.category_menu_cv_backround_img);
         }

         @SuppressLint("CheckResult")
         void bind(final MenuKitchen menuKitchen, final KitchenMenuContract.OnItemClickListener onItemClickListener) {
             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     onItemClickListener.onItemClick(menuKitchen);
                 }
             });
             category_title.setText(menuKitchen.getCategoryName());

//             Glide.with(category_image.getContext())
//                     .load(menuKitchen.getCategoryImage())
//                     .into(category_image);

        }
    }
}
