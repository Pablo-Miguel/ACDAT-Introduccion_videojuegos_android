package com.example.acdat_introduccion_videojuegos_android;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class Vista extends View implements View.OnTouchListener {

    Random rand = new Random();

    private float x = 116, y = 116;
    private Boolean flag = false;

    public Vista(Context context) {
        super(context);

        this.setOnTouchListener(Vista.this);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRGB(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));

        Paint paint = new Paint();
        /*
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        canvas.drawPaint(paint);
        */
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        canvas.drawRect(16, 16, getWidth() - 16, getHeight() - 16, paint);

        paint.setColor(Color.rgb(255, 104, 230));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(x, y, 100, paint);

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        //motionEvent.getX();
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i("Down", "Down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("Move", "Move");
                x = motionEvent.getX();
                y = motionEvent.getY();
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.i("Cancel", "Cancel");
                break;
            case MotionEvent.ACTION_UP:
                Log.i("Up","Up");
                break;

        }

        this.invalidate(); //Para que refresque la pantalla
        return true;
    }
}
