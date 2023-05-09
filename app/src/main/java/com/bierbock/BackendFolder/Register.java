package com.bierbock.BackendFolder;

import android.app.Activity;
import android.os.AsyncTask;

import com.bierbock.RegisterActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Register extends BackendRequest{

    private final RegisterActivity registerActivity;

    public Register(String username, String firstname, String lastname, String birthdate, String email, String password, RegisterActivity registerActivity) {
        super(registerActivity, "POST", "security/register");

        this.registerActivity = registerActivity;

        String body = String.format("{\"userName\": \"%s\", \"vorname\": \"%s\", \"nachname\": \"%s\", \"password\": \"%s\", \"email\": \"%s\", \"birthdate\": \"%s\"}",
                username, firstname, lastname, password, email, birthdate);
        execute(body);
    }

    @Override
    protected void onRequestSuccessful() throws JSONException, IOException {
        try {
            registerActivity.nextActivity(); // here start the next activity
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onRequestFailed() throws JSONException, IOException {
        registerActivity.errorMsg();
    }
}


/*

public class Register{

    private String url = "https://www.beerbock.de/security/register";

    private RegisterActivity registerActivity;

    public Register(String username, String firstname, String lastname, String birthdate, String email, String password, RegisterActivity registerActivity){

        this.registerActivity = registerActivity;

        String body = String.format("{\"userName\": \"%s\", \"vorname\": \"%s\", \"nachname\": \"%s\", \"password\": \"%s\", \"email\": \"%s\", \"birthdate\": \"%s\"}",
                username, firstname, lastname, password, email, birthdate);

        AsyncTask<String, String, String> be = new Backend("POST", result -> {

            JSONObject obj;

            obj = new JSONObject(result);

            if("Successful".equals(obj.getString("statusMessage"))){

                registerActivity.nextActivity(); // here start the next activity

            }else {
                registerActivity.errorMsg();
            }
        }).execute(url, "", body);

    }
} */