package com.bierbock;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_layout);
        videoView = (VideoView) findViewById(R.id.videoView);

        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splashscreen);
        videoView.setVideoURI(video);

        videoView.setOnCompletionListener(mp -> startNextActivity());

        videoView.start();
    }

    private void startNextActivity() {
        if (isFinishing())
            return;
        //TODO: Check the token here, if valid -> move to the MainActivity directly
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

}