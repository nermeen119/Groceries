package com.example.online_store.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.online_store.Adapter.AllProductAdapter;
import com.example.online_store.Client.CategoryClinet;
import com.example.online_store.Model.jeweleryModel;
import com.example.online_store.R;
import com.example.online_store.ui.Details;
import com.example.online_store.user.OnClickItem;
import com.example.online_store.viewModel.postViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExploreFragment extends Fragment implements OnClickItem {

    RecyclerView recyclerView;
    AllProductAdapter allProductAdapter;
    postViewModel postviewModels;
    ArrayList<jeweleryModel> jwList = new ArrayList<>();
    private SearchView searchView;
    ImageView carts;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_explore, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        allProductAdapter=new AllProductAdapter(this);
        recyclerView=v.findViewById(R.id.AllProducts);
        searchView=v.findViewById(R.id.search_Product);
        carts=v.findViewById(R.id.imagecart);
        searchView.clearFocus();
        postviewModels = new ViewModelProvider(this).get(postViewModel.class);
        postviewModels.getPostsFromRepo();
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
        carts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction2 = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction2.replace(R.id.bottom_container,new CartFragment()).commit();
            }
        });
    postviewModels.PostsFullproduct().observe(getActivity(), new Observer<ArrayList<jeweleryModel>>() {
        @Override
        public void onChanged(ArrayList<jeweleryModel> jeweleryModels) {
            if(!jeweleryModels.isEmpty()) {
                jwList = allProductAdapter.setItems(jeweleryModels);
                recyclerView.setAdapter(allProductAdapter);
                GridLayoutManager gridLayoutManager
                        = new GridLayoutManager(getActivity(), 2);
                recyclerView.setLayoutManager(gridLayoutManager);
            }
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
            allProductAdapter.setFilteredList2(filteredList);
        }
    }

    @Override
    public void onClickpro(jeweleryModel jeweleryModels) {
        Intent intent =new Intent(getActivity().getApplicationContext(), Details.class);
        intent.putExtra("title",jeweleryModels.getTitle());
        intent.putExtra("price",jeweleryModels.getPrice());
        intent.putExtra("description",jeweleryModels.getDescription());
        intent.putExtra("image",jeweleryModels.getImage());
        startActivity(intent);
    }

    @Override
    public void onClickpro2(jeweleryModel jewelerymodel1) {
       Toast.makeText(getActivity(),"MMM",Toast.LENGTH_SHORT).show();
    }
}