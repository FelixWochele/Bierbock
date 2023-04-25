package com.bierbock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.bierbock.BackendFolder.Login;
import com.bierbock.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity{

    private ActivityLoginBinding binding;

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.login.setOnClickListener(e -> {

            String usr = binding.username.getText().toString();

            String pwd = binding.password.getText().toString();

            Login l = new Login(usr, pwd, this);
        });

        binding.register.setOnClickListener(e -> {
            nextActivity();
            //startActivity(new Intent(this, RegisterActivity.class));
            //finish();
        });
    }

    public void nextActivity(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void errorMsg(){
        binding.errorMsg.setText("Falsche Anmeldeinformationen!!!");
    }
}