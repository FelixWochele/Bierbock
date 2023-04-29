package com.bierbock.BackendFolder;

import android.os.AsyncTask;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

//TODO: CAN BE REMOVEDDDDD

public class BackendGet extends AsyncTask<String, String, String> {

    private TaskDelegate delegate;

    public BackendGet(TaskDelegate taskDelegate) {
        delegate = taskDelegate;
    }

    @Override
    protected String doInBackground(String... in) {
        String url = in[0];
        String res = apiCall(url);
        return res;
    }

    @Override
    protected void onPostExecute(String res) {
        try {
            delegate.onTaskFinishGettingData(res);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected String apiCall(String url) {
        StringBuilder response = new StringBuilder();
        try {
            // HttpURLConnection-Objekt erstellen
            HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            // Response-Code abrufen
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Response lesen
            BufferedReader in = null;
            if(responseCode == HttpURLConnection.HTTP_OK) {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Response ausgeben
            System.out.println(response);

            return response.toString();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
}
