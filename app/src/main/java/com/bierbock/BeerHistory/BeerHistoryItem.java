package com.bierbock.BeerHistory;

import android.widget.ImageView;

public class BeerHistoryItem {

    private String beerName;
    private String dateTime; //When the beer was drank
    private String beerImageURL; //URL for the image of the beer

    public BeerHistoryItem(String beerName, String dateTime, String beerImageURL) {
        this.beerName = beerName;
        this.dateTime = dateTime;
        this.beerImageURL = beerImageURL;

    }

    public String getBeerName() {
        return beerName;
    }

    public void setBeerName(String beerName) {
        this.beerName = beerName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getBeerImageURL() {
        return beerImageURL;
    }

    public void setBeerImageURL(String beerImageURL) {
        this.beerImageURL = beerImageURL;
    }
}
