package com.example.online_store.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.widget.Toast;

import com.example.online_store.Adapter.CustomAdapter;
import com.example.online_store.Adapter.jeweleryAdapter;
import com.example.online_store.Client.CategoryClinet;
import com.example.online_store.Model.CategoryModel;
import com.example.online_store.Model.jeweleryModel;
import com.example.online_store.R;
import com.example.online_store.user.OnItemClick;
import com.example.online_store.viewModel.postViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllCategory extends AppCompatActivity implements OnItemClick {
    RecyclerView recyclerView;
    CustomAdapter customAdapter;
    postViewModel postviewModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_category);
        getSupportActionBar().hide();
        postviewModels = new ViewModelProvider(this).get(postViewModel.class);
        postviewModels.getPostsFromRepo();
        customAdapter=new CustomAdapter(this);
        recyclerView=findViewById(R.id.reclist2);
        recyclerView.setHasFixedSize(true);
        postviewModels.posts2().observe(this, new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> strings) {
                customAdapter.setCategory(strings);
                recyclerView.setAdapter(customAdapter);
                LinearLayoutManager linearLayoutManager
                        = new LinearLayoutManager(AllCategory.this, LinearLayoutManager.VERTICAL,false);
                recyclerView.setLayoutManager(linearLayoutManager);
            }
        });
    }

    @Override
    public void onClick(String customAdapter) {
        startActivity(new Intent(AllCategory.this,fewProduct.class));

    }

    @Override
    public void onClickElectro(String customAdapter) {
        startActivity(new Intent(AllCategory.this,ElectroProduct.class));

    }

    @Override
    public void onClickMan(String customAdapter) {
        startActivity(new Intent(AllCategory.this,MenProduct.class));

    }

    @Override
    public void onClickWoman(String customAdapter) {
        startActivity(new Intent(AllCategory.this,WomanProduct.class));

    }
}