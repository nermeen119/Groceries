package com.example.online_store.user;

import com.example.online_store.Model.UserResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserInterface {
    @FormUrlEncoded
    @POST("signup.php")
    Call<UserResponseModel> singUp(@Field("name") String name , @Field("email")String email , @Field("password")String password);

    @FormUrlEncoded
    @POST("login.php")
    Call<UserResponseModel> singIn(@Field("email")String email ,
                                   @Field("password")String password);
}
