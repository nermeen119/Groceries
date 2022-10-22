package com.example.online_store.Client;

import com.example.online_store.Model.UserResponseModel;
import com.example.online_store.user.UserInterface;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserClient {
    private static final String BASE_URL = "https://bego8889.000webhostapp.com/php/";
    private UserInterface userInterface;
    private static UserClient INSTANCE;

    public UserClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userInterface = retrofit.create(UserInterface.class);
    }
    public static UserClient getInstance(){

        if (INSTANCE == null)
        {
            INSTANCE = new UserClient();
        }

        return INSTANCE;
    }

    public Call<UserResponseModel> signUp(String name , String email , String password){
        return userInterface.singUp(name,email,password);
    }

    public Call<UserResponseModel> signIn(String email , String password){
        return userInterface.singIn(email,password);
    }
}
