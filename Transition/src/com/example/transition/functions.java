package com.example.transition;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;


public class functions extends Activity implements OnTouchListener{
	Button btn_build;
	Button btn_frnd;
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.functions);
		init();
    }
    private void init()
    {
    	btn_build = (Button)findViewById(R.id.buildings);
    	btn_build.setOnTouchListener(this);
    	btn_frnd = (Button)findViewById(R.id.friends);
    	btn_frnd.setOnTouchListener(this);
    }
    @Override
	public boolean onTouch(View v, MotionEvent event) {
		Intent myintent;
        switch(v.getId()) {
           case R.id.buildings:
        	   myintent = new Intent(this,buildings.class);
        	   startActivity(myintent);
           break;
           case R.id.friends:
        	   myintent = new Intent(this,friends.class);
        	   startActivity(myintent);
           break;
        }
		return false;
	// TODO Auto-generated method stub
	}
}