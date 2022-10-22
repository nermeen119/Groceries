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
import android.widget.Toast;

import com.example.online_store.Adapter.jeweleryAdapter;
import com.example.online_store.Client.CategoryClinet;
import com.example.online_store.Model.Cart;
import com.example.online_store.Model.CategoryModel;
import com.example.online_store.Model.jeweleryModel;
import com.example.online_store.R;
import com.example.online_store.dataBase.CartDataBase;
import com.example.online_store.user.OnClickItem;
import com.example.online_store.viewModel.postViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ElectroProduct extends AppCompatActivity implements OnClickItem {
    String images,titles;
    double prices;
    RecyclerView recyclerView;
    jeweleryAdapter jeweleryAdapters;
    postViewModel postviewModels;

    ArrayList<jeweleryModel> jwList = new ArrayList<>();
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electro_product);
        getSupportActionBar().hide();
        jeweleryAdapters=new jeweleryAdapter(this);
        recyclerView=findViewById(R.id.electroproduct);
        postviewModels = new ViewModelProvider(this).get(postViewModel.class);
        postviewModels.getPostsFromRepo();
        searchView=findViewById(R.id.searchElectro);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
       postviewModels.Postselectro().observe(this, new Observer<ArrayList<jeweleryModel>>() {
           @Override
           public void onChanged(ArrayList<jeweleryModel> jeweleryModels) {
               if(!jeweleryModels.isEmpty())
               {
                   jwList= jeweleryAdapters.setItems(jeweleryModels);
                   recyclerView.setAdapter(jeweleryAdapters);
                   GridLayoutManager gridLayoutManager
                           = new GridLayoutManager(ElectroProduct.this,2);
                   recyclerView.setLayoutManager(gridLayoutManager);
               }
           }
       });
    }

    private void filterList(String text) {
        ArrayList<jeweleryModel> filteredList=new ArrayList<>();
        for (jeweleryModel model:jwList)
        {
            if(model.getTitle().toLowerCase().contains(text.toLowerCase()))
            {
                filteredList.add(model);
            }
        }
        if(filteredList.isEmpty())
        {
            Toast.makeText(ElectroProduct.this,"No Data Found",Toast.LENGTH_SHORT).show();
        }
        else
        {
            jeweleryAdapters.setFilteredList(filteredList);
        }
    }

    @Override
    public void onClickpro(jeweleryModel jeweleryModels) {
        Intent intent =new Intent(ElectroProduct.this,Details.class);
        intent.putExtra("title",jeweleryModels.getTitle());
        intent.putExtra("price",jeweleryModels.getPrice());
       intent.putExtra("description",jeweleryModels.getDescription());
        intent.putExtra("image",jeweleryModels.getImage());
        startActivity(intent);
    }

    @Override
    public void onClickpro2(jeweleryModel jewelerymodel1) {
        titles=jewelerymodel1.getTitle();
        images=jewelerymodel1.getImage();
        prices=jewelerymodel1.getPrice();
        Cart cart=new Cart(null,titles,images,prices);
        try{
            new Thread(new Runnable() {
                @Override
                public void run() {
                    CartDataBase.getInstance(ElectroProduct.this).Deo().insert(cart);
                }
            }).start();
            Toast.makeText(ElectroProduct.this, "Added to basket", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ElectroProduct.this, "error while insert", Toast.LENGTH_SHORT).show();
        }
    }


}