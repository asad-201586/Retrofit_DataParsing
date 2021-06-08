package com.example.retrofit_dataparsing.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    //public static String baseUrl = "https://flyingbird.smartsoftwarebd.com";
    //public static String baseUrl = "https://jsonplaceholder.typicode.com";
    public static String baseUrl = "https://flyingbird-bd.com";
    public static RetrofitClient retrofitClient;
    public static Retrofit retrofit;

    public RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance(){
        if (retrofitClient == null)
            retrofitClient = new RetrofitClient();
        return retrofitClient;
    }

    public ApiResponse getApi(){
        return  retrofit.create(ApiResponse.class);
    }

}
