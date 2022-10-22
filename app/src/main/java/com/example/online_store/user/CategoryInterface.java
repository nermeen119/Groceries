package com.example.online_store.user;

import com.example.online_store.Model.jeweleryModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryInterface {

    @GET("products/categories")
    Call<ArrayList<String>> getAllcategories();
    @GET("products/category/jewelery")
    Call<ArrayList<jeweleryModel>> getAlljewelery();
    @GET("products/category/electronics")
    Call<ArrayList<jeweleryModel>> getAllElectronics();
    @GET("products/category/men's%20clothing")
    Call<ArrayList<jeweleryModel>> getAllMenClothing();
    @GET("products/category/women's%20clothing")
    Call<ArrayList<jeweleryModel>> getAllWomanClothing();
    @GET("products?limit=5")
    Call<ArrayList<jeweleryModel>> getAllProduct();
    @GET("products")
    Call<ArrayList<jeweleryModel>> getFullProduct();
}
