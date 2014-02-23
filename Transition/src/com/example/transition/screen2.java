package com.example.transition;

//import android.annotation.SuppressLint;
import java.text.DecimalFormat;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

//@SuppressLint("NewApi")
public class screen2 extends Activity{
	private Camera mCamera;
    private CameraPreview mPreview;
	private float accx = 400, accy = 560 ;
	DrawacclView av;
	DrawcompView cv;
	FrameLayout alParent;
	DecimalFormat df = new DecimalFormat("###.#");
	
	//NH Coordinates:
	Double nhx = -97.11369355;
	Double nhy = 32.73196555;
	
	//ERB Coordinates:
	Double erbx = -97.11282096;
	Double erby = 32.73297688;
	
	//All Sensor code:
	private List<Sensor> sensors;
	private SensorManager mSensorManager;
    private LocationManager mlocManager;
    private LocationListener mlocListener;
    private TextView txtgps;
    private TextView txtcomp;
    private float angle = 0;
    float azimuth;
    
	private SensorEventListener mSensorEventListener = new SensorEventListener() {
        @Override
	    public void onSensorChanged(SensorEvent event) {
	        Sensor sensor = event.sensor;
	        int tx = 0;// treshold
	        int ty = 0;
	        int snx = 35;//senitivity
	        int sny = 55;
	        if(sensor.getType() == Sensor.TYPE_ACCELEROMETER){
		        if(event.values[0] > tx || event.values[0] < -tx)
		        	 accx = -(event.values[0])*snx;
		        if(event.values[1] > ty || event.values[1] < -ty)
		        	 accy = (event.values[1])*sny;
		    	 av.setX(accx);
		    	 av.setY(accy);
	        }
	    	 if (sensor.getType() == Sensor.TYPE_ORIENTATION) {
	         	azimuth = Math.round(event.values[0]);
	         	//cv.setRotation(-azimuth);
	             // The other values provided are: 
	             //  float pitch = event.values[1];
	             //  float roll = event.values[2];
	         	txtcomp.setText("Direction: " + Float.toString(azimuth));
	         	
        	}
	    }
	
	        @Override
	    public void onAccuracyChanged(Sensor sensor, int accuracy) {
	    }

	};

	private void setupSensors() {
        // initSensorUi();
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
	 mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
	 mlocListener = new LocationListener(){
   	  public void onLocationChanged(Location loc) {
   		  	angle = (float)(Math.atan((loc.getLongitude() - erby)/(loc.getLatitude() - erbx)));
   	        String Text = "Latitude = "+ loc.getLatitude() + "\nLongitude = " + loc.getLongitude();
   	        txtgps.setText((int)angle);
   	        //cv.setRotation(angle);
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
		setContentView(R.layout.screen2);
		mCamera = getCameraInstance();

        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout) this.findViewById(R.id.camera_preview);
        preview.addView(mPreview);
        av = new DrawacclView(this,accx,accy);
        cv = new DrawcompView(this);
        txtgps = new TextView(getBaseContext());
        txtcomp = new TextView(getBaseContext());
        preview.addView(av);
        preview.addView(cv);
        preview.addView(txtgps);
        preview.addView(txtcomp);
        txtgps.setText("Waiting for information from GPS...");
        txtcomp.setX(300);
        txtcomp.setText("compass view...");
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
               //WindowManager.LayoutParams.FLAG_FULLSCREEN);
        	//Toast toast = Toast.makeText(getBaseContext(), "couldnt load acc", Toast.LENGTH_SHORT);
			//toast.show();
		}
		//mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME);
}