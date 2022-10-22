package com.example.online_store.fragment;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.online_store.Adapter.FavAdapter;
import com.example.online_store.Model.Fav;
import com.example.online_store.R;
import com.example.online_store.dataBase.CartDataBase;
import com.example.online_store.dataBase.FavDataBase;
import com.example.online_store.user.RemoveClick;

import java.util.List;


public class favouriteFragment extends Fragment implements RemoveClick {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_favourite, container, false);
        final Dialog dialog = new Dialog(getActivity());
        RecyclerView recyclerView=v.findViewById(R.id.FavoriteRecy);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        FavAdapter favAdapter=new FavAdapter(this);
        FavDataBase.getInstance(getActivity()).Deo().getAllProducts().observe(getActivity(), new Observer<List<Fav>>() {
            @Override
            public void onChanged(List<Fav> favs) {

                if(!favs.isEmpty())
                {
                   // textView.setVisibility(View.GONE);
                    favAdapter.setList(favs);
                    recyclerView.setAdapter(favAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                }else
                {
                    dialog.setCancelable(false);
                    dialog.setContentView(R.layout.custom_fav);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    Button GoToHome = dialog.findViewById(R.id.gotohomefav);
                    ImageView cancel_dialog = dialog.findViewById(R.id.canceldialogfav);
                    GoToHome.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            FragmentTransaction fragmentTransaction2 = getActivity().getSupportFragmentManager().beginTransaction();
                            fragmentTransaction2.replace(R.id.bottom_container,new HomeFragment()).commit();
                            dialog.cancel();

                        }
                    });
                    cancel_dialog.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });
                    favAdapter.setList(favs);
                    recyclerView.setAdapter(favAdapter);
                    dialog.show();
                }
            }
        });
        return v;
    }

    @Override
    public void onClick(Long click) {
        try{
            new Thread(new Runnable() {
                @Override
                public void run() {
                    FavDataBase.getInstance(getActivity()).Deo().deleteProductWithId(click);
                }
            }).start();
            Toast.makeText(getActivity(), "item deleted", Toast.LENGTH_SHORT).show();
        }catch (Exception e)
        {
            Toast.makeText(getActivity(), "error while delete", Toast.LENGTH_SHORT).show();
        }
    }
}