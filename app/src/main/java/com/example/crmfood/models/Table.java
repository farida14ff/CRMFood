package com.example.crmfood.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Table implements Serializable  {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("status")
    private int status;

    public Table(int id, String name, int status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Table() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
