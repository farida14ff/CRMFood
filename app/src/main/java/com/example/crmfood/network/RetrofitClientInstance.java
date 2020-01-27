package com.example.crmfood.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
//    private static final String BASE_URL = "http://neobiscrmfood.herokuapp.com";
//    private static final String BASE_URL = "http://crmfoodwebapp.herokuapp.com";
    private static final String BASE_URL = "http://crmfood.neobis.kg";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
