package com.bierbock.BackendFolder;

import com.bierbock.BeerHistory.BeerHistoryItem;
import com.bierbock.HomeFragment;
import com.bierbock.LoginActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AutomaticLogin extends BackendRequest {


    public AutomaticLogin(LoginActivity loginActivity) {
        //Fill the variables in the BackendRequest
        super(loginActivity,
                "GET",
                "ownDrinkProgress");

        setTaskDelegate(result -> {

            JSONObject obj;

            System.out.println("------------------TEST----------------");
            System.out.println(result);

            try {
                obj = new JSONObject(result);

                if ("Successful".equals(obj.getString("statusMessage"))) {
                    loginActivity.moveToMainActivity();
                    System.out.println("------------------TEST----------------");
                    System.out.println("got right token");
                }else{
                    System.out.println("------------------TEST----------------");
                    System.out.println("cant automatic login");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            loginActivity.toggleViewElements(false);
        });

        String body = ""; //Empty body
        execute(body);
    };
}
