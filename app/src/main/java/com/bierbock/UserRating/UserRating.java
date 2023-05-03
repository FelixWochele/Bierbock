package com.bierbock.UserRating;

public class UserRating {
    private String username;
    private int rating;
    private int points;
    private boolean isOwnUser;

    public UserRating(String username, int rating, int points) {
        this.username = username;
        this.rating = rating;
        this.points = points;
        this.isOwnUser = false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }


    public boolean isOwnUser() {
        return isOwnUser;
    }

    public void setOwnUser(boolean ownUser) {
        isOwnUser = ownUser;
    }
}

