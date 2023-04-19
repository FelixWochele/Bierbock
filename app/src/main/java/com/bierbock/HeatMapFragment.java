package com.bierbock;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.heatmaps.Gradient;
import com.google.maps.android.heatmaps.HeatmapTileProvider;
import com.google.maps.android.heatmaps.WeightedLatLng;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HeatMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HeatMapFragment extends Fragment {


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



    public HeatMapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HeatMapFragment.
     */
    public static HeatMapFragment newInstance(String param1, String param2) {
        HeatMapFragment fragment = new HeatMapFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_heat_map, container, false);
    }

/*
    private void buildheatmap(){

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
    */

    private ArrayList addheatmap() {
        ArrayList<WeightedLatLng> arr = new ArrayList<>();

        arr.add(new WeightedLatLng(new LatLng(9.9252, 78.119),10)); //madurai
        arr.add(new WeightedLatLng(new LatLng(10.7905, 78.7047),60)); //trichy
        arr.add(new WeightedLatLng(new LatLng(13.0827, 80.2707),180)); //chennai
        arr.add(new WeightedLatLng(new LatLng(11.0168, 76.9558),270)); //coimbatore
        arr.add(new WeightedLatLng(new LatLng(11.7863, 77.8008),380));
        arr.add(new WeightedLatLng(new LatLng(11.7480, 79.7714),190));
        arr.add(new WeightedLatLng(new LatLng(8.7642, 78.1348),299));
        arr.add(new WeightedLatLng(new LatLng(11.6643, 78.1460),398));


        return arr;
    }

}