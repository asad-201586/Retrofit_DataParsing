package com.example.retrofit_dataparsing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.retrofit_dataparsing.databinding.ActivityMainBinding;
import com.example.retrofit_dataparsing.model.CityModel;
import com.example.retrofit_dataparsing.model.LoginModel;
import com.example.retrofit_dataparsing.model.MyCityModel;
import com.example.retrofit_dataparsing.model.PostModel;
import com.example.retrofit_dataparsing.model.PostModel;
import com.example.retrofit_dataparsing.utils.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "mainActivity";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser(); // practice post method
            }
        });

        //getPost(); // practice get method without params
        //getCities(); //practice get method with params

    }

    private void getCities() {
        Call<MyCityModel> call = RetrofitClient
                .getInstance()
                .getApi()
                .getCity("1");

        call.enqueue(new Callback<MyCityModel>() {
            @Override
            public void onResponse(@NotNull Call<MyCityModel> call, @NotNull Response<MyCityModel> response) {
                if (response.isSuccessful()){
                    Log.d(TAG, "onResponse: response found");
                    MyCityModel model = response.body();
                    ArrayList<CityModel> cityList = new ArrayList<>(Objects.requireNonNull(model).getCityList());
                    for (int i = 0; i< cityList.size(); i++){
                        Log.d(TAG, "onResponse: city_name: "+cityList.get(i).getName());
                    }

                }else {
                    Log.d(TAG, "onResponse: response failed");
                }
            }

            @Override
            public void onFailure(@NotNull Call<MyCityModel> call, @NotNull Throwable t) {
                Log.d(TAG, "onFailure: error found: "+t.getMessage());
            }
        });
    }

    private void getPost() {
        Call<ArrayList<PostModel>> call = RetrofitClient
                .getInstance()
                .getApi()
                .MyPosts();

        call.enqueue(new Callback<ArrayList<PostModel>>() {
            @Override
            public void onResponse(@NotNull Call<ArrayList<PostModel>> call, @NotNull Response<ArrayList<PostModel>> response) {
                if (response.isSuccessful()){
                    Log.d(TAG, "onResponse: response found");
                    ArrayList<PostModel> list = new ArrayList<>(Objects.requireNonNull(response.body()));
                    for (int i=0;i<list.size();i++){
                        Log.d(TAG, "onResponse: title: "+ list.get(i).getBody());
                    }
                }else {
                    Log.d(TAG, "onResponse: response not successful");
                }
            }

            @Override
            public void onFailure(@NotNull Call<ArrayList<PostModel>> call, @NotNull Throwable t) {
                Log.d(TAG, "onResponse: error occurred: "+t.getMessage());
            }
        });
    }

    private void loginUser() {
        String phone = binding.phoneEdt.getText().toString();
        String password = binding.passwordEdt.getText().toString();

        if(TextUtils.isEmpty(phone)){
            binding.phoneEdt.requestFocus();
            Toast.makeText(this, "Enter phone number", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password)){
            binding.passwordEdt.requestFocus();
            Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();
        }

        else {
            Call<LoginModel> call = RetrofitClient
                    .getInstance()
                    .getApi()
                    .Login(phone,password);

            call.enqueue(new Callback<LoginModel>() {
                @Override
                public void onResponse(@NotNull Call<LoginModel> call, @NotNull Response<LoginModel> response) {
                    if (response.isSuccessful()){
                        Log.d(TAG, "onResponse: response found");
                        Toast.makeText(MainActivity.this, "response found", Toast.LENGTH_SHORT).show();
                        LoginModel model = response.body();
                        Log.d(TAG, "onResponse: response: "+ Objects.requireNonNull(model).getMsg());
                        Log.d(TAG, "onResponse: response: "+ Objects.requireNonNull(model).getAccessToken());
                    }else {
                        Log.d(TAG, "onResponse: response not found");
                    }
                }

                @Override
                public void onFailure(@NotNull Call<LoginModel> call, @NotNull Throwable t) {
                    Log.d(TAG, "onResponse: Error found: "+t.getMessage());
                    Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}