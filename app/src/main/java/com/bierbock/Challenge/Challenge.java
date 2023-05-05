package com.bierbock.Challenge;

import java.util.List;

public class Challenge {
    private String description;
    private int points;
    private int progress;
    private int maxProgress;
    private String endDate;
    private List<String> partialProgresses;

    public Challenge(String description, int points, int progress, int maxProgress, String endDate, List<String> partialProgresses) {
        this.description = description;
        this.points = points;
        this.progress = progress;
        this.maxProgress = maxProgress;
        this.endDate = endDate;
        this.partialProgresses = partialProgresses;
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

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<String> getPartialProgresses() {
        return partialProgresses;
    }

    public void setPartialProgresses(List<String> partialProgresses) {
        this.partialProgresses = partialProgresses;
    }
}

