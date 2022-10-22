package com.example.online_store.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.online_store.Model.UserResponseModel;
import com.example.online_store.R;
import com.example.online_store.Client.UserClient;
import com.example.online_store.viewModel.postViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {
    TextView signintx;
    EditText Email,password,name;
    AlertDialog alertDialog;
    postViewModel postviewModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
        signintx=findViewById(R.id.signintext);
        Email=findViewById(R.id.editEmail);
        name=findViewById(R.id.editname);
        password=findViewById(R.id.editpass);
        postviewModels = new ViewModelProvider(this).get(postViewModel.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
        builder.setMessage("Creating Account");
        builder.setTitle("Please Wait");
        builder.setCancelable(false);
        alertDialog = builder.create();
        signintx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUp.this, SignIn.class));
            }
        });
    }

    public void SignUP(View view) {
        if(name.getText().toString().isEmpty()||Email.getText().toString().isEmpty()||password.getText().toString().isEmpty())
        {
            Toast.makeText(SignUp.this,"Please enter your data",Toast.LENGTH_SHORT).show();
        }
        else
        {
            alertDialog.show();
            postviewModels.getsignUpFromRepo(name.getText().toString(),Email.getText().toString(),password.getText().toString());
            postviewModels.Postssignup().observe(this, new Observer<UserResponseModel>() {
                @Override
                public void onChanged(UserResponseModel userResponseModel) {
                    if (!userResponseModel.equals("")) {
                        alertDialog.dismiss();
                        if (userResponseModel.getResponse().equals("هذا البريد مسجل من قبل"))
                        {
                            Toast.makeText(SignUp.this, userResponseModel.getResponse(), Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(SignUp.this, userResponseModel.getResponse(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignUp.this, SignIn.class));
                        }
                    }
                    else
                    {
                        alertDialog.dismiss();
                        Toast.makeText(SignUp.this, "try again", Toast.LENGTH_SHORT).show();
                    }
                    finish();
                }
            });
        }
    }
}