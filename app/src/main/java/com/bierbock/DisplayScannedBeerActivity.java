package com.bierbock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bierbock.BackendFolder.BarcodeData;
import com.bierbock.BackendFolder.ImageLoader;
import com.bierbock.BackendFolder.NewDrinkAction;

public class DisplayScannedBeerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_scannedbeer);

        //initialize buttons:
        Button scanAgainButton = findViewById(R.id.scan_again_button);
        Button okButton = findViewById(R.id.ok_button);

        //initialize imageView:
        ImageView imageView = findViewById(R.id.beerImageView);
        imageView.setImageResource(R.drawable.beer_example_image);

        //Most important call: backend request to get the beer data
        startBackendRequestForBarcode();


        scanAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // This will close the current activity and return to ScanActivity
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backendRequestNewDrinkAction();
            }
        });

    }

    //Calls backend for the barcode scanned previously in the scanActivity
    private void startBackendRequestForBarcode(){
        String rawBarcodeData = getIntent().getStringExtra("raw_barcode");

        //Start barcode backend request
        BarcodeData barcodeData = new BarcodeData(rawBarcodeData, this);

    }

    //Very important method that is getting called after the OK Button is clicked:
    private void backendRequestNewDrinkAction(){
        double[] userCoordinates = getIntent().getDoubleArrayExtra("user_location");
        String rawBarcodeData = getIntent().getStringExtra("raw_barcode");

        NewDrinkAction newDrinkAction = new NewDrinkAction(rawBarcodeData, userCoordinates, this);
    }

    //This method is called by the backend when opening the view:
    public void loadImageFromURL(String url){

        ImageView imageView = findViewById(R.id.beerImageView);
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
    }

    //Method to update the text views in displayScannedBeerActivity
    public void updateBeerDescription(String productName, String brands, String quantity){

        TextView productNameTextView = findViewById(R.id.product_name);
        TextView brandsTextView = findViewById(R.id.brands);
        TextView quantityTextView = findViewById(R.id.quantity);

        productNameTextView.setText(productName);
        brandsTextView.setText(brands);
        quantityTextView.setText(quantity);
    }

    //Method to get back to the main activity (called by backend callback):
    public void goToMainActivity() {
        //Start main activity here
        Intent intent = new Intent(DisplayScannedBeerActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); //Clear both scan and display activities from the stack
        startActivity(intent);
        finish();
    }

    public void backendFailMessage(){
        Toast.makeText(this, "There is no beer for that barcode", Toast.LENGTH_SHORT).show();

        //got back to scan activity
        Intent intent = new Intent(DisplayScannedBeerActivity.this, ScanActivity.class);
        startActivity(intent);
        finish();
    }

}
