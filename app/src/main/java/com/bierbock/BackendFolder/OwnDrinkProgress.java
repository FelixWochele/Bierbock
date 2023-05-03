package com.bierbock.BackendFolder;

import com.bierbock.BeerHistory.BeerHistoryItem;
import com.bierbock.HomeFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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

                        BeerHistoryItem item = new BeerHistoryItem(time, productName, brands, imageUrl);

                        beerHistoryItems.add(item);

                        System.out.println(jsonArray.getJSONObject(i).getString("productName"));
                    }

                    homeFragment.updateBeerHistory(beerHistoryItems);

                    //System.out.println("PRODUCT NAME TEST: ");
                    //System.out.println(obj.getString("productName"));
                    // Parse the JSON object
                    //LinkedHashMap<String, Object> resultMap = parseJsonObject(obj);


                    // Process the LinkedHashMap, e.g., update UI or data in the HomeFragment
                    // ...

                } else {
                    // TODO: Implement
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        });

        /*super(fragment.requireActivity().getApplicationContext(), //For the Auth. Token
                "GET",
                "https://www.beerbock.de/BierBock/ownDrinkProgress",
                result -> {

            JSONObject obj;

            obj = new JSONObject(result);

            if ("Successful".equals(obj.getString("statusMessage"))) {

                LinkedHashMap<String, Object> resultMap = parseJsonObject(obj);
                //get the data from the jsonObject....
                //Call the method in the home fragment to fill the beer history items list
                //Call the method to refresh the fragment
            } else {
                // TODO: Implement
            }
        }); */

        String body = ""; //Empty body
        execute(body);
    }
}