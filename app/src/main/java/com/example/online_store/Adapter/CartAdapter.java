package com.example.online_store.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.online_store.Model.Cart;
import com.example.online_store.Model.Fav;
import com.example.online_store.R;
import com.example.online_store.user.RemoveClick;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CartAdapter  extends RecyclerView.Adapter<CartAdapter.DataHolder> {

    List<Cart> list;
    RemoveClick onItemClick;
    boolean found=false;
    public CartAdapter(RemoveClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @NonNull
    @NotNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new DataHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.save_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DataHolder holder, int position) {
        Picasso.get().load(list.get(position).getItemImage())
                .into(holder.image);
        holder.productName.setText(list.get(position).getItemTitle());
        holder.price.setText("$"+list.get(position).getPrice());

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onClick(list.get(position).getId());
            }
        });
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int addresult=  Integer.parseInt(holder.txresults.getText().toString());
                addresult+=1;
                if(addresult<10)
                {
                    holder.txresults.setText("0"+addresult+"");
                }
                else
                {
                    holder.txresults.setText(addresult+"");
                }
            }
        });
        holder.sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int subresult=  Integer.parseInt(holder.txresults.getText().toString());
                if(subresult>0)

                    subresult-=1;
                if(subresult<10)
                {
                    holder.txresults.setText("0"+subresult+"");
                    found=true;
                }
                else
                {
                    holder.txresults.setText(subresult+"");
                    found=true;

                }
                if(subresult==0&&found==false)
                {
                    holder.sub.setClickable(false);
                    found=false;
                }

            }
        });
    }

    public void setList(List<Cart>list)
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
        TextView productName,price,txresults;
        ImageButton remove,add,sub;
        public DataHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.imageView2);
            productName=itemView.findViewById(R.id.textView3);
            price=itemView.findViewById(R.id.priceitem);
            remove=itemView.findViewById(R.id.button);
            add=itemView.findViewById(R.id.additems);
            sub=itemView.findViewById(R.id.subitems);
            txresults=itemView.findViewById(R.id.txres);
        }
    }
}
