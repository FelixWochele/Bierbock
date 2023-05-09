package com.bierbock.BackendFolder;

import com.bierbock.Challenge.Challenge;
import com.bierbock.MainActivity;
import com.bierbock.UserProfileActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class OwnUserData extends BackendRequest {

    private final UserProfileActivity activity;

    public OwnUserData(UserProfileActivity activity) {
        super(activity, "GET","ownUserData");

        this.activity = activity;


        execute("");
    }

    @Override
    protected void onRequestSuccessful() throws JSONException, IOException {

        JSONObject res = obj.getJSONObject("result");

        String username = res.getString("userName");
        String firstname = res.getString("vorName");
        String lastname = res.getString("name");
        String birthdate = res.getString("birthDate");
        String email = res.getString("email");

        activity.binding.userName.setText(username);
        activity.binding.firstname.setText(firstname);
        activity.binding.lastname.setText(lastname);
        activity.binding.email.setText(email);
        activity.binding.birthdate.setText(birthdate);

        System.out.println(obj.toString());
    }

    @Override
    protected void onRequestFailed() throws JSONException, IOException {

    }
}
