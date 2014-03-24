package com.example.androidspinner;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;



import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {

  private Spinner spinner1;
  private Button button;

  @Override
  public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);

	
	
	addListenerOnSpinnerItemSelection();
  }

  // add items into spinner dynamically


  public void addListenerOnSpinnerItemSelection() {
	spinner1 = (Spinner) findViewById(R.id.spinner1);
	spinner1.setOnItemSelectedListener(new MyOnItemSelectedListener());
  }

 }