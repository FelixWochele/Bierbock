package com.bierbock.BeerHistory;

import android.widget.ImageView;

public class BeerHistoryItem {

    private String beerName;
    private String dateTime; //When the beer was drank
    private ImageView beerImage;

    public BeerHistoryItem(String beerName, String dateTime, ImageView beerImage) {
        this.beerName = beerName;
        this.dateTime = dateTime;
        this.beerImage = beerImage;
    }


    public String getBeerName() {
        return beerName;
    }

    public void setBeerName(String beerName) {
        this.beerName = beerName;
    }

    public ImageView getBeerImage() {
        return beerImage;
    }

    public void setBeerImage(ImageView beerImage) {
        this.beerImage = beerImage;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
