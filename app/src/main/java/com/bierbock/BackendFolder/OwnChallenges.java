package com.bierbock.BackendFolder;

import com.bierbock.Challenge.Challenge;
import com.bierbock.ChallengeFragment;
import com.bierbock.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


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

                    List<Challenge> challenges = new ArrayList<>();

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


                        boolean isAvailableDate = checkIfAvailableDate(startDate, endDate);

                        //Only add challenges that are still active and not overdue:
                        if(isActive && isAvailableDate){

                            Challenge challengeObject = new Challenge(description, possiblePoints, done, total, endDate, partialProgresses);
                            challenges.add(challengeObject);

                            challengeFragment.updateChallenges(challenges);
                        }

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


    private boolean checkIfAvailableDate(String startDate, String endDate){
        // Step 1: Parse the input string into a Date object
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US);
        try {
            // Parse startTime and create a Calendar instance for it
            Date parsedStartDate = dateFormat.parse(startDate.substring(0, 23));
            Calendar startTimeCalendar = Calendar.getInstance();
            assert parsedStartDate != null;
            startTimeCalendar.setTime(parsedStartDate);

            // Parse endTime and create a Calendar instance for it
            Date parsedEndDate = dateFormat.parse(endDate.substring(0, 23));
            Calendar endTimeCalendar = Calendar.getInstance();
            assert parsedEndDate != null;
            endTimeCalendar.setTime(parsedEndDate);

            // Create a Calendar instance for the current date and time
            Calendar currentTimeCalendar = Calendar.getInstance();

            int comparisonResult = 0;

            // Compare startTimeCalendar to currentTimeCalendar
            int startComparisonResult = startTimeCalendar.compareTo(currentTimeCalendar);

            // Compare endTimeCalendar to currentTimeCalendar
            int endComparisonResult = endTimeCalendar.compareTo(currentTimeCalendar);

            if (startComparisonResult <= 0) {
                comparisonResult = 1;
                if(!(endComparisonResult > 0)){
                    comparisonResult = 0;
                }
            }

            return comparisonResult == 0;

        }
        catch (ParseException e)
        {
            e.printStackTrace();

        }
        return false;
    }
}
