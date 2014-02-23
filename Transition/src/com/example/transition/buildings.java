package com.example.transition;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;

public class buildings extends Activity implements OnTouchListener{
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.buildings);
		init();
    }
    private void init()
    {
    	Button btn_aca = (Button)findViewById(R.id.academic);
    	btn_aca.setOnTouchListener(this);
    }
	@Override
	public boolean onTouch(View v, MotionEvent event) {
    		Intent myintent = new Intent(this,screen2.class);
    		startActivity(myintent);
    		return false;
		// TODO Auto-generated method stub
	}
}