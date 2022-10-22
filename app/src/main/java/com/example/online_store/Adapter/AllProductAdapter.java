package com.example.online_store.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.online_store.Model.jeweleryModel;
import com.example.online_store.R;
import com.example.online_store.user.OnClickItem;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;

public class AllProductAdapter extends RecyclerView.Adapter<AllProductAdapter.DataHolders> {


    private ArrayList<jeweleryModel> jeweleryModels;
    private OnClickItem onClickItem;

    public AllProductAdapter(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    @NonNull
    @NotNull
    @Override
    public DataHolders onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new DataHolders(LayoutInflater.from(parent.getContext()).inflate(R.layout.all_product_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DataHolders holder, int position) {
        Random rnds = new Random();
        int colors = Color.argb(255, rnds.nextInt(256), rnds.nextInt(256), rnds.nextInt(256));
        holder.cardView.setCardBackgroundColor(colors);
        Picasso.get().load(jeweleryModels.get(position).getImage())
                .into(holder.imageButton);
        holder.title.setText(jeweleryModels.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickItem.onClickpro(jeweleryModels.get(position));
            }
        });

    }
    public void setFilteredList2(ArrayList<jeweleryModel> filteredList)
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
        TextView title;
        ImageButton imageButton;
        CardView cardView;
        public DataHolders(@NonNull @NotNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.products_Title);
            imageButton=itemView.findViewById(R.id.products_images);
             cardView = (CardView) itemView.findViewById(R.id.cardproduct);

        }
    }
}
