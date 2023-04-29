package com.bierbock;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bierbock.BackendFolder.Register;
import com.bierbock.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.signUp.setOnClickListener(e -> {
            String username = binding.username.getText().toString();
            String firstname = binding.firstname.getText().toString();
            String lastname = binding.lastname.getText().toString();
            String email = binding.emailAdress.getText().toString();
            String password = binding.password.getText().toString();
            String birthdate = binding.birthdate.getText().toString();

            Register r = new Register(username, firstname, lastname, birthdate, email, password, this);
        });
    }
    public void nexActivity() throws InterruptedException {

        Toast.makeText(this, "Erfolgreiche Anmeldung! Check Deine Emails!", Toast.LENGTH_SHORT).show();
        Thread.sleep(2000);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    public void errorMsg(){
        binding.errorMsg.setText("Falsche Anmeldeinformationen!!!");
    }


}
