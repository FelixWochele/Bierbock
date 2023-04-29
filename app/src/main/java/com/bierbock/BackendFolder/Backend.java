package com.bierbock.BackendFolder;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Xml;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

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
import java.security.GeneralSecurityException;
import java.util.Map;
import java.util.Objects;

import javax.net.ssl.HttpsURLConnection;

public class Backend extends AsyncTask<String,String,String> {

    String data ="";
    String dataParsed = "";
    String singleParsed ="";

    private String htmlRequestType;

    //declare a delegate with type of protocol declared in this task
    private TaskDelegate delegate;

    public Backend(String htmlRequestType, TaskDelegate taskDelegate){
        this.htmlRequestType = htmlRequestType;
        delegate = taskDelegate;
    }
    public Backend(TaskDelegate taskDelegate){
        delegate = taskDelegate;
    }


    @Override
    protected String doInBackground(String... in) {

        String url = in[0];
        String token = in[1]; //Can also be empty, if action doesn't need it
        String body = in[2]; //POST has body, GET doesn't

        String res = apiCall(url, token, body);
        if(token.equals("")){
            res = apiCall(url, "", body);
        }

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


    protected String apiCall(String url, String token, String body){

        try{
            // HttpURLConnection-Objekt erstellen
            HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();


            // Anfrage-Methode setzen
            connection.setRequestMethod(htmlRequestType);

            // Content-Type Header setzen
            connection.setRequestProperty("Content-Type", "application/json");

            //Authorisation Token setzen
            if(!token.equals("")){
                connection.setRequestProperty("Authorization", "Bearer " + token);
            }

            if(!body.equals("")){
                // Body in den Request schreiben
                connection.setDoOutput(true);
                OutputStream os = connection.getOutputStream();
                os.write(body.getBytes());
                os.flush();
                os.close();
            }


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

    //Method for getting the authentication token saved locally on the device
    public static String getToken(Context context) {
        try {
            SharedPreferences sharedPreferences = EncryptedSharedPreferences.create(
                    "your_preference_name",
                    MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
                    context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );

            String token = sharedPreferences.getString("token_key", null);
            return token;
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
