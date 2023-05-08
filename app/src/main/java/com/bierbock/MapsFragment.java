package com.bierbock;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bierbock.BackendFolder.AllDrinkActions;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.heatmaps.Gradient;
import com.google.maps.android.heatmaps.HeatmapTileProvider;
import com.google.maps.android.heatmaps.WeightedLatLng;

import java.util.ArrayList;


public class MapsFragment extends Fragment {

    private ArrayList<LatLng> drinkingCoordinates = new ArrayList<>();

    //for user location
    private double[] userCoordinates;
    private LocationManager locationManager;

    private LocationListener locationListener;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {

            ArrayList<WeightedLatLng> weightedCoordinates = addHeatMap(drinkingCoordinates);

            //Try to get user location
            initLocationListener();
            requestLocationUpdates();

            //Set users location if data available
            if(userCoordinates != null){

                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(userCoordinates[0], userCoordinates[1]), 17.0f));
                System.out.println("USER COORDONATES");
                System.out.println(userCoordinates[0]);
                System.out.println(userCoordinates[1]);
            }else{
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(48.44543782910548, 8.696831606441847), 17.0f));
                System.out.println("CANT GET USER COORDINATES!");
            }

            Gradient gradient = new Gradient(colors, startpoints);

            HeatmapTileProvider heatmapTileProvider = new HeatmapTileProvider.Builder()
                    .weightedData(weightedCoordinates)
                    .radius(20)
                    .gradient(gradient)
                    .build();
            TileOverlayOptions tileoverlayoptions = new TileOverlayOptions().tileProvider(heatmapTileProvider);
            TileOverlay tileoverlay = googleMap.addTileOverlay(tileoverlayoptions);
            tileoverlay.clearTileCache();
            }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //Call all drink actions backend:
        AllDrinkActions allDrinkActions = new AllDrinkActions("", "", "", this);

        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    //Seva:
    //May create race conditions??
    public void updateDrinkingCoordinates(ArrayList<LatLng> coordinates){
        drinkingCoordinates.clear();

        drinkingCoordinates = coordinates;

        // Refresh the map with the new coordinates
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    // Mine

    private ArrayList<WeightedLatLng> addHeatMap(ArrayList<LatLng> drinkingCoordinates) {

        ArrayList<WeightedLatLng> arr = new ArrayList<>();

        //two points to init -> otherwise error
        arr.add(new WeightedLatLng(new LatLng(13.0827, 80.2707),180)); //chennai
        arr.add(new WeightedLatLng(new LatLng(11.0168, 76.9558),270)); //coimbatore

        if(drinkingCoordinates == null) {
            arr.add(new WeightedLatLng(new LatLng(48.0, 8.0), 200));
            arr.add(new WeightedLatLng(new LatLng(48.44543782910548, 8.696831606441847),180)); //madurai
            arr.add(new WeightedLatLng(new LatLng(48.446372343732975, 8.698487270888112),70));
            arr.add(new WeightedLatLng(new LatLng(13.0827, 80.2707),180)); //chennai
            arr.add(new WeightedLatLng(new LatLng(11.0168, 76.9558),270)); //coimbatore
            arr.add(new WeightedLatLng(new LatLng(11.7863, 77.8008),380));
            arr.add(new WeightedLatLng(new LatLng(11.7480, 79.7714),190));
            arr.add(new WeightedLatLng(new LatLng(8.7642, 78.1348),299));
            arr.add(new WeightedLatLng(new LatLng(11.6643, 78.1460),398));
        }else {
            //Add all drinking coordinates:
            if (drinkingCoordinates.size() > 0) {
                for (LatLng coordinate : drinkingCoordinates) {
                    arr.add(new WeightedLatLng((coordinate), 100));
                }
            }
        }

        return arr;
    }


    int[] colors = {
            Color.GREEN,    // green
            Color.YELLOW,    // yellow
            Color.rgb(255,165,0), //Orange
            Color.RED,              //red
            Color.rgb(153,50,204), //dark orchid
            Color.rgb(165,42,42) //brown
    };

    float[] startpoints = {
            0.0f,    //0-50
            0.1f,   //51-100
            0.2f,   //101-150
            0.3f,   //151-200
            0.4f,    //201-300
            0.6f      //301-500
    };



    /*

    All for location Data

     */

    //request location updates of the user:
    private void requestLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this.requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this.requireContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

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
                Toast.makeText(this.getContext(), "No location provider available", Toast.LENGTH_SHORT).show();
            }
        } else {
            requestLocationPermissions();
        }
    }


    //Request user location permissions
    private void requestLocationPermissions(){
        if (ActivityCompat.checkSelfPermission(this.requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this.requireContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this.requireActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);

            Toast.makeText(this.getContext(), "Location permissions are required to get the user's GPS position.", Toast.LENGTH_SHORT).show();

        }
    }

    //Location listener for the user location:
    private void initLocationListener() {
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

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



/*

    //Check the permissions
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Handle Location Permissions
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                requestLocationUpdates();
            } else {
                Toast.makeText(this.getContext(), "Location permissions are required to get the user's GPS position.", Toast.LENGTH_SHORT).show();
            }
        }
    }

 */

}