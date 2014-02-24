package com.findme;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceView;

public class DrawcompView extends SurfaceView{
private Paint stickPaint = new Paint();
private Paint headPaint = new Paint();
float x, y;
 
 public DrawcompView(Context context) {
 super(context);
 // Create out paint to use for drawing
 stickPaint.setARGB(150, 0, 0, 255);
 headPaint.setARGB(255, 100, 0, 255);
 stickPaint.setStrokeWidth(30);
 headPaint.setStrokeWidth(20);
 // This call is necessary, or else the 
 // draw method will not be called. 
 setWillNotDraw(false);
 }
 
 @Override
 protected void onDraw(Canvas canvas){
 // A Simple Text Render to test the display
 canvas.drawLine(400, 300, 400, 130, stickPaint);
 canvas.drawLine(350, 170, 404, 130, headPaint);
 canvas.drawLine(450, 170, 396, 130, headPaint);
 }
}