package com.example.gmaps_project;


import android.os.Bundle;
import android.Manifest;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private final int FINE_PERMISSION_CODE = 1;
    private GoogleMap myMap;
    private SearchView mapSearchView;


    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient; 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        mapSearchView = findViewById(R.id.mapSearch);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mapSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query){
                String location = mapSearchView.getQuery().toString();
                List<Address> addressList = null;

                if (location != null || !location.equals("")){
                    Geocoder geocoder = new Geocoder(MainActivity.this);
                    try{
                        addressList = geocoder.getFromLocationName(location, 1);
                    }
                    catch(IOException e){
                        e.printStackTrace();
                    }
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    myMap.addMarker(new MarkerOptions().position(latLng).title(location));
                    myMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText){
                return false;
            }
        });
        mapFragment.getMapAsync(MainActivity.this); // This ensures that the map is set up and ready to be used when the activity or fragment is created.
        



    }

    private void getLastLocation(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && 
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, FINE_PERMISSION_CODE);
                return;
            }

        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>(){
            @Override
            public void onSuccess(Location location){
                if (location != null){
                    currentLocation = location;

                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                    mapFragment.getMapAsync(MainActivity.this);
                    //which loads the map in an asynchronous manner, meaning the map is requested to be loaded, but the application does not wait for the map to be ready before continuing with other tasks. 
                    //Instead, it runs the map loading process in the background, and once the map is ready, it triggers a callback to notify that the map is available for interaction.
                    
                }

            }


        });
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap){

        myMap = googleMap;

        LatLng sydney = new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());
        
        myMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        MarkerOptions options = new MarkerOptions().position(sydney).title("My Location");
        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
        myMap.addMarker(options);

    }

    //Without this method, your app wouldn't know if: The user granted location permissions,The user denied location permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == FINE_PERMISSION_CODE){
       
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                
                getLastLocation();
            }
            else
            {
                // Permission denied - show a message and use default location
                Toast.makeText(this, "Location Permission Denied. Using default location.", Toast.LENGTH_SHORT).show();
                
            
            }

        }
          
        
    }



}