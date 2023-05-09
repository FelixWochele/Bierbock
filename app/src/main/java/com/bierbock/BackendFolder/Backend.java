package com.bierbock.BackendFolder;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;

import javax.net.ssl.HttpsURLConnection;

public class Backend extends AsyncTask<String,String,String> {
    private final String htmlRequestType;

    //declare a delegate with type of protocol declared in this task
    private final TaskDelegate delegate;

    public Backend(String htmlRequestType, TaskDelegate delegate){
        this.htmlRequestType = htmlRequestType;
        this.delegate = delegate;
    }


    //This method is always called with 3 parameters: url, token and body
    //Token and body can also be empty
    //Token empty -> register or login request
    //Body empty -> GET request without body
    @Override
    protected String doInBackground(String... in) {

        String url = in[0];
        String token = in[1]; //Can also be empty, if action doesn't need it
        String body = in[2]; //POST has body, GET doesn't

        String res = apiCall(url, token, body);

        return res;
    }


    //Method for the background.execute
    @Override
    protected void onPostExecute(String res) {
        try {
            delegate.onTaskFinishGettingData(res);
        } catch (JSONException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    //Main method of the backend
    protected String apiCall(String url, String token, String body){

        try{
            // Create HttpsURLConnection object
            HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();

            //Set the request method
            connection.setRequestMethod(htmlRequestType);

            // Set Content-Type Header
            connection.setRequestProperty("Content-Type", "application/json");

            //Set Authorization Token, if it isn't empty
            if(!token.equals("")){
                connection.setRequestProperty("Authorization", "Bearer " + token);
            }

            //Write the body in the request, if not empty
            if(!body.equals("")){
                connection.setDoOutput(true);
                OutputStream os = connection.getOutputStream();
                os.write(body.getBytes());
                os.flush();
                os.close();
            }

            // Get Response-Code
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Read Response
            BufferedReader in;
            if (responseCode == HttpURLConnection.HTTP_OK) {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Print out the response
            System.out.println(response);

            //TODO: NEW...
            //Create a JSON object to store the response code and response
            JSONObject responseObj = new JSONObject();
            responseObj.put("responseCode", responseCode);
            responseObj.put("response", response.toString());

            return responseObj.toString();

            //Return the response to work with later
            //return response.toString();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }


    //Method for getting the authentication token saved locally on the device
    public static String getToken(Context context) {
        try {
            SharedPreferences sharedPreferences = EncryptedSharedPreferences.create(
                    "authentication_key",
                    MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
                    context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );

            //Return the token key saved in the sharedPreferences
            return sharedPreferences.getString("token_key", "");

        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
    public static String createJsonString(Map<String, String> parameters) {
        StringBuilder body = new StringBuilder("{");
        int tempCounter = 0;

        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            body.append(String.format("\"%s\": \"%s\"", entry.getKey(), entry.getValue()));
            tempCounter++;
            if (tempCounter != parameters.size()) {
                body.append(", ");
            }
        }
        body.append("}");

        return body.toString();
    }

    */
}
