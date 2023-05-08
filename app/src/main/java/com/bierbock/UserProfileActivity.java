package com.bierbock;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bierbock.BackendFolder.OwnUserData;
import com.bierbock.BackendFolder.UpdateUserPosition;
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


        binding.userProfileSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUserPositionBackend();
            }
        });

        binding.userProfileBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToMain();
            }
        });
    }


    private void updateUserPositionBackend(){
        UpdateUserPosition updateUserPosition = new UpdateUserPosition(this);
    }

    private void backToMain(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}