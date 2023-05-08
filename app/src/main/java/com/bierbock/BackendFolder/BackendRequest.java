package com.bierbock.BackendFolder;

import static com.bierbock.BackendFolder.Backend.getToken;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BackendRequest {

    protected Context applicationContext;
    protected String requestType;
    protected String url;
    protected TaskDelegate taskDelegate;

    public BackendRequest(Activity activity, String requestType, String urlShortcut){
        this.applicationContext = activity.getApplicationContext();
        this.requestType = requestType;
        this.url = initializeUrl(urlShortcut);
    }

    public BackendRequest(Fragment fragment, String requestType, String urlShortcut){
        this.applicationContext = fragment.requireActivity().getApplicationContext();
        this.requestType = requestType;
        this.url = initializeUrl(urlShortcut);
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

        //Get authenticationToken:
        String authenticationToken = getToken(applicationContext);

        backend.execute(url, authenticationToken, body);
    }


}
