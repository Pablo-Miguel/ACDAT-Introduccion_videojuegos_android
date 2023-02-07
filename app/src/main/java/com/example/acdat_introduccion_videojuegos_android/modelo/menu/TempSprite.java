package com.example.acdat_introduccion_videojuegos_android.modelo.menu;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.acdat_introduccion_videojuegos_android.GameView;

import java.util.List;

public class TempSprite {
    private List<TempSprite> temps;
    private GameView gameView;
    private float x;
    private float y;
    private Bitmap bmp;
    private int life = 15;

    public TempSprite(List<TempSprite> temps, GameView gameView, float x, float y, Bitmap bmp) {
        this.temps = temps;
        this.gameView = gameView;
        this.x = Math.min(Math.max(x - bmp.getWidth() / 2, 0), gameView.getWidth() - bmp.getWidth());
        this.y = Math.min(Math.max(y - bmp.getHeight() / 2, 0), gameView.getHeight() - bmp.getHeight());
        this.bmp = bmp;
    }

    private void update(){
        if(--life < 1){
            temps.remove(this);
        }
    }

    public void onDraw(Canvas canvas){
        update();
        canvas.drawBitmap(bmp, x, y, null);
    }
}
