package com.bierbock.BackendFolder;
public abstract class BackendRequest {

    protected String requestType;
    protected String url;
    protected TaskDelegate taskDelegate;

    public BackendRequest(String requestType, String url, TaskDelegate taskDelegate) {
        this.requestType = requestType;
        this.url = url;
        this.taskDelegate = taskDelegate;
    }

    public void execute(String... params) {
        Backend backend = new Backend(requestType, taskDelegate);
        String[] parameters = new String[params.length + 1];
        parameters[0] = url;
        System.arraycopy(params, 0, parameters, 1, params.length);
        backend.execute(parameters);
    }

}

