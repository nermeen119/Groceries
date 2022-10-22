package com.example.online_store.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorites_tables")

public class Fav {
    @PrimaryKey(autoGenerate = true)
    private Long id=0L;
    private String itemTitle;
    private String itemImage;
    private double price;

    public Fav(Long id, String itemTitle, String itemImage, double price) {
        this.id = id;
        this.itemTitle = itemTitle;
        this.itemImage = itemImage;
        this.price=price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
