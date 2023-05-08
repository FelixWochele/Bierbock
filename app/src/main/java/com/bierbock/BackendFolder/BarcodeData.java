package com.bierbock.BackendFolder;

import com.bierbock.DisplayScannedBeerActivity;
import com.bierbock.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;

public class BarcodeData extends BackendRequest{

    public BarcodeData(String barcode, DisplayScannedBeerActivity activity) {
        super(activity, "GET", "barcodeData");

        setTaskDelegate(result -> {

            JSONObject obj;

            try{
                obj = new JSONObject(result);

                if ("Successful".equals(obj.getString("statusMessage"))) {
                    JSONObject objectResult = obj.getJSONObject("result");

                    String product_name = objectResult.getString("product_name");
                    String brands = objectResult.getString("brands");
                    String imageUrl = objectResult.getString("image_url");
                    String quantity = objectResult.getString("quantity");

                    //load the image
                    activity.loadImageFromURL(imageUrl);
                    //Update beer description:
                    activity.updateBeerDescription(product_name, brands, quantity);
                } else {
                    activity.backendFailMessage();
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
