package com.example.my;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MyLocationListener extends MainActivity implements LocationListener {
	@Override
    public void onLocationChanged(Location loc) {
        loc.getLatitude();
        loc.getLongitude();
        String Text = "Latitude = "+ loc.getLatitude() + "Longitude = " + loc.getLongitude();
        TextView txtgps = (TextView)findViewById(R.id.text_GPS);
        txtgps.setText(Text);
        Toast.makeText(getApplicationContext(), Text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(getApplicationContext(), "Disable",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(getApplicationContext(), "Enable",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}

}
