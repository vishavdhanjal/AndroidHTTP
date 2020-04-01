package com.example.appgps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import javax.crypto.spec.GCMParameterSpec;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View    v) {
                        GPStracker g=new GPStracker(getApplicationContext());
                        Location l=g.getLocation();
                        if (l!=null){
                            double lat=l.getLatitude();
                            double lon=l.getLongitude();
                            Toast.makeText(getApplicationContext(),"Lat: " +lat+"\n"+"Lon:"+lon, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
}