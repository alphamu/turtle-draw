package com.alimuzaffar.turtledraw;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements View.OnClickListener {

	DrawView	drawView;

	EditText	cmds;

	float		curX	= 0f;
	float		curY	= 0f;
	float		curTurn	= 0f;

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
		while (savedInstanceState.getFloatArray(String.valueOf(String.valueOf(i))) != null) {
			float[] l = savedInstanceState.getFloatArray(String.valueOf(String.valueOf(i)));
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
		for (int i = 0; i < points.length; i++) {
			line[i] = Float.parseFloat(points[i]);
		}
		drawView.addLine(line);
		drawView.invalidate();

	}

}
