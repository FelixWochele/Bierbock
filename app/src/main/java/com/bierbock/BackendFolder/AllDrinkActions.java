package com.bierbock.BackendFolder;

import com.bierbock.MainActivity;

import org.json.JSONObject;

public class AllDrinkActions extends BackendRequest{

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
}
