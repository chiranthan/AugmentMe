package com.example.geocoder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	public void sendMessage(View view) {
	    // Do something in response to button
		EditText editText = (EditText) findViewById(R.id.editText1);
		String address = editText.getText().toString();
		getLatLongFromAddress( address);
		
	}
	
	/*public  void getLatLongFromAddress(String youraddress) {
		double lat= 0.0, lng= 0.0;
	    String uri = "http://maps.google.com/maps/api/geocode/json?address=" +
	                  youraddress + "&sensor=false";
	    HttpGet httpGet = new HttpGet(uri);
	    HttpClient client = new DefaultHttpClient();
	    HttpResponse response;
	    StringBuilder stringBuilder = new StringBuilder();

	    try {
	        response = client.execute(httpGet);
	        HttpEntity entity = response.getEntity();
	        InputStream stream = entity.getContent();
	        int b;
	        while ((b = stream.read()) != -1) {
	            stringBuilder.append((char) b);
	        }
	    } catch (ClientProtocolException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    JSONObject jsonObject = new JSONObject();
	    try {
	        jsonObject = new JSONObject(stringBuilder.toString());

	        lng = ((JSONArray)jsonObject.get("results")).getJSONObject(0)
	            .getJSONObject("geometry").getJSONObject("location")
	            .getDouble("lng");

	        lat = ((JSONArray)jsonObject.get("results")).getJSONObject(0)
	            .getJSONObject("geometry").getJSONObject("location")
	            .getDouble("lat");
	        
	        String Text = "Latitude = "+ lat + "\nLongitude = " + lng;
	        TextView txtgps = (TextView)findViewById(R.id.textView1);
	        txtgps.setText(Text);

	        //Log.d("latitude", lat);
	       // Log.d("longitude", lng);
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }

	}*/
	
	 private void getLatLongFromAddress(String address)
     {
           double lat= 0.0, lng= 0.0;

         Geocoder geoCoder = new Geocoder(this, Locale.getDefault());    
         try 
         {
             List<Address> addresses = geoCoder.getFromLocationName(address , 1);
             if (addresses.size() > 0) 
             {            
                
            	 

                 lat=addresses.get(0).getLatitude();
                 lng=addresses.get(0).getLongitude();
                 
                 String Text = "Latitude = "+ lat + "\nLongitude = " + lng;
     	        TextView txtgps = (TextView)findViewById(R.id.textView1);
     	        txtgps.setText(Text);
                 
                 /*GeoPoint p = new GeoPoint(
                         (int) (addresses.get(0).getLatitude() * 1E6), 
                         (int) (addresses.get(0).getLongitude() * 1E6));

                 lat=p.getLatitudeE6()/1E6;
                 lng=p.getLongitudeE6()/1E6;*/

                 Log.d("Latitude", ""+lat);
             Log.d("Longitude", ""+lng);
             }
         }
         catch(Exception e)
         {
           e.printStackTrace();
         }
     }
 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
