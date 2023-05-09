package com.bierbock.BackendFolder;

import com.bierbock.DisplayScannedBeerActivity;
import com.bierbock.MainActivity;
import com.bierbock.ScanActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.UnknownServiceException;
import java.util.Locale;


public class NewDrinkAction extends BackendRequest {

    private final DisplayScannedBeerActivity activity;

    public NewDrinkAction(String beercode, double[] userLocation, DisplayScannedBeerActivity activity) {
        super(activity, "POST","newDrinkAction");

        this.activity = activity;


        //Add parameters to url here:
        String[] parameterNames = new String[]{"beerCode"};
        String[] parameterValues = new String[]{beercode};

        //important method for adding the parameters to the url:
        super.addUrlParameters(parameterNames, parameterValues);

        /*
        if(userLocation == null){
            userLocation = new double[3];
            userLocation[0] = 10.0;
            userLocation[1] = 10.0;
            userLocation[2] = 10.0;
        } */

        //Locale.US very important, because of "." for the double values
        String body = String.format(Locale.US, "{\"latitude\": %.14f, \"longitude\": %.14f, \"altitude\": %.14f}", userLocation[0], userLocation[1], userLocation[2]);

        execute(body); // empty body
    }

    @Override
    protected void onRequestSuccessful() throws JSONException, IOException {
        activity.goToMainActivity(); //Move to main activity after that
    }

    @Override
    protected void onRequestFailed() throws JSONException, IOException {
        activity.backendFailMessage();
    }
}