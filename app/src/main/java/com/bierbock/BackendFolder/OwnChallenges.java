package com.bierbock.BackendFolder;

import com.bierbock.ChallengeFragment;
import com.bierbock.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class OwnChallenges extends BackendRequest{

    public OwnChallenges(ChallengeFragment challengeFragment) {
        super(challengeFragment.requireActivity().getApplicationContext(),
                "GET",
                "https://www.beerbock.de/BierBock/ownChallenges");

        setTaskDelegate(result -> {

            JSONObject obj;

            try {
                obj = new JSONObject(result);

                if ("Successful".equals(obj.getString("statusMessage"))) {

                    JSONArray resultArray = new JSONArray(obj.getJSONArray("result"));

                    for(int i = 0; i < resultArray.length(); i++){
                        JSONObject challengeProgress = resultArray.getJSONObject(i);

                        //challenge object:

                        JSONObject challenge = challengeProgress.getJSONObject("challenge");
                        int possiblePoints = challenge.getInt("possiblePoints");
                        String description = challenge.getString("description");
                        String startDate = challenge.getString("startDate");
                        String endDate = challenge.getString("endDate");
                        boolean isActive = challenge.getBoolean("isActive");
                        int neededQuantity = challenge.getInt("neededQuantity");
                        String searchString = challenge.getString("searchString");

                        //progress object:
                        JSONObject progress = challengeProgress.getJSONObject("progress");
                        int done = progress.getInt("done");
                        int total = progress.getInt("total");
                        boolean success = progress.getBoolean("success");

                        //Partial progresses array:
                        JSONArray allPartialProgresses = progress.getJSONArray("allPartialProgresses");
                        List<String> partialProgresses = new ArrayList<>();
                        for (int j = 0; j < allPartialProgresses.length(); j++) {
                            partialProgresses.add(allPartialProgresses.getString(j));
                        }

                        //TODO: Add elements to the list of challenges here:


                        //TODO: Add elements from the partial progresses here:

                        //TODO: only add challenges that have the start date before datetime.now

                    }
                } else {
                    // TODO: Implement
                }

            } catch (JSONException e){
                e.printStackTrace();
            }
        });

        String body = ""; //empty body
        execute(body);
    }
}
