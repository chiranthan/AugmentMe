package com.findme;

import java.text.DecimalFormat;
import java.util.List;

import com.findme.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class screen2 extends Activity{
	private Camera mCamera;
    private CameraPreview mPreview;
	private FrameLayout preview;
	private DrawcompView cv;
	
	//NH Coordinates:
	private Double nhx = -97.11369355;
	private Double nhy = 32.73196555;
	
	//ERB Coordinates:
	private Double erbx = -97.11282096;
	private Double erby = 32.73297688;
	
	//All Sensor code:
	private List<Sensor> sensors;
	private SensorManager mSensorManager;
    private LocationManager mlocManager;
    private LocationListener mlocListener;
    private Location loc2 = new Location("dummyprovider");
    private TextView txtgps;
    private TextView txtcomp;
    private float angle = 0;
    private float distance = 0;
    private float azimuth;
    
	private SensorEventListener mSensorEventListener = new SensorEventListener() {
        @Override
	    public void onSensorChanged(SensorEvent event) {
	        Sensor sensor = event.sensor;
	       
	    	 if (sensor.getType() == Sensor.TYPE_ORIENTATION) {
	         	azimuth = Math.round(event.values[0]);
	         	cv.setRotation(-azimuth +angle);
	             
	         	txtcomp.setText("Direction: " + Float.toString(azimuth));
	         	
        	}
	    }
	
	        @Override
	    public void onAccuracyChanged(Sensor sensor, int accuracy) {
	    }

	};

	private void setupSensors() {
        
     mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
     sensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
	 mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
	 mlocListener = new LocationListener(){
   	  public void onLocationChanged(Location loc) {
   		  
   		    loc2.setLatitude(erby);
   		    loc2.setLongitude(erbx);
   		    angle = loc.bearingTo(loc2);
   		  	
   		    distance = loc2.distanceTo(loc);
   	      
   		  	String s = Float.toString(angle);
   		  	String dist = Float.toString(distance);
   	        txtgps.setText("Distance = " + dist + " m \n" + "  Angle = " + s);
   		  
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

     };
    }
	
	protected void onResume() {
	     super.onResume();
	     setupSensors();
	     mSensorManager.registerListener(mSensorEventListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
	     mSensorManager.registerListener(mSensorEventListener, mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_GAME);
	     mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,0, mlocListener);
	     mSensorManager.registerListener(mSensorEventListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	public static Camera getCameraInstance(){
	    Camera c = null;
	    try {
	        c = Camera.open(); // attempt to get a Camera instance
	    }
	    catch (Exception e){
	        // Camera is not available (in use or does not exist)
	    }
	    return c; // returns null if camera is unavailable
	}
	protected void onPause() {
	     super.onPause();

	     Log.i("LoggerActivity", "onPause called");

	     // Unregister sensor listeners
	     for (Sensor s : sensors) {
	         mSensorManager.unregisterListener(mSensorEventListener, s);
	     }


	     Log.i("LoggerActivity", "onPause finished.");
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		preview = new FrameLayout(this);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT);
		preview.setLayoutParams(lp);
		setContentView(preview);
        mCamera = getCameraInstance();
        Intent intent = getIntent();
        
        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(this, mCamera);
        preview.addView(mPreview);
      
        cv = new DrawcompView(this);
        txtgps = new TextView(getBaseContext());
        txtcomp = new TextView(getBaseContext());
        txtgps.setTextSize(17);
        txtcomp.setTextSize(10);
      
        preview.addView(cv);
        preview.addView(txtgps);
        preview.addView(txtcomp);
        txtgps.setText("Waiting for information from GPS...");
        txtcomp.setX(550);
        txtcomp.setText("compass view...");
		}
		
}