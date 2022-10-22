package com.example.online_store.user;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.online_store.Model.Cart;

import java.util.List;

@Dao
public interface CartDao {
    @Insert
    void insert(Cart cart);

    @Query("DELETE FROM baskets_tables WHERE id=:id")
    void deleteProduct(Long id);

    @Query("SELECT * FROM baskets_tables")
    LiveData<List<Cart>> getAllProducts();
}
