package com.bierbock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bierbock.databinding.ActivityLoginBinding;
import com.bierbock.databinding.ActivityUserProfileBinding;

public class UserProfileActivity extends AppCompatActivity {

    private ActivityUserProfileBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityUserProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());





    }
}