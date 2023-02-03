package com.example.acdat_introduccion_videojuegos_android.modelo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import com.example.acdat_introduccion_videojuegos_android.GameView;
import com.example.acdat_introduccion_videojuegos_android.MoverFiguras;

import java.util.Random;

public class Sprite {
    private static final int BMP_ROWS = 4;
    private static final int BMP_COLUMNS = 4;
    private int x = 0;
    private int y = 0;
    private int xSpeed, ySpeed;
    private GameView gameView;
    private Bitmap bmp;
    private int currentFrame = 0;
    private int width;
    private int height;
    private int[] DIRECTION_TO_ANIMATION_MAP = {3, 1, 0, 2};

    public Sprite(GameView gameView, Bitmap bmp) {
        this.gameView = gameView;
        this.bmp = bmp;
        this.width = bmp.getWidth() / BMP_COLUMNS;
        this.height = bmp.getHeight() / BMP_ROWS;
        xSpeed = 20;
        ySpeed = 20;
    }

    private int getAnimationRow() {
        double dirDouble = (Math.atan2(xSpeed, ySpeed) / (Math.PI / 2) + 2);
        int direction = (int) Math.round(dirDouble) % BMP_ROWS;
        return DIRECTION_TO_ANIMATION_MAP[direction];
    }

    private void update(){
        if(x > gameView.getWidth() - width - xSpeed || x + xSpeed < 0){
            xSpeed = -xSpeed;
        }

        if(y > gameView.getHeight() - height - ySpeed || y + ySpeed < 0){
            ySpeed = -ySpeed;
        }

        x = x + xSpeed;
        y = y + ySpeed;

        currentFrame = ++currentFrame % BMP_COLUMNS;
    }

    public void onDraw(Canvas canvas){
        update();
        int srcX = currentFrame * width;
        int srcY = getAnimationRow() * height;
        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        Rect dst = new Rect(x, y, x + width, y + height);
        canvas.drawBitmap(bmp, src, dst, null);
    }


}
