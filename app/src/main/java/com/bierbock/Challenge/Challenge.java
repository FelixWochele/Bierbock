package com.bierbock.Challenge;

public class Challenge {
    private String description;
    private int points;
    private int progress;
    private int maxProgress;

    public Challenge(String description, int points, int progress, int maxProgress) {
        this.description = description;
        this.points = points;
        this.progress = progress;
        this.maxProgress = maxProgress;
    }

    // Getters and setters for each property
    // ...
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getMaxProgress() {
        return maxProgress;
    }

    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }
}

