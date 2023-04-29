package com.bierbock.BackendFolder;

import android.content.SharedPreferences;
import android.os.AsyncTask;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import com.bierbock.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.Cleaner;
import java.security.GeneralSecurityException;

public class Login {

    private String url = "https://www.beerbock.de/security/createToken";

    private LoginActivity loginActivity;

    public Login(String username, String password, LoginActivity loginActivity) {

        this.loginActivity = loginActivity;

        String body = String.format("{\"userName\": \"%s\", \"password\": \"%s\"}", username, password);

        AsyncTask<String, String, String> be = new Backend("POST", result -> {

            JSONObject obj;

            obj = new JSONObject(result);

            if("Successful".equals(obj.getString("statusMessage"))){
                //Save the token here:
                String authToken = obj.getString("result");

                //Encrypt the token
                try {
                    SharedPreferences sharedPreferences = EncryptedSharedPreferences.create(
                            "your_preference_name",
                            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
                            loginActivity,
                            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                    );

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("token_key", authToken);
                    editor.apply();

                    loginActivity.nextActivity(); // here start the next activity
                } catch (GeneralSecurityException | IOException e) {
                    e.printStackTrace();
                }
            }else {
                loginActivity.errorMsg();
            }
        }).execute(url, body);
    }
}