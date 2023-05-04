package com.bierbock.BackendFolder;

import static com.bierbock.BackendFolder.Backend.getToken;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class BackendRequest {

    protected Context applicationContext;
    protected String requestType;
    protected String url;
    protected TaskDelegate taskDelegate;

    public BackendRequest(Context context, String requestType, String url) {
        this.applicationContext = context;
        this.requestType = requestType;
        this.url = url;
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


    /*
    public LinkedHashMap<String, Object> parseJsonObject(JSONObject object) throws JSONException {

        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();

        try {
            // Check if the "result" key contains a JSONObject or a JSONArray
            Object result = object.get("result");

            if (result instanceof JSONObject) {
                // Process the "result" JSONObject
                JSONObject resultObject = (JSONObject) result;
                resultMap = jsonToLinkedHashMap(resultObject);
            } else if (result instanceof JSONArray) {
                // Process the "result" JSONArray
                JSONArray resultArray = (JSONArray) result;
                resultMap = jsonArrayToLinkedHashMap(resultArray);
            } else {
                // Handle unexpected "result" value types
                // ...
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    private static LinkedHashMap<String, Object> jsonToLinkedHashMap(JSONObject jsonObject) throws JSONException {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();

        for (Iterator<String> iterator = jsonObject.keys(); iterator.hasNext();) {
            String key = iterator.next();
            Object value = jsonObject.get(key);
            map.put(key, value);
        }

        return map;
    }

    private static LinkedHashMap<String, Object> jsonArrayToLinkedHashMap(JSONArray jsonArray) throws JSONException {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            for (Iterator<String> iterator = jsonObject.keys(); iterator.hasNext();) {
                String key = iterator.next();
                Object value = jsonObject.get(key);

                if (!map.containsKey(key)) {
                    ArrayList<Object> list = new ArrayList<>();
                    map.put(key, list);
                }
                ((ArrayList<Object>) map.get(key)).add(value);
            }
        }

        return map;
    }

*/

}
