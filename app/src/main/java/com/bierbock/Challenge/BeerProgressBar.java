package com.bierbock.Challenge;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bierbock.MainActivity;

import com.bierbock.R;

public class BeerProgressBar extends FrameLayout {
    private FrameLayout progressContainer;
    private ImageView bubble;

    private double maxProgress = 1.0;

    public BeerProgressBar(Context context) {
        super(context);
        init(context);
    }

    public BeerProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BeerProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.beer__progress_bar, this, true);
        progressContainer = findViewById(R.id.progress_container);
        bubble = findViewById(R.id.bubble);
    }

    public void setProgress(double progress) {
        if (progress < 0 || progress > maxProgress) {
            throw new IllegalArgumentException("Progress must be between 0 and maxProgress");
        }
        float percentage = (float) (progress / maxProgress);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) progressContainer.getLayoutParams();
        layoutParams.width = (int) (percentage * getResources().getDisplayMetrics().widthPixels);
        progressContainer.setLayoutParams(layoutParams);
        //FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) progressContainer.getLayoutParams();
        //layoutParams.width = (int) (progress * getResources().getDisplayMetrics().widthPixels);
        //progressContainer.setLayoutParams(layoutParams);
    }

    public void animateBubble() {
        //MainActivity.animateBubble(getContext(), bubble);
    }

    public double getMaxProgress() {
        return maxProgress;
    }

    public void setMaxProgress(double maxProgress) {
        this.maxProgress = maxProgress;
    }
}