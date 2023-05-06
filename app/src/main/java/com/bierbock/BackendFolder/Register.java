package com.bierbock.BackendFolder;

import android.os.AsyncTask;

import com.bierbock.RegisterActivity;

import org.json.JSONObject;

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
}