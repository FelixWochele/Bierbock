package com.bierbock.BackendFolder;

import com.bierbock.ChallengeFragment;
import com.bierbock.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;


public class OwnRanking extends BackendRequest {

    public OwnRanking(ChallengeFragment fragment) {
        super(fragment, "GET","ownRanking");

        setTaskDelegate(result -> {

            try{
                JSONObject obj;

                obj = new JSONObject(result);

                if ("Successful".equals(obj.getString("statusMessage"))) {

                    JSONObject objectResult = obj.getJSONObject("result");

                    int rank = objectResult.getInt("rank");
                    int userCount = objectResult.getInt("userCount");

                    fragment.updateOwnRanking(rank, userCount);

                } else {
                    // TODO: Implement
                }


            }catch (JSONException e){
                e.printStackTrace();
            }


        });

        String body = ""; //Empty body

        execute(body);
    }
}