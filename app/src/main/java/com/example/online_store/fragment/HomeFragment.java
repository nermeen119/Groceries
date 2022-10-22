package com.example.online_store.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.online_store.Adapter.CustomAdapter;
import com.example.online_store.Adapter.jeweleryAdapter;
import com.example.online_store.Client.CategoryClinet;
import com.example.online_store.Model.Cart;
import com.example.online_store.Model.jeweleryModel;
import com.example.online_store.R;
import com.example.online_store.dataBase.CartDataBase;
import com.example.online_store.ui.AllCategory;
import com.example.online_store.ui.Details;
import com.example.online_store.ui.ElectroProduct;
import com.example.online_store.ui.MenProduct;
import com.example.online_store.ui.WomanProduct;
import com.example.online_store.ui.fewProduct;
import com.example.online_store.user.OnClickItem;
import com.example.online_store.user.OnItemClick;
import com.example.online_store.viewModel.postViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements OnItemClick, OnClickItem {

    String titles,images;
    double prices;
    RecyclerView recyclerView,recyclerView2;
    CustomAdapter customAdapter;
    jeweleryAdapter jeweleryAdapters;
    postViewModel postviewModels;
    TextView textView1;
    ArrayList<jeweleryModel> jwList = new ArrayList<>();
    private SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        textView1=v.findViewById(R.id.seeAll);
        postviewModels = new ViewModelProvider(this).get(postViewModel.class);
        postviewModels.getPostsFromRepo();
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity().getApplicationContext(), AllCategory.class));
            }
        });
        customAdapter=new CustomAdapter(this);
        recyclerView=(RecyclerView)v.findViewById(R.id.reclist);
        jeweleryAdapters=new jeweleryAdapter(this);
        recyclerView2=(RecyclerView)v.findViewById(R.id.recItem);
        searchView=v.findViewById(R.id.searchs);
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

        postviewModels.posts().observe(getActivity(), new Observer<ArrayList<jeweleryModel>>() {
            @Override
            public void onChanged(ArrayList<jeweleryModel> jeweleryModels) {
                if(!jeweleryModels.isEmpty())
                {
                    jwList=jeweleryAdapters.setItems(jeweleryModels);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false);
                    recyclerView2.setHasFixedSize(true);
                    recyclerView2.setLayoutManager(layoutManager);
                    recyclerView2.setAdapter(jeweleryAdapters);
                }
            }
        });
        postviewModels.error().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(!s.isEmpty())
                {
                    Log.d("Main","error nermeen "+s);
                }
            }
        });
       postviewModels.posts2().observe(getActivity(), new Observer<ArrayList<String>>() {
           @Override
           public void onChanged(ArrayList<String> strings) {
               customAdapter.setCategory(strings);
               RecyclerView.LayoutManager layoutManager2= new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false);
               recyclerView.setHasFixedSize(true);
               recyclerView.setLayoutManager(layoutManager2);
               recyclerView.setAdapter(customAdapter);
           }
       });
        return v;
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
            Toast.makeText(getActivity().getApplicationContext(),"No Data Found",Toast.LENGTH_SHORT).show();
        }
        else
        {
            jeweleryAdapters.setFilteredList(filteredList);
        }
    }

    @Override
    public void onClickpro(jeweleryModel jeweleryModels) {
        Intent intent =new Intent(getActivity(), Details.class);
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
                    CartDataBase.getInstance(getActivity()).Deo().insert(cart);
                }
            }).start();
            Toast.makeText(getActivity(), "Added to basket", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "error while insert", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(String customAdapter) {
        startActivity(new Intent(getActivity().getApplicationContext(), fewProduct.class));
    }

    @Override
    public void onClickElectro(String customAdapter) {
        startActivity(new Intent(getActivity().getApplicationContext(), ElectroProduct.class));
    }

    @Override
    public void onClickMan(String customAdapter) {
        startActivity(new Intent(getActivity().getApplicationContext(), MenProduct.class));
    }

    @Override
    public void onClickWoman(String customAdapter) {
        startActivity(new Intent(getActivity().getApplicationContext(), WomanProduct.class));
    }
}