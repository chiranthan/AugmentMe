package com.findme;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Create extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.createuser);
		Intent intent = getIntent();
		
		TextView firstname = (TextView)findViewById(R.id.firstname);
		TextView lastname = (TextView)findViewById(R.id.lastname);
		TextView emailid = (TextView)findViewById(R.id.emailid);
		TextView password = (TextView)findViewById(R.id.pwd);
		TextView repassword = (TextView)findViewById(R.id.reenteredpwd);
		TextView phone = (TextView)findViewById(R.id.phone);
		
}
	
	public void validate_email()
	{
		Random random = new Random();
		int num = random.nextInt();
		
		Intent i = new Intent(Intent.ACTION_SEND);
		i.setType("text/plain");
		i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"bhavana547@gmail.com"});
		i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
		i.putExtra(Intent.EXTRA_TEXT   , "body of email");
		try {
		    startActivity(Intent.createChooser(i, "Send mail..."));
		} catch (android.content.ActivityNotFoundException ex) {
		    Toast.makeText(getApplicationContext(), "Exception", 10).show();
		}
		
	}

}
