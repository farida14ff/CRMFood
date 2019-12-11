package com.example.crmfood.models;

public class Categories {

    private long id;
    private String department_names;
    private int category;

    public Categories(long id, String department_names, int categorys) {
        this.id = id;
        this.department_names = department_names;
        this.category = categorys;
    }

    public Categories(int category) {
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public String getDepartment_names() {
        return department_names;
    }

    public int getCategory() {
        return category;
    }
}
