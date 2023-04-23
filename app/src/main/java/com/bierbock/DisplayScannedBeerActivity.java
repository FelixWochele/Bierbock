package com.bierbock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayScannedBeerActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_scannedbeer);

        ImageView imageView = findViewById(R.id.beerImageView);
        Button scanAgainButton = findViewById(R.id.scan_again_button);
        Button okButton = findViewById(R.id.ok_button);
        String scannedBeerURL = getBeerImageURL(); //TODO: change to Image?

        // Load the image corresponding to the scanned data
        // For now, just using a placeholder image
        // You can replace this with code to load an image based on the scanned data
        imageView.setImageResource(R.drawable.beer_example_image);

        scanAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // This will close the current activity and return to ScanActivity
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMainActivity();
            }
        });

    }

    private String getBeerImageURL(){
        String rawBarcodeData = getIntent().getStringExtra("scanned_data");
        //TODO: ACCESS BACKEND HERE?


        return rawBarcodeData;
    }

    private void goToMainActivity() {
        Intent intent = new Intent(DisplayScannedBeerActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

}
