package com.alimuzaffar.turtledraw;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements View.OnClickListener {
	public static final String tag = "MainActivity";
	DrawView drawView;

	EditText cmds;

	float curX = 0f;
	float curY = 0f;
	float curTurn = 0f;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		drawView = (DrawView) findViewById(R.id.drawView1);
		// drawView.setBackgroundColor(Color.WHITE);
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);

		curX = metrics.widthPixels / 2f;
		curY = (metrics.heightPixels / 2f) - DpiUtils.getPxFromDpi(this, 50);

		if (savedInstanceState == null) {
			// as a test draw an X in the center of the screen.
			drawView.addLine(curX, curY, curX + 20, curY + 20);
			drawView.addLine(curX + 20, curY + 0, curX + 0, curY + 20);
		}

		cmds = (EditText) findViewById(R.id.cmds);
		Button go = (Button) findViewById(R.id.go);
		go.setOnClickListener(this);
		drawView.requestFocus();

	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

		int i = 0;
		while (savedInstanceState.getFloatArray(String.valueOf(String
				.valueOf(i))) != null) {
			float[] l = savedInstanceState.getFloatArray(String.valueOf(String
					.valueOf(i)));
			drawView.addLine(l);
			i++;
		}

		drawView.invalidate();
		drawView.requestFocus();

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		int i = 0;
		for (float[] l : drawView.getLines()) {
			outState.putFloatArray(String.valueOf(i), l);
			i++;
		}
	}

	@Override
	public void onClick(View v) {
		String text = cmds.getText().toString();
		String[] points = text.split(",");
		float[] line = new float[points.length];
//		for (int i = 0; i < points.length; i++) {
//			line[i] = Float.parseFloat(points[i]);
//		}
		Log.i(tag, "Forward 100");
		forward(0, 100);
		Log.i(tag, "Left 90 Forward 100");
		forward(90, 100);
		Log.i(tag, "Left 90 Forward 100");
		forward(90, 100);
		Log.i(tag, "Left 90 Forward 100");
		forward(90, 100);
		Log.i(tag, "Forward 100");
		forward(0, 100);

		Log.i(tag, "Forward 100");
		forward(0, 100);
		Log.i(tag, "Right 90 Forward 100");
		forward(360-90, 100);
		Log.i(tag, "Right 90 Forward 100");
		forward(360-90, 100);
		Log.i(tag, "Right 90 Forward 100");
		forward(360-90, 100);
		Log.i(tag, "Forward 100");
		forward(0, 100);
		
		drawView.addLine(line);
		drawView.invalidate();

	}

	public void forward(float turn, float distance) {
		curTurn = (curTurn + turn)%360;
		float nextX = getNextX((float)Math.toRadians(curTurn), distance);
		float nextY = getNextY((float)Math.toRadians(curTurn), distance);

		drawView.addLine(curX, curY, curX-nextX, curY-nextY);
		Log.i(tag, "curX = " + curX + ", curY = "+curY);
		Log.i(tag, "nextX = " + nextX + ", nextY = "+nextY);

		//subtract because in CS the point (0,0) is top left
		curX -= nextX;
		curY -= nextY;
		

		Log.i(tag, "NEW X = " + curX + ", NEW Y = "+curY);
	}

	public float getNextX(float turn, float distance) {
		return android.util.FloatMath.sin(turn) * distance;
			
	}

	public float getNextY(float turn, float distance) {
		return android.util.FloatMath.cos(turn) * distance;
	}

}
