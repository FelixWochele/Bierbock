package com.bierbock.BackendFolder;

import android.os.AsyncTask;

import com.bierbock.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class Login {

    private String url = "https://www.beerbock.de/security/createToken";

    private LoginActivity loginActivity;

    public Login(String username, String password, LoginActivity loginActivity) {

        this.loginActivity = loginActivity;

        String body = String.format("{\"userName\": \"%s\", \"password\": \"%s\"}", username, password);

        AsyncTask<String, String, String> be = new Backend(new TaskDelegate() {
            @Override
            public void onTaskFinishGettingData(String result) throws JSONException {

                JSONObject obj = null;

                obj = new JSONObject(result);

                if("Successful".equals(obj.getString("statusMessage"))){
                    loginActivity.nexActivity();
                }else {
                    loginActivity.errorMsg();
                }
            }
        }).execute(url, body);
    }
}
