package com.bierbock.BackendFolder;

import com.bierbock.ChallengeFragment;
import com.bierbock.HomeFragment;
import com.bierbock.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;


public class OwnScore extends BackendRequest {

    public OwnScore(ChallengeFragment challengeFragment) {
        //Fill the variables in the BackendRequest
        super(challengeFragment, "GET", "ownScore");

        setTaskDelegate(result -> {
            JSONObject obj;

            try{
                obj = new JSONObject(result);

                if ("Successful".equals(obj.getString("statusMessage"))) {

                    int score = obj.getInt("result");

                    challengeFragment.updateOwnScore(score);

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
