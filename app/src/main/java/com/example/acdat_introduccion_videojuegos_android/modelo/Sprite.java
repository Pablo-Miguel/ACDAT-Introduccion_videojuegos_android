package com.example.acdat_introduccion_videojuegos_android.modelo;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.acdat_introduccion_videojuegos_android.GameView;
import com.example.acdat_introduccion_videojuegos_android.MoverFiguras;

public class Sprite {
    private int x = 0;
    private int xSpeed = 5;
    private GameView gameView;
    private Bitmap bmp;

    public Sprite(GameView gameView, Bitmap bmp) {
        this.gameView = gameView;
        this.bmp = bmp;
    }

    private void update(){
        x = x + xSpeed;
    }

    public void onDraw(Canvas canvas){
        update();
        canvas.drawBitmap(bmp, x, 10, null);
    }
}
