package com.example.retrofit_dataparsing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.retrofit_dataparsing.databinding.ActivityMainBinding;
import com.example.retrofit_dataparsing.model.LoginModel;
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
                loginUser();
            }
        });

        getPost();


//        ArrayList<String> list = new ArrayList<>();
//        list.add("Asad1");
//        list.add("Asad2");
//        list.add("Asad3");
//        list.add("Asad4");
//        list.add("Asad5");
//        for (int i=0;i<list.size();i++){
//            System.out.println("my name: "+list.get(i));
//        }

    }

    private void getPost() {
        Call<ArrayList<PostModel>> call = RetrofitClient
                .getInstance()
                .getApi()
                .MyPosts();

        call.enqueue(new Callback<ArrayList<PostModel>>() {
            @Override
            public void onResponse(@NotNull Call<ArrayList<PostModel>> call, Response<ArrayList<PostModel>> response) {
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

//        call.enqueue(new Callback<PostListModel>() {
//            @Override
//            public void onResponse(@NotNull Call<ArrayList<>> call, @NotNull Response<ArrayList<PostModel>> response) {
//                if (response.isSuccessful()){
//                    Log.d(TAG, "onResponse: response found");
//                    //PostListModel postModel = response.body();
//                    ArrayList<PostModel> list = new ArrayList<>();
//                    list = response.body();
//                    Log.d(TAG, "onResponse: title: "+ postModel.getPost().getPosts().get(0).getBody());
//                    //Log.d(TAG, "onResponse: title: "+ Objects.requireNonNull(postModel).getPosts().get(0).getId());
//                    //Log.d(TAG, "onResponse: title: "+ Objects.requireNonNull(postModel).getPosts().get(0).getBody());
//                }else {
//                    Log.d(TAG, "onResponse: response not successful");
//                }
//            }
//
//            @Override
//            public void onFailure(@NotNull Call<PostListModel> call, @NotNull Throwable t) {
//                Log.d(TAG, "onResponse: error occurred: "+t.getMessage());
//            }
//        });
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