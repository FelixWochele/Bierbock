package com.bierbock;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bierbock.BackendFolder.BarcodeData;
import com.bierbock.BackendFolder.ImageLoader;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class DisplayScannedBeerActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_scannedbeer);

        ImageView imageView = findViewById(R.id.beerImageView);
        Button scanAgainButton = findViewById(R.id.scan_again_button);
        Button okButton = findViewById(R.id.ok_button);
        String scannedBeerURL = startBackendRequest(); //TODO: change to Image?

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

    private String startBackendRequest(){
        String rawBarcodeData = getIntent().getStringExtra("scanned_data");

        //Start barcode backend request
        BarcodeData barcodeData = new BarcodeData(rawBarcodeData, this);
        //ImageView image = (ImageView) findViewById(R.id.beerImageView);

        System.out.println(rawBarcodeData);

        return rawBarcodeData;
    }

    public void loadImageFromURL(String url){

        ImageView imageView = (ImageView) findViewById(R.id.beerImageView);
        int targetWidth = imageView.getWidth();
        int targetHeight = imageView.getHeight();

        //load the image:
        ImageLoader.loadImage(
                this,
                url,
                imageView,
                targetWidth,
                targetHeight,
                R.drawable.beer_example_image,
                null //no callback
        );


        /*
        loadImage(this, url, imageView, targetWidth, targetHeight, () ->{
            //NO callback action for now
        }); */
    }

    interface ImageLoadCallback {
        void onImageLoaded();
    }

    private void loadImage(Context context, String imageUrl, @NonNull ImageView imageView, int targetWidth, int targetHeight, ImageLoadCallback callback) {
        Glide.with(context)
                .load(imageUrl)
                .fallback(R.drawable.beer_example_image) //If the image loading failed
                .override(targetWidth, targetHeight)
                .centerCrop()
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        callback.onImageLoaded();
                        return false; //Return false to set the fallback image if the loading fails
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        callback.onImageLoaded();
                        return false;
                    }
                })
                .into(imageView);
    }


    private void goToMainActivity() {
        Intent intent = new Intent(DisplayScannedBeerActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

}
