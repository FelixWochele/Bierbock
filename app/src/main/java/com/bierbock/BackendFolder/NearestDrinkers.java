package com.bierbock.BackendFolder;

import com.bierbock.MainActivity;

import org.json.JSONObject;

/*
public class NearestDrinkers extends BackendRequest{

    public NearestDrinkers(String latitude, String longitude, String altitude, String beerCode, MainActivity activity) {
        super("GET", "https://www.beerbock.de/BierBock/nearestDrinkers", result -> {
            JSONObject obj;

            obj = new JSONObject(result);

            if ("Successful".equals(obj.getString("statusMessage"))) {
                // TODO: Implement update UI
            } else {
                // TODO: Implement
            }
        });

        String body = String.format("{\"latitude\": \"%s\", \"longitude\": \"%s\", \"altitude\": \"%s\", \"beerCode\": \"%s\"}",
                latitude, longitude, altitude, beerCode);

        execute(body);
    }
} */