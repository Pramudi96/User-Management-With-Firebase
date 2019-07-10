package com.example.user.smartbustracker;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class getDrivetCureentLocation extends AppCompatActivity {

    private TextView Longitude;
    private TextView Latitude;
    private TextView accuracy;
    private TextView sensorType;
    private TextView altitude;
    private TextView updatesOff;

    private FusedLocationProviderClient mFusedLocationClient;
    private static final int MY_PERMISSION_REQUEST_FINE_LOCATION = 101;
    private Button Gps;
    private Button TurnOff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_drivet_current_location);

        Gps = (ToggleButton) findViewById(R.id.Gps);
        TurnOff = (ToggleButton) findViewById(R.id.TurnOff);
        Longitude = (TextView) findViewById(R.id.Longitude);
        Latitude = (TextView) findViewById(R.id.Latitude);
        accuracy =(TextView)findViewById(R.id.accuracy);
        altitude =(TextView)findViewById(R.id.altitude);
        updatesOff =(TextView)findViewById(R.id.updatesOff);


        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    Latitude.setText(String.valueOf(location.getLatitude()));
                    Longitude.setText(String.valueOf(location.getLongitude()));
                    accuracy.setText(String.valueOf(location.getAccuracy()));
                    if (location.hasAltitude()) {
                        altitude.setText(String.valueOf(location.getAltitude()));
                    } else {
                        altitude.setText("no Altitude");
                    }
                }


            }
        });


    }
}
