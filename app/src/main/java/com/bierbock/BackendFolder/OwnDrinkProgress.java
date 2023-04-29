package com.bierbock.BackendFolder;

import com.bierbock.MainActivity;
import com.bierbock.R;

import org.json.JSONObject;

public class OwnDrinkProgress extends BackendRequest {

    public OwnDrinkProgress(MainActivity activity) {
        super("GET", "https://www.beerbock.de/BierBock/ownDrinkProgress", result -> {
            JSONObject obj;

            obj = new JSONObject(result);

            if ("Successful".equals(obj.getString("statusMessage"))) {
                // TODO: Implement update UI
            } else {
                // TODO: Implement
            }
        });

        String body = ""; //Empty body

        execute(body);
    }
}