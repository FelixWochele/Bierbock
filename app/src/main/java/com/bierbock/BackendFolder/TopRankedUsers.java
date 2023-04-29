package com.bierbock.BackendFolder;

import com.bierbock.MainActivity;

import org.json.JSONObject;

public class TopRankedUsers extends BackendRequest {

    public TopRankedUsers(MainActivity activity) {
        super("GET", "https://www.beerbock.de/BierBock/topRankedUsers", result -> {
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
