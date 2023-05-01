package com.bierbock.BackendFolder;

import static com.bierbock.BackendFolder.Backend.getToken;

import android.content.Context;

public abstract class BackendRequest {

    protected Context applicationContext;
    protected String requestType;
    protected String url;
    protected TaskDelegate taskDelegate;

    public BackendRequest(Context context, String requestType, String url, TaskDelegate taskDelegate) {
        this.applicationContext = context;
        this.requestType = requestType;
        this.url = url;
        this.taskDelegate = taskDelegate;
    }

    public void execute(String... params) {
        //Create new backend object
        Backend backend = new Backend(requestType, taskDelegate);

        //TODO: Check if working correctly:
        String authenticationToken = getToken(applicationContext);

        backend.execute(url, authenticationToken, params[0]); //TODO: change params from String... to String?
    }
}

//Backend backend = new Backend(requestType, taskDelegate);
//String[] parameters = new String[params.length + 2];
//parameters[0] = url;

//parameters[1] = getToken(applicationContext); //get the token for the request
//System.arraycopy(params, 0, parameters, 2, params.length);
//backend.execute(parameters);