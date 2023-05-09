package com.bierbock.BackendFolder;

import com.bierbock.DisplayScannedBeerActivity;
import com.bierbock.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;

public class BarcodeData extends BackendRequest{

    private final DisplayScannedBeerActivity activity;

    public BarcodeData(String barcode, DisplayScannedBeerActivity activity) {
        super(activity, "GET", "barcodeData");

        this.activity = activity;


        //Add parameters to url here:
        String[] parameterNames = new String[]{"barcode"};
        String[] parameterValues = new String[]{barcode};

        //important method for adding the parameters to the url:
        super.addUrlParameters(parameterNames, parameterValues);

        execute(""); // empty body
    }

    @Override
    protected void onRequestSuccessful() throws JSONException{

        JSONObject objectResult = obj.getJSONObject("result");

        String product_name = objectResult.getString("product_name");
        String brands = objectResult.getString("brands");
        String imageUrl = objectResult.getString("image_url");
        String quantity = objectResult.getString("quantity");

        //load the image
        activity.loadImageFromURL(imageUrl);
        //Update beer description:
        activity.updateBeerDescription(product_name, brands, quantity);
    }

    @Override
    protected void onRequestFailed() {
        activity.backendFailMessage();
    }
}
