package com.bierbock.BackendFolder;

import android.os.AsyncTask;
import android.util.Xml;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Backend extends AsyncTask<String,String,String> {

    String data ="";
    String dataParsed = "";
    String singleParsed ="";

    //declare a delegate with type of protocol declared in this task
    private TaskDelegate delegate;


    public Backend(TaskDelegate taskDelegate){
        delegate = taskDelegate;
    }


    @Override
    protected String doInBackground(String... in) {

        String url = in[0];
        String body = in[1];

        String res = apiCall(url, body);

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


    protected String apiCall(String url, String body){

        try{
            // HttpURLConnection-Objekt erstellen
            HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();

            // Anfrage-Methode setzen
            connection.setRequestMethod("POST");

            // Content-Type Header setzen
            connection.setRequestProperty("Content-Type", "application/json");

            // Body in den Request schreiben
            connection.setDoOutput(true);
            OutputStream os = connection.getOutputStream();
            os.write(body.getBytes());
            os.flush();
            os.close();

            // Response-Code abrufen
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Response lesen
            BufferedReader in = null;
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
