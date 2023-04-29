package com.bierbock.BackendFolder;

import com.bierbock.MainActivity;

import org.json.JSONObject;

public class OwnUserData extends BackendRequest {

    public OwnUserData(MainActivity activity) {
        super("GET", "https://www.beerbock.de/BierBock/ownUserData", result -> {
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
