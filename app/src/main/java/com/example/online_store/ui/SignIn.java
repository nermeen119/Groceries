package com.example.online_store.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.online_store.Model.UserResponseModel;
import com.example.online_store.R;
import com.example.online_store.SaveData;
import com.example.online_store.Client.UserClient;
import com.example.online_store.fragment.HomeFragment;
import com.example.online_store.viewModel.postViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignIn extends AppCompatActivity {

    TextView signuptx;
    EditText email,pass;
    AlertDialog alertDialog;
    postViewModel postviewModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getSupportActionBar().hide();
        signuptx=findViewById(R.id.signuptext);
        email=findViewById(R.id.editTextTextEmail);
        pass=findViewById(R.id.editTextTextPassword);
        postviewModels = new ViewModelProvider(this).get(postViewModel.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(SignIn.this);
        builder.setMessage("Log in Your account");
        builder.setTitle("Please Wait");
        builder.setCancelable(false);
        alertDialog = builder.create();
        signuptx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignIn.this, SignUp.class));
            }
        });
    }
    public void Login(View view) {

        if(email.getText().toString().isEmpty()||pass.getText().toString().isEmpty())
        {
            Toast.makeText(SignIn.this,"Please enter your data",Toast.LENGTH_SHORT).show();
        }
        else
        {
            alertDialog.show();
            postviewModels.getsignFromRepo(email.getText().toString(),pass.getText().toString());
            postviewModels.PostsSignin().observe(this, new Observer<UserResponseModel>() {
                @Override
                public void onChanged(UserResponseModel userResponseModel) {
                    if (!userResponseModel.equals("")) {
                        alertDialog.dismiss();
                        if (userResponseModel.getResponse().equals("تأكد من البريد او الرقم السرى")) {
                            Toast.makeText(SignIn.this, userResponseModel.getResponse(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SignIn.this, userResponseModel.getResponse(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignIn.this, button_navigate.class));
                            new SaveData(SignIn.this).updateUserStatus(true);
                        }
                    }
                    else
                    {
                        alertDialog.dismiss();
                        Toast.makeText(SignIn.this,"try again",Toast.LENGTH_SHORT).show();
                    }
                }
            });
            SharedPreferences sharedPreferences=getSharedPreferences("names",MODE_PRIVATE);
            SharedPreferences.Editor my_edit=sharedPreferences.edit();
            my_edit.putString("useremail",email.getText().toString());
            my_edit.commit();
        }
    }
}