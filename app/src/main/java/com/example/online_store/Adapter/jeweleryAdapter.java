package com.example.online_store.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.online_store.Model.jeweleryModel;
import com.example.online_store.R;
import com.example.online_store.ui.Details;
import com.example.online_store.user.OnClickItem;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class jeweleryAdapter extends RecyclerView.Adapter<jeweleryAdapter.DataHolders>{

    private ArrayList<jeweleryModel> jeweleryModels;

    private OnClickItem onClickItem;

    public jeweleryAdapter(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    @NonNull
    @NotNull
    @Override
    public DataHolders onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new DataHolders(LayoutInflater.from(parent.getContext()).inflate(R.layout.jewelery_item,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DataHolders holder, int position) {
        Picasso.get().load(jeweleryModels.get(position).getImage())
                .into(holder.imageButton);
        holder.title.setText(jeweleryModels.get(position).getTitle());
        holder.price.setText("$"+jeweleryModels.get(position).getPrice());

        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickItem.onClickpro(jeweleryModels.get(position));
            }
        });
        holder.addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickItem.onClickpro2(jeweleryModels.get(position));
            }
        });
    }

    public void setFilteredList(ArrayList<jeweleryModel> filteredList)
    {
        this.jeweleryModels=filteredList;
        notifyDataSetChanged();
    }
    public ArrayList<jeweleryModel> setItems(ArrayList<jeweleryModel>jeweleryModels)
    {
        this.jeweleryModels=jeweleryModels;
        notifyDataSetChanged();
        return jeweleryModels;
    }
    @Override
    public int getItemCount() {
        return jeweleryModels.size();
    }

    class DataHolders extends RecyclerView.ViewHolder
    {
        TextView title,price;
        ImageButton imageButton,addItem;
        public DataHolders(@NonNull @NotNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.jwTitle);
            price=itemView.findViewById(R.id.jwprice);
            imageButton=itemView.findViewById(R.id.jewelery_image);
            addItem=itemView.findViewById(R.id.Imageadditem);
        }
    }
}
