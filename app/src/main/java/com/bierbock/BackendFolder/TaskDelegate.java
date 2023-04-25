package com.bierbock.BackendFolder;

import org.json.JSONException;

//here is the task protocol to can delegate on other object
public interface TaskDelegate {

    //define you method headers to override;
    void onTaskFinishGettingData(String result) throws JSONException, InterruptedException;

}
