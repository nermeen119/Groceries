package com.example.online_store.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.online_store.R;
import com.example.online_store.ui.Details;
import com.example.online_store.ui.MenProduct;
import com.example.online_store.user.OnItemClick;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.DataHolders>{
    private ArrayList<String> category;
    private OnItemClick onItemClick;
    public CustomAdapter(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @NonNull
    @NotNull
    @Override
    public DataHolders onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new DataHolders(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DataHolders holder, int position) {

        holder.title.setText(category.get(position));
        Random rnd = new Random();
        int currentColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        holder.card.setCardBackgroundColor(currentColor);
        if(category.get(position).equals("jewelery"))
        {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClick.onClick(category.get(position));
                }
            });
        }
        if(category.get(position).equals("electronics"))
        {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClick.onClickElectro(category.get(position));
                }
            });
        }
        if(category.get(position).equals("men's clothing"))
        {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClick.onClickMan(category.get(position));
                }
            });
        }
        if(category.get(position).equals("women's clothing"))
        {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClick.onClickWoman(category.get(position));
                }
            });
        }

    }
    public void setCategory(ArrayList<String> category)
    {
        this.category=category;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return category.size();
    }

    class DataHolders extends RecyclerView.ViewHolder
    {
        TextView title;
        CardView card;
        public DataHolders(@NonNull @NotNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.texofstring);
             card = itemView.findViewById(R.id.cardlist);
        }
    }
}
