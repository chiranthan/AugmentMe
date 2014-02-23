package com.example.my;

import java.util.List;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	private TextView mAccelXTextView;

    private TextView mAccelYTextView;

    private TextView mAccelZTextView;

    private TextView mMagXTextView;

    private TextView mMagYTextView;

    private TextView mMagZTextView;

    private List<Sensor> sensors;
    LocationListener mlocListener;
    private Sensor mCompass;
 
    
    private TextView mCompDTextView;
    
    protected TextView txtgps;

    private SensorManager mSensorManager;
    private LocationManager mlocManager;
	
	private SensorEventListener mSensorEventListener = new SensorEventListener() {
        @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        if (sensor.getType() == Sensor.TYPE_ORIENTATION) {
        	float azimuth = Math.round(event.values[0]);
            // The other values provided are: 
            //  float pitch = event.values[1];
            //  float roll = event.values[2];
        	mCompDTextView.setText("Direction: " + Float.toString(azimuth));
        }
        updateSensorUi(sensor.getType(), event.accuracy, event.values);
        
    }

        @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

};


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// setupSensors();
        initSensorUi();
	}
	private void setupSensors() {
        // initSensorUi();
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
       
         mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
          mlocListener = new LocationListener(){
        	  public void onLocationChanged(Location loc) {
        	        loc.getLatitude();
        	        loc.getLongitude();
        	        String Text = "Latitude = "+ loc.getLatitude() + "\nLongitude = " + loc.getLongitude();
        	        TextView txtgps = (TextView)findViewById(R.id.text_GPS);
        	        txtgps.setText(Text);
        	        //Toast.makeText(getApplicationContext(), Text, Toast.LENGTH_SHORT).show();
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
	private void initSensorUi() {
        mAccelXTextView = (TextView) findViewById(R.id.accelerometerX_text);
        mAccelYTextView = (TextView) findViewById(R.id.accelerometerY_text);
        mAccelZTextView = (TextView) findViewById(R.id.accelerometerZ_text);
        
        mCompDTextView = (TextView) findViewById(R.id.compass_text);
   
        mMagXTextView = (TextView) findViewById(R.id.magneticFieldX_text);
        mMagYTextView = (TextView) findViewById(R.id.magneticFieldY_text);
        mMagZTextView = (TextView) findViewById(R.id.magneticFieldZ_text);

    }
	
private void updateSensorUi(int sensorType, int accuracy, float[] values) {
        
        TextView xTextView;
        TextView yTextView;
        TextView zTextView;
        if (sensorType == Sensor.TYPE_ACCELEROMETER) {
            xTextView = mAccelXTextView;
            yTextView = mAccelYTextView;
            zTextView = mAccelZTextView;
        } 
         else if (sensorType == Sensor.TYPE_MAGNETIC_FIELD) {
            xTextView = mMagXTextView;
            yTextView = mMagYTextView;
            zTextView = mMagZTextView;
        } else {
            return;
        }

        int textColor = Color.BLACK;
        String prefix = "";
        switch (accuracy) {
            case SensorManager.SENSOR_STATUS_ACCURACY_HIGH:
                prefix = "  ";
                break;
            case SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM:
                prefix = "  *";
                break;
            case SensorManager.SENSOR_STATUS_ACCURACY_LOW:
                prefix = "  **";
                break;
            case SensorManager.SENSOR_STATUS_UNRELIABLE:
                prefix = "  ***";
                break;
        }

        xTextView.setTextColor(textColor);
        yTextView.setTextColor(textColor);
        zTextView.setTextColor(textColor);
        xTextView.setText(prefix + numberDisplayFormatter(values[0]));
        yTextView.setText(prefix + numberDisplayFormatter(values[1]));
        zTextView.setText(prefix + numberDisplayFormatter(values[2]));

    }
 private String numberDisplayFormatter(float value) {
        String displayedText = Float.toString(value);
        if (value >= 0) {
            displayedText = " " + displayedText;
        }
        if (displayedText.length() > 8) {
            displayedText = displayedText.substring(0, 8);
        }
        while (displayedText.length() < 8) {
            displayedText = displayedText + " ";
        }
        return displayedText;
    }
 protected void onResume() {
     super.onResume();
     setupSensors();
     mSensorManager.registerListener(mSensorEventListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
     mSensorManager.registerListener(mSensorEventListener, mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_GAME);
     mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,0, mlocListener);
     mSensorManager.registerListener(mSensorEventListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_NORMAL);
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

}
