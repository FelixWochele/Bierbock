package com.bierbock.BackendFolder;

import com.bierbock.BeerHistory.BeerHistoryItem;
import com.bierbock.HomeFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class OwnDrinkProgress extends BackendRequest {

    public OwnDrinkProgress(HomeFragment homeFragment) {
        //Fill the variables in the BackendRequest
        super(homeFragment.requireActivity().getApplicationContext(), //For the Auth. Token
                "GET",
                "https://www.beerbock.de/BierBock/ownDrinkProgress");

        setTaskDelegate(result -> {
            JSONObject obj;

            try {
                obj = new JSONObject(result);

                if ("Successful".equals(obj.getString("statusMessage"))) {
                    JSONArray jsonArray = obj.getJSONArray("result");

                    List<BeerHistoryItem> beerHistoryItems = new ArrayList<>();

                    for (int i = 0; i< jsonArray.length(); i++){

                        String time = jsonArray.getJSONObject(i).getString("time");
                        String productName = jsonArray.getJSONObject(i).getString("productName");
                        String brands = jsonArray.getJSONObject(i).getString("brands");
                        String imageUrl = jsonArray.getJSONObject(i).getString("imageUrl");


                        BeerHistoryItem item = new BeerHistoryItem(time.substring(0,9), productName, brands, imageUrl);

                        beerHistoryItems.add(item);

                        System.out.println(jsonArray.getJSONObject(i).getString("productName"));
                    }

                    homeFragment.updateBeerHistory(beerHistoryItems);


                } else {
                    // TODO: Implement
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        });

        String body = ""; //Empty body
        execute(body);
    }
}