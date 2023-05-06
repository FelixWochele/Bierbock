package com.bierbock.BackendFolder;

import com.bierbock.DisplayScannedBeerActivity;
import com.bierbock.MainActivity;
import com.bierbock.ScanActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;


public class NewDrinkAction extends BackendRequest {

    public NewDrinkAction(String beercode, double[] userLocation, DisplayScannedBeerActivity activity) {
        super(activity.getApplicationContext(),
                "POST",
                "https://www.beerbock.de/BierBock/newDrinkAction");

        setTaskDelegate(result -> {

            JSONObject obj;
            try {
                obj = new JSONObject(result);

                if ("Successful".equals(obj.getString("statusMessage"))) {

                    activity.goToMainActivity(); //Move to main activity after that

                } else {
                    activity.backendFailMessage();
                }

            } catch (JSONException e){
                e.printStackTrace();
            }
        });

        //Add parameters to url here:
        String[] parameterNames = new String[]{"beerCode"};
        String[] parameterValues = new String[]{beercode};

        //important method for adding the parameters to the url:
        super.addUrlParameters(parameterNames, parameterValues);

        //Locale.US very important, because of "." for the double values
        String body = String.format(Locale.US, "{\"latitude\": %.14f, \"longitude\": %.14f, \"altitude\": %.14f}", userLocation[0], userLocation[1], userLocation[2]);

        execute(body); // empty body
    }
}