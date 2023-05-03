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

    public void setTaskDelegate(TaskDelegate taskDelegate) {
        this.taskDelegate = taskDelegate;
    }

    public void execute(String... params) {
        //Create new backend object
        Backend backend = new Backend(requestType, taskDelegate);

        //TODO: Check if working correctly:
        String authenticationToken = getToken(applicationContext);

        backend.execute(url, authenticationToken, params[0]); //TODO: change params from String... to String?
    }

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

}

//Backend backend = new Backend(requestType, taskDelegate);
//String[] parameters = new String[params.length + 2];
//parameters[0] = url;

//parameters[1] = getToken(applicationContext); //get the token for the request
//System.arraycopy(params, 0, parameters, 2, params.length);
//backend.execute(parameters);