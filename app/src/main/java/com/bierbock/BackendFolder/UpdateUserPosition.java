package com.bierbock.BackendFolder;

import com.bierbock.UserProfileActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class UpdateUserPosition extends BackendRequest {

    //private final UserProfileActivity activity;

    public UpdateUserPosition(UserProfileActivity activity) {
        super(activity, "POST", "actualisateUserBasicData");

        //this.activity = activity;

        String username = activity.binding.userName.getText().toString();
        String firstname = activity.binding.firstname.getText().toString();
        //String lastname = activity.binding.lastname.getText().toString();
        String birthdate = activity.binding.birthdate.getText().toString();
        String email = activity.binding.email.getText().toString();

        String[] parameterNames = new String[]{"newName", "newVorname", "newBirthDate", "newMail"};
        String[] parameterValues = new String[]{username, firstname, birthdate, email};

        //important method for adding the parameters to the url:
        super.addUrlParameters(parameterNames, parameterValues);

        System.out.println(super.url);

        execute("");
    }

    @Override
    protected void onRequestSuccessful() throws JSONException, IOException {
        //No implementation here...
    }

    @Override
    protected void onRequestFailed() throws JSONException, IOException {

    }
}