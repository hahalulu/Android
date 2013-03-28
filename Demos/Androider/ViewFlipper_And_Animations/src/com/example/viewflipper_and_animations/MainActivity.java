package com.example.viewflipper_and_animations;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ViewFlipper;

public class MainActivity extends Activity {
	ViewFlipper page;

	Animation animFlipInForeward;
	Animation animFlipOutForeward;
	Animation animFlipInBackward;
	Animation animFlipOutBackward;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button btnNext = (Button) findViewById(R.id.next);
		Button btnPrevious = (Button) findViewById(R.id.previous);

		btnNext.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				page.setInAnimation(animFlipInForeward);
				page.setOutAnimation(animFlipOutForeward);
				page.showNext();

			}
		});

		btnPrevious.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				page.setInAnimation(animFlipInBackward);
				page.setOutAnimation(animFlipOutBackward);
				page.showPrevious();

			}
		});

		page = (ViewFlipper) findViewById(R.id.flipper);

		animFlipInForeward = AnimationUtils.loadAnimation(this, R.anim.flipin);
		animFlipOutForeward = AnimationUtils
				.loadAnimation(this, R.anim.flipout);
		animFlipInBackward = AnimationUtils.loadAnimation(this,
				R.anim.flipin_reverse);
		animFlipOutBackward = AnimationUtils.loadAnimation(this,
				R.anim.flipout_reverse);

	}

	private void SwipeRight() {
		page.setInAnimation(animFlipInBackward);
		page.setOutAnimation(animFlipOutBackward);
		page.showPrevious();
	}

	private void SwipeLeft() {
		page.setInAnimation(animFlipInForeward);
		page.setOutAnimation(animFlipOutForeward);
		page.showNext();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return gestureDetector.onTouchEvent(event);
	}

	SimpleOnGestureListener simpleOnGestureListener = new SimpleOnGestureListener() {

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {

			float sensitvity = 50;
			if ((e1.getX() - e2.getX()) > sensitvity) {
				SwipeLeft();
			} else if ((e2.getX() - e1.getX()) > sensitvity) {
				SwipeRight();
			}

			return true;
		}

	};

	GestureDetector gestureDetector = new GestureDetector(
			simpleOnGestureListener);

}