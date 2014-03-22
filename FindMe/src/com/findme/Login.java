package com.findme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Intent intent = getIntent();
		
		TextView username = (TextView)findViewById(R.id.username);
		TextView password = (TextView)findViewById(R.id.password);
		Button newuser = (Button)findViewById(R.id.newuser);
}
	
	/*public void sendMessage(View view)
    {
  	  Intent intent = new Intent(this, FindActivity.class);
  	  startActivity(intent);
    }*/
	
	public void createuser(View view)
    {
	  	  Intent intent = new Intent(this, Create.class);
		  startActivity(intent);
	 }
	
}
