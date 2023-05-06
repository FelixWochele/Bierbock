package com.bierbock.BeerHistory;

public class BeerHistoryItem {

    private String beerName;
    private String beerBrand;
    private String date; //When the beer was drank
    private String beerImageURL; //URL for the image of the beer

    private String time; //URL for the image of the beer

    private String location;

    public BeerHistoryItem(String beerName, String beerBrand, String dateTime, String time, String location, String beerImageURL) {
        this.beerName = beerName;
        this.beerBrand = beerBrand;
        this.date = dateTime;
        this.beerImageURL = beerImageURL;
        this.time = time;
        this.location = location;
    }

    public String getBeerName() {
        return beerName;
    }

    public void setBeerName(String beerName) {
        this.beerName = beerName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBeerImageURL() {
        return beerImageURL;
    }

    public void setBeerImageURL(String beerImageURL) {
        this.beerImageURL = beerImageURL;
    }

    public String getBeerBrand() {return beerBrand;}

    public String getTime(){return  time;}

    public String getLocation(){return location;}

    public void setBeerBrand(String beerBrand) {
        this.beerBrand = beerBrand;
    }
}
