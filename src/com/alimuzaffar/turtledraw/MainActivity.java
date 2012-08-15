package com.alimuzaffar.turtledraw;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements View.OnClickListener{

	DrawView	drawView;
	
	EditText cmds;

	Handler		handler	= new Handler();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		drawView = (DrawView) findViewById(R.id.drawView1);
		// drawView.setBackgroundColor(Color.WHITE);
		drawView.addLine(0, 0, 20, 20);
		drawView.addLine(20, 0, 0, 20);

		cmds = (EditText) findViewById(R.id.cmds);
		Button go = (Button) findViewById(R.id.go);
		go.setOnClickListener(this);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

		int i=0;
		while(savedInstanceState.getFloatArray(String.valueOf(String.valueOf(i))) != null) {
			float [] l = savedInstanceState.getFloatArray(String.valueOf(String.valueOf(i)));
			drawView.addLine(l);
			i++;
		}
		
		drawView.invalidate();
		drawView.requestFocus();
		
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		
		int i=0;
		for(float[] l : drawView.getLines()) {
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
