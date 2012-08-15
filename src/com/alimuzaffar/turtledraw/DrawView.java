package com.alimuzaffar.turtledraw;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class DrawView extends View {
    Paint paint = new Paint();
    
    ArrayList<float[]> lines = new ArrayList<float[]>();
    
    public DrawView(Context context, AttributeSet attrs) {
    	super(context,attrs);
    }

    public DrawView(Context context) {
        super(context);
        paint.setColor(Color.BLACK);
    }
    
    public void addLine(float... l) {
    	lines.add(l);
    }
    
    public List<float[]> getLines() {
    	return lines;
    }

    @Override
    public void onDraw(Canvas canvas) {
    	super.onDraw(canvas);
    	
    	for(float[] l : lines) {
    		paint.setStrokeWidth(5);
    		canvas.drawLines(l, paint);
    	}

    }

}