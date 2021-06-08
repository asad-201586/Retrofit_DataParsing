package com.example.retrofit_dataparsing.utils;

import com.example.retrofit_dataparsing.model.LoginModel;
import com.example.retrofit_dataparsing.model.PostModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiResponse {

    @FormUrlEncoded
    @POST("/api/auth/login")
    Call<LoginModel> Login(
            @Field("mobile") String mobile,
            @Field("password") String password
    );

    @GET("/posts")
    Call<ArrayList<PostModel>> MyPosts();

}
