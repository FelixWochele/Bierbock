package com.bierbock.BackendFolder;

import com.bierbock.MainActivity;

import org.json.JSONObject;

public class NewDrinkAction extends BackendRequest {

    public NewDrinkAction(String beercode, MainActivity activity) {
        super("POST", "https://www.beerbock.de/BierBock/newDrinkAction", result -> {
            JSONObject obj;

            obj = new JSONObject(result);

            if ("Successful".equals(obj.getString("statusMessage"))) {
                // TODO: Implement update UI
            } else {
                // TODO: Implement
            }
        });

        String body = String.format("{\"beercode\": \"%s\"}", beercode);

        execute(body);
    }
}