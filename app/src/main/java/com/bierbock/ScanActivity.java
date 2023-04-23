package com.bierbock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bierbock.databinding.ActivityScanBinding;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

public class ScanActivity extends AppCompatActivity {

    // constant for permission request code
    private static final int CAMERA_REQUEST_CODE = 100;

    private ActivityScanBinding binding;

    private CodeScanner mCodeScanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityScanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ScanActivity.this, result.getText(), Toast.LENGTH_SHORT).show(); //TODO: Can be removed?
                        mCodeScanner.stopPreview();
                        Intent intent = new Intent(ScanActivity.this, DisplayScannedBeerActivity.class);
                        intent.putExtra("scanned_data", result.getText());
                        startActivity(intent);
                    }
                });
            }
        });
        //TODO: Can be removed?
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });

       requestCameraPermission();
    }


    //Request camera permission on the start of the fragment...
    private void requestCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
        } else {
            mCodeScanner.startPreview();
        }
    }

    //Check the permissions
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mCodeScanner.startPreview();
            } else {
                Toast.makeText(this, "Camera permission is required to use the scanner.", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    //TODO: Can be removed?
    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    //TODO: Can be removed?
    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();

    }
}