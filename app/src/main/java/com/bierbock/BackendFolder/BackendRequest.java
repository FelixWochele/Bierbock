package com.bierbock.BackendFolder;

import static com.bierbock.BackendFolder.Backend.getToken;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.bierbock.LoginActivity;
import com.bierbock.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;

public abstract class BackendRequest {

    private Activity currentActivity; //needed for http error 401 (unauthorized)
    private Fragment currentFragment; //needed for http error 401 (unauthorized)

    protected Context applicationContext;
    protected String requestType;
    protected String url;
    protected TaskDelegate taskDelegate;

    //Main jsonObject:...
    protected JSONObject obj;

    //Initialize Backend Request and the callback for given activity
    public BackendRequest(Activity activity, String requestType, String urlShortcut){
        currentActivity = activity;
        this.applicationContext = activity.getApplicationContext();
        this.requestType = requestType;
        this.url = initializeUrl(urlShortcut);

        setTaskDelegate(result -> {
            try {
                JSONObject resultObj = new JSONObject(result);
                int responseCode = resultObj.getInt("responseCode");
                String response = resultObj.getString("response");

                if (responseCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                    onUnauthorizedResponse();
                } else {
                    obj = new JSONObject(response); // Create JSONObject

                    if ("Successful".equals(obj.getString("statusMessage"))) {
                        onRequestSuccessful();
                    } else {
                        onRequestFailed();
                    }
                }
            } catch (JSONException | IOException e){
                e.printStackTrace();
            }
        });

    }

    //Initialize Backend Request and the callback for given fragment
    public BackendRequest(Fragment fragment, String requestType, String urlShortcut){
        currentFragment = fragment;
        this.applicationContext = fragment.requireActivity().getApplicationContext();
        this.requestType = requestType;
        this.url = initializeUrl(urlShortcut);

        setTaskDelegate(result -> {
            try {
                JSONObject resultObj = new JSONObject(result);
                int responseCode = resultObj.getInt("responseCode");
                String response = resultObj.getString("response");

                if (responseCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                    onUnauthorizedResponse();
                } else {
                    obj = new JSONObject(response); // Create JSONObject

                    if ("Successful".equals(obj.getString("statusMessage"))) {
                        onRequestSuccessful();
                    } else {
                        onRequestFailed();
                    }
                }
            } catch (JSONException | IOException e){
                e.printStackTrace();
            }
        });
    }

    private String initializeUrl(String urlShortcut){
        if(!urlShortcut.contains("/")){
            return "https://www.beerbock.de/BierBock/" + urlShortcut;
        }
        return "https://www.beerbock.de/" + urlShortcut;
    }

    public void addUrlParameters(String[] parameterNames, String[] parameterValues) {
        if (parameterNames == null || parameterValues == null || parameterNames.length != parameterValues.length) {
            throw new IllegalArgumentException("Invalid parameter names or values");
        }

        if (parameterNames.length == 0) {
            return;
        }

        StringBuilder urlBuilder = new StringBuilder(url);
        urlBuilder.append("?");

        for (int i = 0; i < parameterNames.length; i++) {
            if (i > 0) { //first url doesn't need this
                urlBuilder.append("&");
            }
            urlBuilder.append(parameterNames[i]);
            urlBuilder.append("=");
            urlBuilder.append(parameterValues[i]);
        }

        url = urlBuilder.toString();

        url = url.replace("@", "%40");
    }

    //Set Task delegate callback here:
    public void setTaskDelegate(TaskDelegate taskDelegate) {
        this.taskDelegate = taskDelegate;
    }

    public void execute(String body) {
        //Create new backend object
        Backend backend = new Backend(requestType, taskDelegate);

        String authenticationToken = ""; //empty token by default (if security action)

        if(!url.contains("/security/createToken") && !url.contains("/security/register")){
            //Get authenticationToken:
            authenticationToken = getToken(applicationContext);
        }

        backend.execute(url, authenticationToken, body);
    }

    protected abstract void onRequestSuccessful() throws JSONException, IOException;

    protected abstract void onRequestFailed() throws JSONException, IOException;

    //Move to login activity when the auth token is too old:
    protected void onUnauthorizedResponse(){
        //if the class is created using an activity
        if(currentActivity != null){
            //TODO: add error messages to the intent extra
            currentActivity.startActivity(new Intent(applicationContext, LoginActivity.class));
            currentActivity.finish();
        }

        //if the class is created using a fragment
        if(currentFragment != null){
            Activity activity = currentFragment.requireActivity();
            //TODO: add error messages to the intent extra
            activity.startActivity(new Intent(applicationContext, LoginActivity.class));
            activity.finish();
        }
    }
}
