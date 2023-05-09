package com.bierbock.BackendFolder;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import com.bierbock.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class Login extends BackendRequest{

    private final LoginActivity loginActivity;

    public Login(String username, String password, LoginActivity loginActivity) {
        super(loginActivity, "POST", "security/createToken");

        this.loginActivity = loginActivity;

        String body = String.format("{\"userName\": \"%s\", \"password\": \"%s\"}", username, password);
        execute(body);
    }

    @Override
    protected void onRequestSuccessful() throws JSONException, IOException {
        //Save the token here:
        String authToken = obj.getString("result");

        //Encrypt the token and save it in sharedPreferences file
        try {
            SharedPreferences sharedPreferences = EncryptedSharedPreferences.create(
                    "authentication_key",
                    MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
                    loginActivity,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("token_key", authToken);
            editor.apply();

            loginActivity.moveToMainActivity(); // here start the next activity
        }
        catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onRequestFailed() throws JSONException, IOException {
        loginActivity.errorMsg();
    }
}


/*
public class Login{

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

                //Encrypt the token and save it in sharedPreferences file
                try {
                    SharedPreferences sharedPreferences = EncryptedSharedPreferences.create(
                            "authentication_key",
                            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
                            loginActivity,
                            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                    );

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("token_key", authToken);
                    editor.apply();

                    loginActivity.moveToMainActivity(); // here start the next activity
                } catch (GeneralSecurityException | IOException e) {
                    e.printStackTrace();
                }
            }else {
                loginActivity.errorMsg();
            }
        }).execute(url, "", body);
    }
} */
