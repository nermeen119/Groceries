package com.example.online_store.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.online_store.Model.Fav;
import com.example.online_store.R;
import com.example.online_store.user.RemoveClick;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FavAdapter  extends RecyclerView.Adapter<FavAdapter.DataHolder> {

    RemoveClick removeItem;

    public FavAdapter(RemoveClick removeItem) {
        this.removeItem = removeItem;
    }

    List<Fav> list;
    @NonNull
    @NotNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new DataHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.save_fav,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DataHolder holder, int position) {
        Picasso.get().load(list.get(position).getItemImage())
                .into(holder.image);
        holder.productName.setText(list.get(position).getItemTitle());
        holder.price.setText("$"+list.get(position).getPrice());
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeItem.onClick(list.get(position).getId());
            }
        });
    }

    public void setList(List<Fav>list)
    {
        this.list=list;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DataHolder extends RecyclerView.ViewHolder{
        ImageView image;
        ImageButton remove;
        TextView productName,price;
        public DataHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.imagefav);
            productName=itemView.findViewById(R.id.textitle3);
            price=itemView.findViewById(R.id.ptxpriceitem);
            remove=itemView.findViewById(R.id.removefrmofav);
        }
    }
}
