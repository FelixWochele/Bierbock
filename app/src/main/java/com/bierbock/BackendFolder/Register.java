package com.bierbock.BackendFolder;

import android.os.AsyncTask;

import com.bierbock.RegisterActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class Register {


    private String username;
    private String firstname;
    private String lastname;

    private String birthdate;
    private String email;
    private String password;

    private RegisterActivity registerActivity;

    private String url = "https://www.beerbock.de/security/register";


    public Register(String username, String firstname, String lastname, String birthdate, String email, String password, RegisterActivity registerActivity){
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;

        this.registerActivity = registerActivity;

        String body = "";


        AsyncTask<String, String, String> be = new Backend(new TaskDelegate() {
            @Override
            public void onTaskFinishGettingData(String result) throws JSONException, InterruptedException {

                JSONObject obj = null;

                obj = new JSONObject(result);

                if("Successful".equals(obj.getString("statusMessage"))){
                    registerActivity.nexActivity();
                }else {
                    registerActivity.errorMsg();
                }
            }
        }).execute(url, body);
    }






}
