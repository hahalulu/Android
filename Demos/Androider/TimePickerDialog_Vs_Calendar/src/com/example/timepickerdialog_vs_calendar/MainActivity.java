package com.example.timepickerdialog_vs_calendar;

import java.util.Calendar;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends Activity {

	TimePicker myTimePicker;
	Button buttonstartSetDialog;
	TextView textPrompt;

	TimePickerDialog timePickerDialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textPrompt = (TextView) findViewById(R.id.prompt);

		buttonstartSetDialog = (Button) findViewById(R.id.startSetDialog);
		buttonstartSetDialog.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				openTimePickerDialog(false);

			}
		});

	}

	private void openTimePickerDialog(boolean is24r) {
		Calendar calendar = Calendar.getInstance();

		timePickerDialog = new TimePickerDialog(MainActivity.this,
				onTimeSetListener, calendar.get(Calendar.HOUR_OF_DAY),
				calendar.get(Calendar.MINUTE), is24r);
		timePickerDialog.setTitle("TimePickerDialog Title");
		timePickerDialog.setMessage("TimePickerDialog Message");

		timePickerDialog.show();

	}

	OnTimeSetListener onTimeSetListener = new OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

			Calendar calNow = Calendar.getInstance();
			Calendar calSet = (Calendar) calNow.clone();

			calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
			calSet.set(Calendar.MINUTE, minute);

			String stringPrompt = "Current Time is: " + calNow.getTime()
					+ " / " + calNow.getTimeInMillis() + "\n" + "Set Time is: "
					+ calSet.getTime() + " / " + calSet.getTimeInMillis()
					+ "\n" + "calSet.compareTo(calNow) = "
					+ calSet.compareTo(calNow) + "\n\n";

			if (calSet.compareTo(calNow) > 0) {
				// Today Set time not yet passed
				long diff = calSet.getTimeInMillis() - calNow.getTimeInMillis();
				stringPrompt += "Millis to set time = " + diff;
			} else {
				// Today Set time passed, count to tomorrow
				calSet.add(Calendar.DATE, 1);
				long diff = calSet.getTimeInMillis() - calNow.getTimeInMillis();
				stringPrompt += "Millis to tomorrow set time = " + diff;
			}

			textPrompt.setText(stringPrompt);
		}
	};

}