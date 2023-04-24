package com.bierbock.BackendFolder;

import android.os.AsyncTask;

import com.bierbock.LoginActivity;

import java.util.concurrent.ExecutionException;

public class Login {

    private String url = "https://www.beerbock.de/security/createToken";

    private LoginActivity loginActivity;

    public Login(String username, String password, LoginActivity loginActivity) {

        this.loginActivity = loginActivity;

        String body = String.format("{\"userName\": \"%s\", \"password\": \"%s\"}", username, password);

        AsyncTask<String, String, String> be = new Backend(new TaskDelegate() {
            @Override
            public void onTaskFinishGettingData(String result) {

                System.out.println("FICKEN!");
            }
        }).execute(url, body);
    }
}
