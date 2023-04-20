package com.bierbock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Bundle;

import com.bierbock.databinding.ActivityLoginBinding;
import com.bierbock.databinding.ActivityMainBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.login.setOnClickListener(e -> {


            AsyncTask be = new Backend().execute();

            //startActivity(new Intent(this, MainActivity.class));
            //finish();
            System.out.println(binding.username.getText().toString());
        });

    }
}