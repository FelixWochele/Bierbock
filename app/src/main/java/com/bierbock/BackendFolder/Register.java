package com.bierbock.BackendFolder;

import android.os.AsyncTask;

import com.bierbock.MainActivity;
import com.bierbock.RegisterActivity;

import org.json.JSONObject;
public class Register extends BackendRequest{


    //TODO: change so that it doesn't use the backendRequest class, because that class gets a token

    public Register(String username, String firstname, String lastname, String birthdate, String email, String password, RegisterActivity registerActivity){
        super(registerActivity.getApplicationContext(),
                "POST", "https://www.beerbock.de/security/register",
                result -> {

            JSONObject obj;

            obj = new JSONObject(result);

            if("Successful".equals(obj.getString("statusMessage"))){
                registerActivity.nexActivity();
            }else {
                registerActivity.errorMsg();
            }
        });

        String body = String.format("{\"userName\": \"%s\", \"vorname\": \"%s\", \"nachname\": \"%s\", \"password\": \"%s\", \"email\": \"%s\", \"birthdate\": \"%s\"}",
            username, firstname, lastname, password, email, birthdate);

        execute(body);
    }
}