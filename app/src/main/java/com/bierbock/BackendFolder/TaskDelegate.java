package com.bierbock.BackendFolder;

//here is the task protocol to can delegate on other object
public interface TaskDelegate {

    //define you method headers to override;
    void onTaskFinishGettingData(String result);

}
