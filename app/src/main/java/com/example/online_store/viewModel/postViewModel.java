package com.example.online_store.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.online_store.Adapter.CustomAdapter;
import com.example.online_store.Model.CategoryModel;
import com.example.online_store.Model.UserResponseModel;
import com.example.online_store.Model.jeweleryModel;
import com.example.online_store.Repositry.Repositry;

import java.util.ArrayList;

public class postViewModel extends ViewModel {
    private final Repositry repositry=new Repositry();
    public LiveData<String> error()
    {
        return repositry.errorPosts();
    }

    String name,email,pass,email2,pass2;
    public LiveData<ArrayList<jeweleryModel>> PostsFullproduct()
    {
        return repositry.PostsFullproduct();
    }
    public LiveData<UserResponseModel> PostsSignin()
    {
        return repositry.postSignin(email,pass);
    }
    public LiveData<UserResponseModel> Postssignup()
    {
        return repositry.postSignup(name,email2,pass2);
    }

    public LiveData<ArrayList<jeweleryModel>> PostsMen()
    {
        return repositry.PostsMen();
    }
    public LiveData<ArrayList<jeweleryModel>> PostsWomen()
    {
        return repositry.PostsWomen();
    }
    public LiveData<ArrayList<jeweleryModel>> Postselectro()
    {
        return repositry.Postselectro();
    }
    public LiveData<ArrayList<jeweleryModel>> Postsjewlary()
    {
        return repositry.Postsjewlary();
    }
    public LiveData<ArrayList<jeweleryModel>> posts()
    {
        return repositry.Posts();
    }
    public LiveData<ArrayList<String>> posts2()
    {
        return repositry.Postscategory();
    }
    public void getPostsFromRepo()
    {
        repositry.getallPostfromSerevr();
    }
    public void getsignFromRepo(String email,String pass)
    {
        repositry.getallsignfromSerevr(email,pass);
    }
    public void getsignUpFromRepo(String names,String emails,String password)
    {
        repositry.getallsignUpfromSerevr(names,emails,password);
    }
}
