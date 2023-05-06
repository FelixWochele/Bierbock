package com.bierbock.BackendFolder;

import com.bierbock.UserProfileActivity;

import org.json.JSONException;
import org.json.JSONObject;


public class UpdateUserPosition extends BackendRequest {

    public UpdateUserPosition(UserProfileActivity activity) {

        super(activity.getApplicationContext(), //For the Auth. Token
                "POST",
                "https://www.beerbock.de/BierBock/actualisateUserBasicData");


        setTaskDelegate(result -> {

            JSONObject obj;

            try{
               obj = new JSONObject(result);

            } catch (JSONException e){
                e.printStackTrace();
            }
        });

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
}