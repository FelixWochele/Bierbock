package com.bierbock.BackendFolder;

import com.bierbock.MainActivity;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//TODO: maybe implement this for the heat map?

/*public class AllDrinkActions extends BackendRequest{

    public AllDrinkActions(String searchString, String fromTime, String toTime, MainActivity activity) {
        super(activity.getApplicationContext(),
                "GET", "https://www.beerbock.de/BierBock/allDrinkActions",
                result -> {
            JSONObject obj;

            obj = new JSONObject(result);

            if ("Successful".equals(obj.getString("statusMessage"))) {
                // TODO: Implement update UI
            } else {
                // TODO: Implement
            }
        });

        String body = String.format("{\"searchString\": \"%s\", \"fromTime\": \"%s\", \"toTime\": \"%s\"}",
                searchString, fromTime, toTime);

        execute(body);
    }
} */
public class AllDrinkActions extends BackendRequest {

    public AllDrinkActions(String searchString, String fromTime, String toTime, MainActivity activity) {
        super(activity.getApplicationContext(),
                "GET",
                "https://www.beerbock.de/BierBock/allDrinkActions");

        setTaskDelegate(result -> {

            try{
                JSONObject obj;

                obj = new JSONObject(result);

                if ("Successful".equals(obj.getString("statusMessage"))) {

                    JSONArray resultArray = obj.getJSONArray("result");

                    List<LatLng> locationList = new ArrayList<>();

                    for(int i = 0; i < resultArray.length(); i++){

                        JSONObject locationTime = resultArray.getJSONObject(i);

                        JSONObject location = locationTime.getJSONObject("location");
                        int latitude = location.getInt("latitude");
                        int longitude = location.getInt("longitude");
                        int altitude = location.getInt("altitude");

                        String time = locationTime.getString("time");

                        //TODO: look if altitude is needed or not
                        locationList.add(new LatLng(latitude, longitude));

                    }

                    //TODO: implement activity access

                } else {
                    // TODO: Implement
                }


            }catch (JSONException e){
                e.printStackTrace();
            }


        });

        //Add parameters to url here:
        String[] parameterNames = new String[]{"searchString", "fromTime", "toTime"};
        String[] parameterValues = new String[]{searchString, fromTime, toTime};

        //important method for adding the parameters to the url:
        super.addUrlParameters(parameterNames, parameterValues);

        String body = ""; //Empty body

        execute(body);
    }
}