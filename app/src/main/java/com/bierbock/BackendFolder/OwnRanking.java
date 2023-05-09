package com.bierbock.BackendFolder;

import com.bierbock.ChallengeFragment;
import com.bierbock.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;


public class OwnRanking extends BackendRequest {
    private final ChallengeFragment challengeFragment;

    public OwnRanking(ChallengeFragment fragment) {
        //Initialize backend and callback:
        super(fragment, "GET","ownRanking");
        //safe a reference to a fragment for calling the functions
        challengeFragment = fragment;


        String body = ""; //Empty body

        execute(body);
    }

    @Override
    protected void onRequestSuccessful() {
        try{
            JSONObject objectResult = obj.getJSONObject("result");

            int rank = objectResult.getInt("rank");
            int userCount = objectResult.getInt("userCount");

            challengeFragment.updateOwnRanking(rank, userCount);
        } catch(JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onRequestFailed() {
        //call other functions
    }
}