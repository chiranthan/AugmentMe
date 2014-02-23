package com.example.transition;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnTouchListener {
	Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    init();
    }
    private void init()
    {
    	btn = (Button)findViewById(R.id.next_btn);
    	btn.setOnTouchListener(this);
    }
    @Override
	public boolean onTouch(View v, MotionEvent event) {
    		Intent myintent = new Intent(this,screen2.class);
    		startActivity(myintent);
    		return false;
		// TODO Auto-generated method stub
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
