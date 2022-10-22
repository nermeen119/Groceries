package com.example.online_store.ui;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.online_store.Model.Cart;
import com.example.online_store.Model.Fav;
import com.example.online_store.Model.jeweleryModel;
import com.example.online_store.R;
import com.example.online_store.dataBase.CartDataBase;
import com.example.online_store.dataBase.FavDataBase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Details extends AppCompatActivity {

    String titles;
    String images;
    double rating;
    String description;
    double prices;
    boolean flag=false;
    boolean arrow=false;
    boolean found=false;
    ImageButton add,sub;
    ImageButton imgfav;
    TextView title,rate,price,descriptionss,countsresult;
    ImageView image,drop_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().hide();
        title=findViewById(R.id.detailtiltle);
        price=findViewById(R.id.detailprice);
        descriptionss=findViewById(R.id.descriptions);
        image=findViewById(R.id.deatailimage);
        drop_image=findViewById(R.id.imagedrop);
        add=findViewById(R.id.addimage);
        sub=findViewById(R.id.subimage);
        countsresult=findViewById(R.id.txresult);
        imgfav=findViewById(R.id.add_fav);

        drop_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(arrow==true)
                {
                    drop_image.setImageResource(R.drawable.dwon);
                    descriptionss.setText(description);
                    arrow=false;
                }
                else
                {
                    drop_image.setImageResource(R.drawable.uparrow);
                    descriptionss.setText("");
                    arrow=true;
                }

            }
        });
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null)
        {
            titles=bundle.getString("title");
            prices =bundle.getDouble("price");
            description=bundle.getString("description");
            images=bundle.getString("image");
            title.setText(titles);
            price.setText("$"+prices);
            descriptionss.setText(description);
            Picasso.get().load(images)
                    .into(image);
        }
    }

    public void adds(View view) {
        int addresult=  Integer.parseInt(countsresult.getText().toString());
        addresult+=1;
        if(addresult<10)
        {
            countsresult.setText("0"+addresult+"");
        }
        else
        {
            countsresult.setText(addresult+"");
        }
    }

    public void subs(View view) {
        int subresult=  Integer.parseInt(countsresult.getText().toString());
        if(subresult>0)

            subresult-=1;
        if(subresult<10)
        {
            countsresult.setText("0"+subresult+"");
            found=true;
        }
        else
        {
            countsresult.setText(subresult+"");
            found=true;

        }
        if(subresult==0&&found==false)
        {
            sub.setClickable(false);
            found=false;
        }
    }

    public void arr_toFav(View view) {
        if(flag==true)
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    FavDataBase.getInstance(Details.this).Deo().deleteProductWithName(titles);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Details.this, "Removed from favourite", Toast.LENGTH_SHORT).show();
                            //imgfav.setBackgroundColor(Color.parseColor("#CDCED3"));
                            imgfav.setImageResource(R.drawable.heart2);
                            flag=false;
                        }
                    });

                }
            }).start();
        }
        else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    FavDataBase.getInstance(Details.this).Deo().insert(new Fav(null, titles, images, prices));
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imgfav.setImageResource(R.drawable.heart3);
                            flag=true;
                            Toast.makeText(Details.this, "Added to favourite", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }).start();

        }

    }
    public void add_Cart(View view) {

        Cart cart=new Cart(null,titles,images,prices);
        try{
            new Thread(new Runnable() {
                @Override
                public void run() {
                    CartDataBase.getInstance(Details.this).Deo().insert(cart);
                }
            }).start();
            Toast.makeText(Details.this, "Added to basket", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(Details.this, "error while insert", Toast.LENGTH_SHORT).show();
        }
    }
}