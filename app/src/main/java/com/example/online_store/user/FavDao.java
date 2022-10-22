package com.example.online_store.user;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.online_store.Model.Fav;

import java.util.List;
@Dao
public interface FavDao {
    @Insert
    void insert(Fav fav);

    @Query("DELETE FROM favorites_tables WHERE id=:id")
    void deleteProductWithId(Long id);

    @Query("DELETE FROM favorites_tables WHERE itemTitle=:itemTitle")
    void deleteProductWithName(String itemTitle);

    @Query("SELECT * FROM favorites_tables WHERE itemTitle=:itemTitle")
    LiveData<Fav> getProduct(String itemTitle);

    @Query("SELECT * FROM favorites_tables")
    LiveData<List<Fav>> getAllProducts();
}
