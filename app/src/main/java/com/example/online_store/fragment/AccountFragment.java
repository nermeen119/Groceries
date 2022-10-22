package com.example.online_store.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.online_store.R;
import com.example.online_store.ui.SignIn;

import static android.content.Context.MODE_PRIVATE;


public class AccountFragment extends Fragment {
    Button logOut;
    TextView nameUser,emailUser;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_account, container, false);
        logOut=v.findViewById(R.id.btlogout);
        nameUser=v.findViewById(R.id.nameusers);
        emailUser=v.findViewById(R.id.emailusers);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity().getApplicationContext(), SignIn.class));
                getActivity().finish();
            }
        });
        SharedPreferences preferences=getContext().getSharedPreferences("names", MODE_PRIVATE);
        String mail=preferences.getString("useremail","");
        emailUser.setText(mail);
        return v;
    }
}