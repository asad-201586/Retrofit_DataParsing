package com.example.retrofit_dataparsing.utils;

import com.example.retrofit_dataparsing.model.LoginModel;
import com.example.retrofit_dataparsing.model.MyCityModel;
import com.example.retrofit_dataparsing.model.PostModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiResponse {

    @FormUrlEncoded
    @POST("/api/auth/login")
    Call<LoginModel> Login(
            @Field("mobile") String mobile,
            @Field("password") String password
    );

    @GET("/posts")
    Call<ArrayList<PostModel>> MyPosts();

    @GET("/api/cities")
    Call<MyCityModel> getCity(
            @Query("division_id") String divisionId
    );

}
