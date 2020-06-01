/**
 * MapsActivity.java: Class which shows a map of games played in database
 *
 * @author Cameron Colleran
 * @version 1.0
 */
package edu.miracostacollege.stattracker;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback
{

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;

        // Move camera to focus in between all markers (near Las Vegas)
        LatLng focus = new LatLng(36, -115);
        CameraPosition position = new CameraPosition.Builder().target(focus).zoom(5f).build();
        CameraUpdate update = CameraUpdateFactory.newCameraPosition(position);
        mMap.moveCamera(update);

        // Add a marker at Petco Park
        LatLng petcoPark = new LatLng(32.7073, -117.1566);
        mMap.addMarker(new MarkerOptions().position(petcoPark).title("Game vs Rockies"));

        // Add a marker at Oracle Park
        LatLng oraclePark = new LatLng(37.7786, -122.3893);
        mMap.addMarker(new MarkerOptions().position(oraclePark).title("Game vs Giants"));

        // Add a marker at Dodger Stadium
        LatLng dodgerStadium = new LatLng(34.0739, -118.2400);
        mMap.addMarker(new MarkerOptions().position(dodgerStadium).title("Game vs Dodgers"));

        // Add a marker at Chase Field
        LatLng chaseField = new LatLng(33.4453, -112.0667);
        mMap.addMarker(new MarkerOptions().position(chaseField).title("Game vs Diamondbacks"));
    }
}
