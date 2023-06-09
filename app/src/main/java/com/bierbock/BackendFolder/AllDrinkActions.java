package com.bierbock.BackendFolder;

import com.bierbock.MapsFragment;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;


public class AllDrinkActions extends BackendRequest {

    private final MapsFragment fragment;

    public AllDrinkActions(String searchString, String fromTime, String toTime, MapsFragment fragment) {
        super(fragment, "GET", "allDrinkActions");

        this.fragment = fragment;

        if(!Objects.equals(searchString, "")){
            //Add parameters to url here:
            String[] parameterNames = new String[]{"searchString", "fromTime", "toTime"};
            String[] parameterValues = new String[]{searchString, fromTime, toTime};

            //important method for adding the parameters to the url:
            super.addUrlParameters(parameterNames, parameterValues);
        }

        String body = ""; //Empty body

        execute(body);
    }

    @Override
    protected void onRequestSuccessful() throws JSONException{

        JSONArray resultArray = obj.getJSONArray("result");

        ArrayList<LatLng> locationList = new ArrayList<>();

        for(int i = 0; i < resultArray.length(); i++){

            JSONObject locationTime = resultArray.getJSONObject(i);

            JSONObject location = locationTime.getJSONObject("location");
            double latitude = location.getDouble("latitude");
            double longitude = location.getDouble("longitude");
            double altitude = location.getDouble("altitude");

            String time = locationTime.getString("time");


            locationList.add(new LatLng(latitude, longitude));
        }

        fragment.updateDrinkingCoordinates(locationList);

    }

    @Override
    protected void onRequestFailed() {

    }
}