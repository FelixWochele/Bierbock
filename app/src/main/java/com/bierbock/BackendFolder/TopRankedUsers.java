package com.bierbock.BackendFolder;

import com.bierbock.HomeFragment;
import com.bierbock.UserRating.UserRanking;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class TopRankedUsers extends BackendRequest {

    public TopRankedUsers(HomeFragment homeFragment) {

        super(homeFragment, "GET", "topRankedUsers");

        setTaskDelegate(result -> {
            JSONObject obj;

            try {
                obj = new JSONObject(result);

                if ("Successful".equals(obj.getString("statusMessage"))) {
                    JSONObject objectResult = obj.getJSONObject("result");

                    List<UserRanking> userRankings = new ArrayList<>();

                    // Get the top 25 values
                    JSONArray top25Array = objectResult.getJSONArray("top25");
                    for (int i = 0; i < top25Array.length(); i++) {
                        JSONObject userObject = top25Array.getJSONObject(i);
                        int rank = userObject.getInt("rank");
                        String userName = userObject.getString("userName");
                        int points = userObject.getInt("points");

                        userRankings.add(new UserRanking(userName, rank, points));
                        System.out.println("Rank: " + rank + ", Username: " + userName + ", Points: " + points);
                    }

                    // Get the own values
                    JSONObject ownObject = objectResult.getJSONObject("own");
                    int ownRank = ownObject.getInt("rank");
                    int ownPoints = ownObject.getInt("points");
                    String ownUserName = ownObject.getString("userName");

                    System.out.println("Own Rank: " + ownRank + ", Own Username: " + ownUserName + ", Own Points: " + ownPoints);

                    homeFragment.updateUserRankings(userRankings, ownUserName, ownRank, ownPoints);

                } else {
                    homeFragment.backendErrorMessage();
                }
            } catch (JSONException e){
                e.printStackTrace();
            }

        });

        String body = ""; //Empty body

        execute(body);
    }
}
