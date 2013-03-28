package com.example.fragment_lifecycle;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView status;
	String myStatus = "";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		status = (TextView) findViewById(R.id.status);
		updateMyStatus("onCreate()");
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		updateMyStatus("onDestroy()");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		updateMyStatus("onPause()");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		updateMyStatus("onResume()");
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		updateMyStatus("onStart()");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		updateMyStatus("onStop()");
	}

	private void updateMyStatus(String myst) {
		updateStatus("Activity: " + myst);
	}

	public void updateStatus(String st) {

		if (status == null) {
			myStatus += "delay - " + st + "\n";
		} else {
			myStatus += st + "\n";
			status.setText(myStatus);
		}
	}

}