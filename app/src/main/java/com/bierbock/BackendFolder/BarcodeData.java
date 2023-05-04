package com.bierbock.BackendFolder;

import com.bierbock.DisplayScannedBeerActivity;
import com.bierbock.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;

public class BarcodeData extends BackendRequest{

    public BarcodeData(String barcode, DisplayScannedBeerActivity activity) {
        super(activity.getApplicationContext(), //For the Auth. Token
                "GET",
                "https://www.beerbock.de/BierBock/barcodeData");

        setTaskDelegate(result -> {

            JSONObject obj;

            try{
                obj = new JSONObject(result);

                if ("Successful".equals(obj.getString("statusMessage"))) {
                    JSONObject objectResult = obj.getJSONObject("result");

                    String imageUrl = objectResult.getString("image_url");

                    activity.loadImageFromURL(imageUrl);

                } else {
                    // TODO: Implement
                }
            } catch (JSONException e){
                e.printStackTrace();
            }
        });

        //Add parameters to url here:
        String[] parameterNames = new String[]{"barcode"};
        String[] parameterValues = new String[]{barcode};

        //important method for adding the parameters to the url:
        super.addUrlParameters(parameterNames, parameterValues);

        execute(""); // empty body
    }
}
