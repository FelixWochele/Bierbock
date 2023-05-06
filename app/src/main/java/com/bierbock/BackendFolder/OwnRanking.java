package com.bierbock.BackendFolder;

import com.bierbock.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;


public class OwnRanking extends BackendRequest {

    public OwnRanking(MainActivity activity) {
        super(activity.getApplicationContext(),
                "GET",
                "https://www.beerbock.de/BierBock/ownRanking");

        setTaskDelegate(result -> {

            try{
                JSONObject obj;

                obj = new JSONObject(result);

                if ("Successful".equals(obj.getString("statusMessage"))) {

                    JSONObject objectResult = obj.getJSONObject("result");

                    String rank = objectResult.getString("rank");
                    String userCount = objectResult.getString("userCount");

                    //TODO: implement activity access

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