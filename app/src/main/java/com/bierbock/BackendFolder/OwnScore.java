package com.bierbock.BackendFolder;

import com.bierbock.HomeFragment;
import com.bierbock.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;


public class OwnScore extends BackendRequest {

    public OwnScore(HomeFragment homeFragment) {
        //Fill the variables in the BackendRequest
        super(homeFragment.requireActivity().getApplicationContext(), //For the Auth. Token
                "GET",
                "https://www.beerbock.de/BierBock/ownScore");

        setTaskDelegate(result -> {
            JSONObject obj;

            try{
                obj = new JSONObject(result);

                if ("Successful".equals(obj.getString("statusMessage"))) {

                    int score = obj.getInt("result");

                    //homeFragment.updateUserRatings(null);

                } else {
                    // TODO: Implement
                }
            } catch (JSONException e){
                e.printStackTrace();
            }
        });

        String body = ""; //Empty body
        execute(body);
    }
}
