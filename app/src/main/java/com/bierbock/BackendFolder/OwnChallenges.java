package com.bierbock.BackendFolder;

import com.bierbock.ChallengeFragment;
import com.bierbock.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;


public class OwnChallenges extends BackendRequest{

    public OwnChallenges(ChallengeFragment challengeFragment) {
        super(challengeFragment.requireActivity().getApplicationContext(),
                "GET",
                "https://www.beerbock.de/BierBock/ownChallenges");

        setTaskDelegate(result -> {

            JSONObject obj;

            try {
                obj = new JSONObject(result);

                if ("Successful".equals(obj.getString("statusMessage"))) {
                    // TODO: Implement update UI
                    // TODO: Update the challenges (like in the other fragment)
                } else {
                    // TODO: Implement
                }

            } catch (JSONException e){
                e.printStackTrace();
            }
        });

        String body = ""; //empty body
        execute(body);
    }
}
