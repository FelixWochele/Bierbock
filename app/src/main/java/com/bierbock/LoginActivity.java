package com.bierbock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;

import com.bierbock.databinding.ActivityLoginBinding;
import com.bierbock.databinding.ActivityMainBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //TODO: add login logic here
        binding.login.setOnClickListener(e -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        binding.register.setOnClickListener(e -> {
            startActivity(new Intent(this, RegisterActivity.class));
            finish();
        });
    }
}