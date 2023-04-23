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
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) progressContainer.getLayoutParams();
        layoutParams.width = (int) (progress * getResources().getDisplayMetrics().widthPixels);
        progressContainer.setLayoutParams(layoutParams);
    }

    public void animateBubble() {
        MainActivity.animateBubble(getContext(), bubble);
    }

}