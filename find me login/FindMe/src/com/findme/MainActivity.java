package com.findme;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.facebook.*;
import com.facebook.model.*;
import com.facebook.widget.LoginButton;
import com.findme.R;


public class MainActivity extends Activity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
	  
    super.onCreate(savedInstanceState);
   
    setContentView(R.layout.activity_main);
    LoginButton authButton = (LoginButton) findViewById(R.id.authButton);
    
 //     if(authButton.isSelected()==true)
      
   //   {

 // start Facebook Login
   Session.openActiveSession(this, true, new Session.StatusCallback() {
    
   // Session.StatusCallback callback = new Session.StatusCallback() {

      // callback when session changes state
      
      public void call(Session session, SessionState state, Exception exception) {

        if (session.isOpened()) {
        	
        	Button next = (Button)findViewById(R.id.button1);
        	next.setVisibility(View.VISIBLE);
        	next.setText("Continue");
        	

          // make request to the /me API
  //        Request.executeMeRequestAsync(session, new Request.GraphUserCallback() {
        	Request.newMeRequest(session, new Request.GraphUserCallback() {

            // callback after Graph API response with user object
            @Override
            public void onCompleted(GraphUser user, Response response) {
              if (user != null) {
                TextView welcome = (TextView) findViewById(R.id.welcome);
                welcome.setText("Hello " + user.getName() + "!");
              }
            }
          }).executeAsync();
        }
        
       else if(session.isClosed()){
       	TextView welcome = (TextView) findViewById(R.id.welcome);
            welcome.setText("Thank you");   
           
            FindActivity fa = new FindActivity();
     //       fa.clear();
        /*    Button button1 = (Button)fa.findViewById(R.id.button1);
            button1.setVisibility(View.GONE);
            Button button2 = (Button)fa.findViewById(R.id.button2);
            button2.setVisibility(View.GONE);  */
            
     /*       Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);    */
             
        }  
      }
    });

	
    };
  
  
      public void sendMessage(View view)
      {
    	  Intent intent = new Intent(this, FindActivity.class);
    	  startActivity(intent);
      }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
      super.onActivityResult(requestCode, resultCode, data);
      Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
  }

}