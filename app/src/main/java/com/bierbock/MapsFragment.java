package com.bierbock;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.heatmaps.Gradient;
import com.google.maps.android.heatmaps.HeatmapTileProvider;
import com.google.maps.android.heatmaps.WeightedLatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsFragment extends Fragment {

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
            /*LatLng sydney = new LatLng(-34, 151);
            googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

             */


            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(48.44543782910548, 8.696831606441847), 1500));

            Gradient gradient = new Gradient(colors, startpoints);
            HeatmapTileProvider heatmapTileProvider = new HeatmapTileProvider.Builder()
                    .weightedData(addheatmap())
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

    // Mine


    private ArrayList addheatmap() {
        ArrayList<WeightedLatLng> arr = new ArrayList<>();

        arr.add(new WeightedLatLng(new LatLng(48.44543782910548, 8.696831606441847),180)); //madurai
        arr.add(new WeightedLatLng(new LatLng(48.446372343732975, 8.698487270888112),70));
        arr.add(new WeightedLatLng(new LatLng(13.0827, 80.2707),180)); //chennai
        arr.add(new WeightedLatLng(new LatLng(11.0168, 76.9558),270)); //coimbatore
        arr.add(new WeightedLatLng(new LatLng(11.7863, 77.8008),380));
        arr.add(new WeightedLatLng(new LatLng(11.7480, 79.7714),190));
        arr.add(new WeightedLatLng(new LatLng(8.7642, 78.1348),299));
        arr.add(new WeightedLatLng(new LatLng(11.6643, 78.1460),398));


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

}