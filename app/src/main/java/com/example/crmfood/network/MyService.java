package com.example.crmfood.network;

import com.example.crmfood.models.ActiveOrder;

import com.example.crmfood.models.AddMeal;
import com.example.crmfood.models.CategoryId;
import com.example.crmfood.models.CloseCheque;
import com.example.crmfood.models.CreateOrder;
import com.example.crmfood.models.Login;
import com.example.crmfood.models.MenuBar;
import com.example.crmfood.models.MenuKitchen;
import com.example.crmfood.models.OrderId;
import com.example.crmfood.models.StatusMessage;
import com.example.crmfood.models.SubMenu;
import com.example.crmfood.models.Table;
import com.example.crmfood.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface MyService {

    @POST("/api/Account/Login")
    Call<User> login(@Body Login login);

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

    @POST("/api/Waiter/closeCheque")
    Call<CloseCheque> closeCheque(@Header("Authorization") String authToken, @Body OrderId orderId);

    @POST("/api/Waiter/createOrder")
    Call<StatusMessage> createOrder(@Header("Authorization") String authToken, @Body CreateOrder createOrder);

    @POST("/api/Waiter/addMealsOrder")
    Call<StatusMessage> addMealsOrder(@Header("Authorization") String authToken, @Body AddMeal addMeal);







}