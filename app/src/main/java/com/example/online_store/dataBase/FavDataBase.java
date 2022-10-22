package com.example.online_store.dataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.online_store.Model.Fav;
import com.example.online_store.user.FavDao;
@Database(entities = {Fav.class},version = 1)

public abstract class FavDataBase extends RoomDatabase {
    private static FavDataBase instance;
    public abstract FavDao Deo();
    public static FavDataBase getInstance(Context context)
    {
        if(instance==null)
        {
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    FavDataBase.class,"favorites_database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
