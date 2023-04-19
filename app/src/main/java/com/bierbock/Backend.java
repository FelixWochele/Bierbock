package com.bierbock;

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

public class Backend extends AsyncTask<Void,Void,Void> {

    String data ="";
    String dataParsed = "";
    String singleParsed ="";


    @Override
    protected Void doInBackground(Void... voids) {

        // URL der REST-API
        String url = "https://www.beerbock.de/swagger/security/createToken";

        // Body der Anfrage
        String body = "{\"userName\": \"mustimax\", \"password\": \"Password123\"}";

        // URL-Objekt erstellen
        URL obj = null;
        try {
            obj = new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        // HttpURLConnection-Objekt erstellen
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) obj.openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Anfrage-Methode setzen
        try {
            connection.setRequestMethod("POST");
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        }

        // Content-Type Header setzen
        connection.setRequestProperty("Content-Type", "application/json");


        // Body in den Request schreiben
        connection.setDoOutput(true);
        OutputStream os = null;
        try {
            os = connection.getOutputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            os.write(body.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            os.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            os.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Response-Code abrufen
        int responseCode = 0;
        try {
            responseCode = connection.getResponseCode();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Response Code: " + responseCode);

        // Response lesen
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String inputLine;
        StringBuffer response = new StringBuffer();
        while (true) {
            try {
                if (!((inputLine = in.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            response.append(inputLine);
        }
        try {
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Response ausgeben
        System.out.println("Response: " + response.toString());




        /*
        try {

            URL url = new URL("https://www.beerbock.de/swagger/security/createToken");

            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

            //HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                con.setDoOutput(true);
                con.setChunkedStreamingMode(0);

                OutputStream out = new BufferedOutputStream(con.getOutputStream());

                PrintWriter pw = new PrintWriter(out);
                pw.println("{\n" +
                        "  \"userName\": \"mustimax\",\n" +
                        "  \"password\": \"Password1sdsf23\"\n" +
                        "}");

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

            } finally {
                con.disconnect();
            }
            /*
            con.setRequestMethod("GET");
            con.setRequestProperty("userName", "mustimax");
            con.setRequestProperty("password", "Password123");
            //con.setRequestProperty("token", "123456");


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
        */


        return null;

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

    }
}
