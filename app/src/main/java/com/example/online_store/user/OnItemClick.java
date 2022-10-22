package com.example.online_store.user;

import com.example.online_store.Adapter.CustomAdapter;
import com.example.online_store.Model.jeweleryModel;

public interface OnItemClick {
    void onClick(String customAdapter);
    void onClickElectro(String customAdapter);
    void onClickMan(String customAdapter);
    void onClickWoman(String customAdapter);

}
