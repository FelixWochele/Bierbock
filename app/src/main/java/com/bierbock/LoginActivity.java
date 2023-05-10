package com.bierbock;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bierbock.BackendFolder.AutomaticLogin;
import com.bierbock.BackendFolder.Login;
import com.bierbock.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity{

    private ActivityLoginBinding binding;

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);

        boolean connected = (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED);

        if(!connected){
            binding.errorMsg.setText("Internet connection needed! Pleas restart!");
            toggleViewElements(false);
            return;
        }

        toggleViewElements(true);

        AutomaticLogin autoLogin = new AutomaticLogin(this);

        binding.login.setOnClickListener(e -> {

            String usr = binding.username.getText().toString();
            String pwd = binding.password.getText().toString();

            Login l = new Login(usr, pwd, this);
        });

        binding.register.setOnClickListener(e -> {
            moveToRegisterActivity();
            //startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }

    public void moveToRegisterActivity(){
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }

    public void moveToMainActivity(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void errorMsg(){
        binding.errorMsg.setText("Falsche Anmeldeinformationen!!!");
    }


    //Toggle the state of View Elements on the home view:
    public void toggleViewElements(boolean state){

        ProgressBar progressBar = binding.loginProgressBar;

        int visibility = state ? View.VISIBLE : View.INVISIBLE;

        progressBar.setVisibility(visibility);
    }
}