package com.example.online_store.Client;

import com.example.online_store.Model.jeweleryModel;
import com.example.online_store.user.CategoryInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryClinet {
    private static final String BASE_URL = "https://fakestoreapi.com/";
    private CategoryInterface categoryInterface;
    private  static CategoryClinet categoryClinet;

    public CategoryClinet() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        categoryInterface = retrofit.create(CategoryInterface.class);
    }

    public static CategoryClinet Get_Instance(){

        if (categoryClinet == null){
            categoryClinet = new CategoryClinet();
        }
        return categoryClinet;
    }
    public Call<ArrayList<String>> getAllcategories(){
        return categoryInterface.getAllcategories();
    }
    public Call<ArrayList<jeweleryModel>> getAlljewelery(){
        return categoryInterface.getAlljewelery();
    }

    public Call<ArrayList<jeweleryModel>> getAllElectronics(){
        return categoryInterface.getAllElectronics();
    }
    public Call<ArrayList<jeweleryModel>> getAllMenClothing(){
        return categoryInterface.getAllMenClothing();
    }
    public Call<ArrayList<jeweleryModel>> getAllWomanClothing(){
        return categoryInterface.getAllWomanClothing();
    }
    public Call<ArrayList<jeweleryModel>> getAllProduct(){
        return categoryInterface.getAllProduct();
    }
    public Call<ArrayList<jeweleryModel>> getFullProduct(){
        return categoryInterface.getFullProduct();
    }
}
