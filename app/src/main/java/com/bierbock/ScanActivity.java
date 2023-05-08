package com.bierbock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bierbock.databinding.ActivityScanBinding;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

import java.util.Objects;

public class ScanActivity extends AppCompatActivity {

    //Variables for the location management:
    private LocationManager locationManager;
    private LocationListener locationListener;

    private double[] userCoordinates;


    // constant for permission request code
    private static final int CAMERA_REQUEST_CODE = 100;
    //constant for location permissions request code
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    private ActivityScanBinding binding;

    private CodeScanner mCodeScanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityScanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mCodeScanner.stopPreview();
                        Intent intent = new Intent(ScanActivity.this, DisplayScannedBeerActivity.class);

                        intent.putExtra("raw_barcode", result.getText());

                        if (userCoordinates != null) {
                            intent.putExtra("user_location", userCoordinates); //pass user coordinates to the displayScannedBarActivity class
                        } else {
                            Toast.makeText(ScanActivity.this, "Can't access user location, using default data!", Toast.LENGTH_SHORT).show();
                            //return; // don't start the DisplayScannedBeerActivity if the user location is not available yet
                        }

                        startActivity(intent);
                    }
                });
            }
        });
        //when the scanner somehow blocks:
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });

        //First: check the camera, then gps
        //Request camera permission:
        requestCameraPermission();

        //Request user location and save user coordinates:
        initLocationListener(); //Init location listener that updates userCoordinates
        requestLocationUpdates(); //request location updates
        //////
    }


    //Request camera permission on the start of the fragment...
    private void requestCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
        } //else {
            //requestLocationPermissions(); //Check location permissions status
        //}
    }

    //Request user location permissions
    private void requestLocationPermissions() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            //If both the camera permission and location permissions are checked:
            mCodeScanner.startPreview();
        }
    }


    //request location updates of the user:
    private void requestLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            String locationProvider = locationManager.getBestProvider(new Criteria(), true);
            if (locationProvider != null) {
                locationManager.requestLocationUpdates(locationProvider, 1000, 1, locationListener);

                // Get the last known location
                Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
                if (lastKnownLocation != null) {
                    double latitude = lastKnownLocation.getLatitude();
                    double longitude = lastKnownLocation.getLongitude();
                    double altitude = lastKnownLocation.getAltitude();

                    // Save user coordinates here
                    userCoordinates = new double[3];
                    userCoordinates[0] = latitude;
                    userCoordinates[1] = longitude;
                    userCoordinates[2] = altitude;
                }
            } else {
                Toast.makeText(this, "No location provider available", Toast.LENGTH_SHORT).show();
            }
        } else {
            requestLocationPermissions();
        }
    }

    //Check the permissions
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //Handle camera request code user click:
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mCodeScanner.startPreview();
            } else {
                Toast.makeText(this, "Camera permission is required to use the scanner.", Toast.LENGTH_SHORT).show();
                mCodeScanner.stopPreview();
                finish(); //TODO: check if needed to finish, maybe call requestCameraPermissions again?
            }
        }

        // Handle Location Permissions
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                // Both Fine and Coarse location permissions are granted
                requestLocationUpdates();
            } else {
                Toast.makeText(this, "Location permissions are required to get the user's GPS position.", Toast.LENGTH_SHORT).show();
                if(locationManager != null){
                    locationManager.removeUpdates(locationListener);
                }
                //Create default coordinates here:
                userCoordinates = new double[3];
                userCoordinates[0] = 10.0;
                userCoordinates[1] = 10.0;
                userCoordinates[2] = 10.0;
                //finish(); //For now, just finish the activity
                // You may decide to finish the activity or disable location-based features
            }
        }
    }

    //Resume when it can't read the barcode
    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
        requestLocationUpdates();
    }

    //when the scanner somehow blocks:
    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();

        if(locationManager != null){
            locationManager.removeUpdates(locationListener);
        }
    }


    //Location listener for the user location:
    private void initLocationListener() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                double altitude = location.getAltitude();

                //save user coordinated here:
                userCoordinates = new double[3];
                userCoordinates[0] = latitude;
                userCoordinates[1] = longitude;
                userCoordinates[2] = altitude;
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }
        };
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
        return true;
    }

}