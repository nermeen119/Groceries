package com.example.online_store.ui;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.online_store.R;
import com.example.online_store.fragment.AccountFragment;
import com.example.online_store.fragment.CartFragment;
import com.example.online_store.fragment.ExploreFragment;
import com.example.online_store.fragment.HomeFragment;
import com.example.online_store.fragment.favouriteFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class button_navigate extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_navigate);
        bottomNavigationView = findViewById(R.id.nav_bottom);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
      //  bottomNavigationView.setSelectedItemId(R.id.shopbar);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.bottom_container,new HomeFragment()).commit();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.shopbar:
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.bottom_container,new HomeFragment()).commit();

                return true;

            case R.id.cartbar:
                FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction2.replace(R.id.bottom_container,new CartFragment()).commit();
                return true;

            case R.id.explorbar:
                FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction3.replace(R.id.bottom_container,new ExploreFragment()).commit();
                return true;
            case R.id.accountbar:
                FragmentTransaction fragmentTransaction4 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction4.replace(R.id.bottom_container,new AccountFragment()).commit();
                return true;
            case R.id.favbar:
                FragmentTransaction fragmentTransaction5= getSupportFragmentManager().beginTransaction();
                fragmentTransaction5.replace(R.id.bottom_container,new favouriteFragment()).commit();
                return true;
        }
        return false;
    }
}