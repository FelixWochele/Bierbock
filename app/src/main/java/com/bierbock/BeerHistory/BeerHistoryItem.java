package com.bierbock.BeerHistory;

public class BeerHistoryItem {

    private String beerName;
    private String beerBrand;
    private String dateTime; //When the beer was drank
    private String beerImageURL; //URL for the image of the beer

    public BeerHistoryItem(String beerName, String beerBrand, String dateTime, String beerImageURL) {
        this.beerName = beerName;
        this.beerBrand = beerBrand;
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

    public String getBeerBrand() {
        return beerBrand;
    }

    public void setBeerBrand(String beerBrand) {
        this.beerBrand = beerBrand;
    }
}
