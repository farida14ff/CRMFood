package com.example.crmfood.tables;

import com.example.crmfood.models.Table;

import java.util.List;

public interface TablesContract {

    interface View {
        void setTables(List<Table> tables);
        void showMenu(Table table);
        void showError();
    }

    interface OnItemClickListener {
        void onItemClick(Table table);
    }

    interface Presenter{
        void displayTables();
    }

}
