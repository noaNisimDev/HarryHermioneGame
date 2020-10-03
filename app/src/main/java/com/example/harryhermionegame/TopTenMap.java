package com.example.harryhermionegame;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;


public class TopTenMap extends Fragment {

    private GoogleMap gMap;
    MapView mapView;

    public TopTenMap() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.topten_map, container, false);
        mapView = (MapView) view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        mapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }


        mapView.getMapAsync(new OnMapReadyCallback() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onMapReady(GoogleMap mMap) {
                gMap = mMap;


                //TODO - get all location from shared preferences

                Gson gson = new Gson();
                TopTen topTen = gson.fromJson(MySP.getInstance().getString(MySP.KEYS.USER_HIGH_SCORE, null), TopTen.class);

                if (topTen != null) {
                    for (Score score : topTen.getScores()) {
                        LatLng pos = new LatLng(score.getLat(), score.getLon() );
                        gMap.addMarker(new MarkerOptions().position(pos).title(score.getWinner()).snippet("Time: " + score.getTimestamp()
                        + " Num of actions: " + score.getNumOfActions()));
                    }
                }


                // For zooming automatically to the location of the marker
                //CameraPosition cameraPosition = new CameraPosition.Builder().target(israel).zoom(12).build();
                //gMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });


        return view;
    }
}
