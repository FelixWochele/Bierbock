package com.bierbock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bierbock.BackendFolder.OwnUserData;
import com.bierbock.databinding.ActivityLoginBinding;
import com.bierbock.databinding.ActivityUserProfileBinding;

public class UserProfileActivity extends AppCompatActivity {

    public ActivityUserProfileBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityUserProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Init backend to get User date
        OwnUserData ownUserData = new OwnUserData(this);

        /*
        binding.userProfileBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int = (new Intent(this, MainActivity.class));
                finish();
            }
        });
        */
    }
}