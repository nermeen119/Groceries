package com.example.online_store.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.online_store.Adapter.CartAdapter;
import com.example.online_store.Model.Cart;
import com.example.online_store.R;
import com.example.online_store.dataBase.CartDataBase;
import com.example.online_store.user.RemoveClick;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment implements RemoveClick {

    Button basket;
    BottomSheetDialog sheetDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_cart, container, false);
         final Dialog dialog = new Dialog(getActivity());
        RecyclerView recyclerView=v.findViewById(R.id.Cart_Recy);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        basket=v.findViewById(R.id.btcart1);
        basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sheetDialog=new BottomSheetDialog(getActivity(),R.style.BottomSheetStyle);
                View view1=LayoutInflater.from(getActivity()).inflate(R.layout.activity_bottom_sheet,(LinearLayout)v.findViewById(R.id.sheet1));
                sheetDialog.setContentView(view1);
                sheetDialog.show();
                view1.findViewById(R.id.order).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.setCancelable(false);
                        dialog.setContentView(R.layout.custom_dialog);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                        TextView backtoHome = dialog.findViewById(R.id.backhome);
                        Button Track_order = dialog.findViewById(R.id.trackorder);
                        backtoHome.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                sheetDialog.cancel();
                                dialog.cancel();
                                FragmentTransaction fragmentTransaction2 = getActivity().getSupportFragmentManager().beginTransaction();
                                fragmentTransaction2.replace(R.id.bottom_container,new HomeFragment()).commit();                            }
                        });
                        Track_order.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(getActivity(),"Order Succsse",Toast.LENGTH_SHORT).show();
                            }
                        });
                        dialog.show();
                    }
                });
                view1.findViewById(R.id.cancelimage1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sheetDialog.cancel();
                    }
                });
            }
        });
        CartAdapter cartAdapter=new CartAdapter(this);
        CartDataBase.getInstance(getActivity().getApplicationContext()).Deo().getAllProducts().observe(getActivity(), new Observer<List<Cart>>() {
            @Override
            public void onChanged(List<Cart> carts) {
                if(!carts.isEmpty())
                {
                    cartAdapter.setList(carts);
                    recyclerView.setAdapter(cartAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                }else
                {
                    dialog.setCancelable(false);
                    dialog.setContentView(R.layout.custom_item);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    Button GoToHome = dialog.findViewById(R.id.gohome);
                    ImageView cancel_dialog = dialog.findViewById(R.id.canceldialog);
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
                    cartAdapter.setList(carts);
                    recyclerView.setAdapter(cartAdapter);
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
                    CartDataBase.getInstance(getActivity()).Deo().deleteProduct(click);
                }
            }).start();
            Toast.makeText(getActivity().getApplicationContext(), "item deleted", Toast.LENGTH_SHORT).show();
        }catch (Exception e)
        {
            Toast.makeText(getActivity().getApplicationContext(), "error while delete", Toast.LENGTH_SHORT).show();
        }
    }
}