package com.example.crmfood.network;

import com.example.crmfood.models.ActiveOrder;
import com.example.crmfood.models.CategoryId;
import com.example.crmfood.models.MenuBar;
import com.example.crmfood.models.MenuKitchen;
import com.example.crmfood.models.SubMenu;
import com.example.crmfood.models.Table;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface MyService {

    @GET("/api/Waiter/getTables")
    Call<List<Table>> getTables(@Header("Authorization") String authToken);

    @GET("/api/Waiter/getActiveOrders")
    Call<List<ActiveOrder>> getListOfActiveOrders(@Header("Authorization") String authToken);

    @GET("/api/Waiter/getKitchenMenu")
    Call<List<MenuKitchen>> getMenuKitchen(@Header("Authorization") String authToken);

    @POST("/api/Waiter/getMeals")
    Call<List<SubMenu>> getSubMenu(@Header("Authorization") String authToken, @Body CategoryId categoryId);

    @GET("/api/Waiter/getBarMenu")
    Call<List<MenuBar>> getMenuBar(@Header("Authorization") String authToken);

//    @POST("http://neobiscrmfood.herokuapp.com/api/Account/Login")
//    Call<User> login(@Body Login login);




}