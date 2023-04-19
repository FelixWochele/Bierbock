package com.bierbock;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Backend extends AsyncTask<Void,Void,Void> {

    String data ="";
    String dataParsed = "";
    String singleParsed ="";


    @Override
    protected Void doInBackground(Void... voids) {

        try {

            URL url = new URL("https://h2910896.stratoserver.net/BierBock/ownUserData");

            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("token", "123456");

            //TODO: WHY THE FUCK ERROR
            int responseCode = con.getResponseCode();

            System.out.println("GET Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();

                // print result
                System.out.println(response.toString());

            } else {
                System.out.println("GET request did not work.");
            }

        }
        catch (ProtocolException e) {
            System.out.println("fuck1");
            throw new RuntimeException(e);
        }
        catch (MalformedURLException e) {
            System.out.println("fuck2");
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            System.out.println("fuck3");
            throw new RuntimeException(e);
        }


        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

    }
}
