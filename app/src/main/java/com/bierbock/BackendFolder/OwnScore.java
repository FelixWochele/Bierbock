package com.bierbock.BackendFolder;

import com.bierbock.MainActivity;

import org.json.JSONObject;

public class OwnScore extends BackendRequest {

    public OwnScore(MainActivity activity) {
        super("GET", "https://www.beerbock.de/BierBock/ownScore", result -> {
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
