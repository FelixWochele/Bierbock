package com.bierbock.BackendFolder;

import com.bierbock.MainActivity;
import com.bierbock.ScanActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;


public class NewDrinkAction extends BackendRequest {

    public NewDrinkAction(String beercode, String latitude, String longitude, String altitude, ScanActivity activity) {
        super(activity.getApplicationContext(),
                "POST",
                "https://www.beerbock.de/BierBock/newDrinkAction");

        setTaskDelegate(result -> {

            JSONObject obj;
            try {
                obj = new JSONObject(result);

                if ("Successful".equals(obj.getString("statusMessage"))) {

                    JSONObject objectResult = obj.getJSONObject("result");

                    //TODO: Call some callback here? (ScanActivity or DisplayScannedBeerActivity action)

                } else {
                    // TODO: Implement
                }

            } catch (JSONException e){
                e.printStackTrace();
            }
        });

        //Add parameters to url here:
        String[] parameterNames = new String[]{"beercode"};
        String[] parameterValues = new String[]{beercode};

        //important method for adding the parameters to the url:
        super.addUrlParameters(parameterNames, parameterValues);

        String body = String.format("{\"latitude\": \"%s\", \"longitude\": \"%s\", \"altitude\": \"%s\"}", latitude, longitude, altitude);

        execute(body); // empty body
    }
}