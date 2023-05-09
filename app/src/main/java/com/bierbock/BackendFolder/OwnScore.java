package com.bierbock.BackendFolder;

import com.bierbock.ChallengeFragment;
import com.bierbock.HomeFragment;
import com.bierbock.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;


public class OwnScore extends BackendRequest {

    private final ChallengeFragment challengeFragment;

    public OwnScore(ChallengeFragment challengeFragment) {
        //Fill the variables in the BackendRequest
        super(challengeFragment, "GET", "ownScore");

        this.challengeFragment = challengeFragment;


        String body = ""; //Empty body
        execute(body);
    }

    @Override
    protected void onRequestSuccessful() throws JSONException {
        int score = obj.getInt("result");

        challengeFragment.updateOwnScore(score);
    }

    @Override
    protected void onRequestFailed() {

    }
}
