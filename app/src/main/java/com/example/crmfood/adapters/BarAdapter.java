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
import com.example.crmfood.menu.MainMenuActivity;
import com.example.crmfood.menu.barMenu.BarMenuContract;
import com.example.crmfood.models.MenuBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class BarAdapter extends RecyclerView.Adapter<BarAdapter.BarICategoriesViewHolder> {

    private List<MenuBar> menuBarList;
    private BarMenuContract.OnItemClickListener clickListener;
    private Context context;
    MainMenuActivity mainMenuActivity = new MainMenuActivity();

    public BarAdapter(BarMenuContract.OnItemClickListener clickListener) {
        menuBarList = new ArrayList<>();
        this.clickListener = clickListener;
    }




    public void setValues(List<MenuBar> values) {
        menuBarList.clear();
        if (values != null) {
            menuBarList.addAll(values);
        }
        this.notifyDataSetChanged();
    }



    @NonNull
    @Override
    public BarICategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.menu_bar_item,parent,false);
        return new BarICategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BarICategoriesViewHolder holder, int position) {
        holder.bind(menuBarList.get(position), clickListener);
    }

    @Override
    public int getItemCount() {
        return menuBarList.size();
    }

    class BarICategoriesViewHolder extends RecyclerView.ViewHolder {

        TextView category_title;
//        ImageView category_image;

        BarICategoriesViewHolder(@NonNull View itemView) {
            super(itemView);


            category_title = itemView.findViewById(R.id.bar_cv_title);
//            category_image = itemView.findViewById(R.id.bar_cv_backround_img);
        }

        @SuppressLint("CheckResult")
        void bind(final MenuBar menuBar, final BarMenuContract.OnItemClickListener onItemClickListener) {
            itemView.setOnClickListener(view -> {
                onItemClickListener.onItemClick(menuBar);

                itemView.setEnabled(false);

                Timer buttonTimer = new Timer();
                buttonTimer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        mainMenuActivity.runOnUiThread(() -> itemView.setEnabled(true));
                    }
                }, 5000);
            });

            category_title.setText(menuBar.getCategoryName());

//            Glide.with(category_image.getContext())
//                    .load(menuBar.getCategoryImage())
//                    .into(category_image);

        }
    }
}
