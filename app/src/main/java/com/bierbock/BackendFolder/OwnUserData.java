package com.bierbock.BackendFolder;

import com.bierbock.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class OwnUserData extends BackendRequest {

    public OwnUserData(MainActivity activity) {
        super(activity.getApplicationContext(),
                "GET",
                "https://www.beerbock.de/BierBock/ownUserData");

        setTaskDelegate(result -> {

            try{
                JSONObject obj;

                obj = new JSONObject(result);

                if ("Successful".equals(obj.getString("statusMessage"))) {

                    JSONObject objectResult = obj.getJSONObject("result");

                    String userName = objectResult.getString("userName");
                    String name = objectResult.getString("name");
                    String vorName = objectResult.getString("vorName");
                    String birthDate = objectResult.getString("birthDate");
                    String email = objectResult.getString("email");
                    //NOT NEEDED:
                    //String location = object.getString("location");
                    //String wohnort = object.getString("wohnort");
                    //String productName = object.getString("productName");

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
