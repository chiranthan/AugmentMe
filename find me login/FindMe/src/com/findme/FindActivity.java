package com.findme;

import com.facebook.Session;
import com.facebook.SessionState;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class FindActivity extends Activity {

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find);
		Intent intent = getIntent();
		
		  // Make sure we're running on Honeycomb or higher to use ActionBar APIs
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.find, menu);
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
	
	//public void call(Session session, SessionState state, Exception exception) {

	//    if(session.isClosed())
	//     protected void onStop()
	//    {
	
			
	//	private void onSessionStateChange(Session session, SessionState state, Exception exception) 
//	    {    
	    //    if(state.isClosed())
	  // 	    {
	
	//		Session.StatusCallback callback = new Session.StatusCallback() {
	/*			 public void clear()
			        {
	   	        Button button1 = (Button)findViewById(R.id.button1);
	   	    	button1.setVisibility(View.INVISIBLE);
	   	    	Button button2 = (Button)findViewById(R.id.button2);
	   	    	button2.setVisibility(View.INVISIBLE);
	   	    		 }    */
			}
	


