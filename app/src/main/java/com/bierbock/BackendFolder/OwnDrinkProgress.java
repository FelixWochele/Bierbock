package com.bierbock.BackendFolder;

import android.location.Address;
import android.location.Geocoder;

import com.bierbock.BeerHistory.BeerHistoryItem;
import com.bierbock.HomeFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class OwnDrinkProgress extends BackendRequest {

    public OwnDrinkProgress(HomeFragment homeFragment) {
        //Fill the variables in the BackendRequest
        super(homeFragment, "GET","ownDrinkProgress");

        setTaskDelegate(result -> {
            JSONObject obj;

            try {
                obj = new JSONObject(result);

                if ("Successful".equals(obj.getString("statusMessage"))) {
                    JSONArray jsonArray = obj.getJSONArray("result");

                    List<BeerHistoryItem> beerHistoryItems = new ArrayList<>();

                    for (int i = 0; i< jsonArray.length(); i++){

                        String dateTime = jsonArray.getJSONObject(i).getString("time");
                        String productName = jsonArray.getJSONObject(i).getString("productName");
                        String brands = jsonArray.getJSONObject(i).getString("brands");
                        String imageUrl = jsonArray.getJSONObject(i).getString("imageUrl");

                        String timeParsed = dateTime.substring(0,9).replace("-", "/");
                        String dateParsed = dateTime.substring(11,16);

                        //get location
                        JSONObject location = jsonArray.getJSONObject(i).getJSONObject("location");
                        String city = getLocation(location);

                        BeerHistoryItem item = new BeerHistoryItem(timeParsed, productName, brands, dateParsed, city, imageUrl);


                        beerHistoryItems.add(item);

                        System.out.println(jsonArray.getJSONObject(i).getString("productName"));
                    }

                    homeFragment.updateBeerHistory(beerHistoryItems);

                } else {
                    homeFragment.backendErrorMessage();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        String body = ""; //Empty body
        execute(body);
    }

    private String getLocation(JSONObject location) throws JSONException, IOException {


        Double latitude = Double.parseDouble(location.getString("latitude"));
        Double longitude = Double.parseDouble(location.getString("longitude"));


        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(applicationContext, Locale.getDefault());

        addresses = geocoder.getFromLocation(latitude, longitude, 1);

        //System.out.println("-------------TEST---------------");
        //System.out.println(latitude);
        //System.out.println(longitude);


        if(!addresses.isEmpty()){
            return addresses.get(0).getLocality();
        }
        else{
            return "";
        }

    }
}