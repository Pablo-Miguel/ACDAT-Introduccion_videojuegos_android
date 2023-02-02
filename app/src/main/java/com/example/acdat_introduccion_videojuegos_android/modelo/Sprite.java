package com.example.acdat_introduccion_videojuegos_android.modelo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.acdat_introduccion_videojuegos_android.GameView;
import com.example.acdat_introduccion_videojuegos_android.MoverFiguras;

public class Sprite {
    private static final int BMP_ROWS = 4;
    private static final int BMP_COLUMNS = 4;
    private int x = 0;
    private int y = 0;
    private int xSpeed = 20;
    private GameView gameView;
    private Bitmap bmp;
    private int currentFrame = 0;
    private int width;
    private int height;
    private int dir = 2;

    public Sprite(GameView gameView, Bitmap bmp) {
        this.gameView = gameView;
        this.bmp = bmp;
        this.width = bmp.getWidth() / BMP_COLUMNS;
        this.height = bmp.getHeight() / BMP_ROWS;
    }

    private void update(){
        if(x > gameView.getWidth() - width - xSpeed){
            xSpeed = -xSpeed;
            dir = 1;
        } else if(x + xSpeed < 0){
            xSpeed = -xSpeed;
            dir = 2;
        }
        x = x + xSpeed;
        currentFrame = ++currentFrame % BMP_COLUMNS;
    }

    public void onDraw(Canvas canvas){
        update();
        int srcX = currentFrame * width;
        int srcY = dir * height;
        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        Rect dst = new Rect(x, y, x + width, y + height);
        canvas.drawBitmap(bmp, src, dst, null);
    }
}
