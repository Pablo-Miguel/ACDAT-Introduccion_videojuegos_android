package com.example.acdat_introduccion_videojuegos_android;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.acdat_introduccion_videojuegos_android.modelo.Sprite;
import com.example.acdat_introduccion_videojuegos_android.threads.GameLoopThread;

import java.util.ArrayList;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private Bitmap bmp;
    private GameLoopThread gameLoopThread;
    private Sprite sprite;

    public GameView(Context context) {
        super(context);

        getHolder().addCallback(this);
        setBackgroundColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        sprite.onDraw(canvas);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.fnaf_v2);
        bmp = bmp.createScaledBitmap(bmp, 395, 600, true);
        sprite = new Sprite(this, this.bmp);

        gameLoopThread = new GameLoopThread(this);
        gameLoopThread.setRunning(true);
        gameLoopThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

}
