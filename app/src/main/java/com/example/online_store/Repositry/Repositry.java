package com.example.online_store.Repositry;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.online_store.Client.CategoryClinet;
import com.example.online_store.Client.UserClient;
import com.example.online_store.Model.Cart;
import com.example.online_store.Model.Fav;
import com.example.online_store.Model.UserResponseModel;
import com.example.online_store.Model.jeweleryModel;
import com.example.online_store.dataBase.FavDataBase;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repositry  {
    String email,pass,name;
    private MutableLiveData<ArrayList<jeweleryModel>> responsePost=new MutableLiveData<>();
    private MutableLiveData<ArrayList<String>> responseCategory=new MutableLiveData<>();
    private MutableLiveData<ArrayList<jeweleryModel>> responseMen=new MutableLiveData<>();
    private MutableLiveData<ArrayList<jeweleryModel>> responseWomen=new MutableLiveData<>();
    private MutableLiveData<ArrayList<jeweleryModel>> responseElectro=new MutableLiveData<>();
    private MutableLiveData<ArrayList<jeweleryModel>> responsejewlary=new MutableLiveData<>();
    private MutableLiveData<ArrayList<jeweleryModel>> responsefullproduct=new MutableLiveData<>();
    private MutableLiveData<UserResponseModel> responsSignin=new MutableLiveData<UserResponseModel>();
    private MutableLiveData<UserResponseModel>responseSignup=new MutableLiveData<>();
    private  MutableLiveData<String> errorpost=new MutableLiveData<>();
    public LiveData<String>errorPosts()
    {
        return errorpost;
    }
    public LiveData<ArrayList<jeweleryModel>>Posts()
    {
        return responsePost;
    }
    public LiveData<ArrayList<jeweleryModel>>PostsFullproduct()
    {
        return responsefullproduct;
    }
    public LiveData<ArrayList<jeweleryModel>>PostsMen()
    {
        return responseMen;
    }
    public LiveData<ArrayList<jeweleryModel>>PostsWomen() { return responseWomen; }
    public LiveData<ArrayList<jeweleryModel>>Postselectro()
    {
        return responseElectro;
    }
    public LiveData<ArrayList<jeweleryModel>>Postsjewlary()
    {
        return responsejewlary;
    }
    public LiveData<ArrayList<String>>Postscategory()
    {
        return responseCategory;
    }
    public LiveData<UserResponseModel> postSignin(String email,String pass) { return responsSignin; }
    public LiveData<UserResponseModel>postSignup(String name,String email,String pass) { return responseSignup; }

    public void getallPostfromSerevr()
    {
        CategoryClinet.Get_Instance().getAllProduct().enqueue(new Callback<ArrayList<jeweleryModel>>() {
            @Override
            public void onResponse(Call<ArrayList<jeweleryModel>> call, Response<ArrayList<jeweleryModel>> response) {
                if(response.isSuccessful())
                {
                    responsePost.setValue(response.body());

                }
                else
                {
                    errorpost.setValue(response.errorBody().toString());

                }
            }

            @Override
            public void onFailure(Call<ArrayList<jeweleryModel>> call, Throwable t) {
                errorpost.setValue(t.getMessage());

            }
        });
        CategoryClinet.Get_Instance().getAllcategories().enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                if(response.isSuccessful())
                {
                    responseCategory.setValue(response.body());

                }
                else
                {
                    errorpost.setValue(response.errorBody().toString());

                }
            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                errorpost.setValue(t.getMessage());

            }
        });
        CategoryClinet.Get_Instance().getFullProduct().enqueue(new Callback<ArrayList<jeweleryModel>>() {
            @Override
            public void onResponse(Call<ArrayList<jeweleryModel>> call, Response<ArrayList<jeweleryModel>> response) {
                if(response.isSuccessful())
                {
                    responsefullproduct.setValue(response.body());

                }
                else
                {
                    errorpost.setValue(response.errorBody().toString());

                }
            }

            @Override
            public void onFailure(Call<ArrayList<jeweleryModel>> call, Throwable t) {
                errorpost.setValue(t.getMessage());

            }
        });
        CategoryClinet.Get_Instance().getAllElectronics().enqueue(new Callback<ArrayList<jeweleryModel>>() {
            @Override
            public void onResponse(Call<ArrayList<jeweleryModel>> call, Response<ArrayList<jeweleryModel>> response) {
                if(response.isSuccessful())
                {
                    responseElectro.setValue(response.body());
                }
                else
                {
                    errorpost.setValue(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<jeweleryModel>> call, Throwable t) {

                errorpost.setValue(t.getMessage());
            }
        });

        CategoryClinet.Get_Instance().getAlljewelery().enqueue(new Callback<ArrayList<jeweleryModel>>() {
            @Override
            public void onResponse(Call<ArrayList<jeweleryModel>> call, Response<ArrayList<jeweleryModel>> response) {
                if(response.isSuccessful())
                {
                    responsejewlary.setValue(response.body());
                }
                else
                {
                    errorpost.setValue(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<jeweleryModel>> call, Throwable t) {
                errorpost.setValue(t.getMessage());

            }
        });
        CategoryClinet.Get_Instance().getAllMenClothing().enqueue(new Callback<ArrayList<jeweleryModel>>() {
            @Override
            public void onResponse(Call<ArrayList<jeweleryModel>> call, Response<ArrayList<jeweleryModel>> response) {
                if(response.isSuccessful())
                {
                    responseMen.setValue(response.body());
                }
                else
                {
                    errorpost.setValue(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<jeweleryModel>> call, Throwable t) {
                errorpost.setValue(t.getMessage());

            }
        });
        CategoryClinet.Get_Instance().getAllWomanClothing().enqueue(new Callback<ArrayList<jeweleryModel>>() {
            @Override
            public void onResponse(Call<ArrayList<jeweleryModel>> call, Response<ArrayList<jeweleryModel>> response) {
                if(response.isSuccessful())
                {
                    responseWomen.setValue(response.body());
                }
                else
                {
                    errorpost.setValue(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<jeweleryModel>> call, Throwable t) {
                errorpost.setValue(t.getMessage());

            }
        });

    }
    public void getallsignfromSerevr(String emails,String password)
    {
        UserClient.getInstance().signIn(emails,password).enqueue(new Callback<UserResponseModel>() {
            @Override
            public void onResponse(Call<UserResponseModel> call, Response<UserResponseModel> response) {
                if(response.isSuccessful())
                {
                    responsSignin.setValue(response.body());
                }
                else
                {
                    errorpost.setValue(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<UserResponseModel> call, Throwable t) {
                errorpost.setValue(t.getMessage());

            }
        });

    }

    public void getallsignUpfromSerevr(String name,String email,String pass)
    {
       UserClient.getInstance().signUp(name,email,pass).enqueue(new Callback<UserResponseModel>() {
           @Override
           public void onResponse(Call<UserResponseModel> call, Response<UserResponseModel> response) {
               if(response.isSuccessful())
               {
                   responseSignup.setValue(response.body());
               }
               else
               {
                   errorpost.setValue(response.errorBody().toString());
               }
           }

           @Override
           public void onFailure(Call<UserResponseModel> call, Throwable t) {
               errorpost.setValue(t.getMessage());

           }
       });

    }

}
