package com.example.acdat_introduccion_videojuegos_android;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.acdat_introduccion_videojuegos_android.modelo.Figura;
import com.example.acdat_introduccion_videojuegos_android.modelo.Sprite;
import com.example.acdat_introduccion_videojuegos_android.modelo.menu.TempSprite;
import com.example.acdat_introduccion_videojuegos_android.threads.GameLoopThread;

import java.util.ArrayList;
import java.util.List;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    //private Bitmap bmp;
    private GameLoopThread gameLoopThread;
    //private Sprite sprite;
    private List<Sprite> sprites;
    private long lastClick;
    private List<TempSprite> temps;
    private Bitmap bmpBlood;

    public GameView(Context context) {
        super(context);

        sprites = new ArrayList<Sprite>();
        lastClick = 0;
        temps = new ArrayList<TempSprite>();

        getHolder().addCallback(this);
        setBackgroundColor(Color.WHITE);
    }

    private void createSprites(){
        sprites.add(createSprite(R.drawable.bad1));
        sprites.add(createSprite(R.drawable.bad2));
        sprites.add(createSprite(R.drawable.bad3));
        sprites.add(createSprite(R.drawable.bad4));
        sprites.add(createSprite(R.drawable.bad5));
        sprites.add(createSprite(R.drawable.bad6));
        sprites.add(createSprite(R.drawable.good1));
        sprites.add(createSprite(R.drawable.good2));
        sprites.add(createSprite(R.drawable.good3));
        sprites.add(createSprite(R.drawable.good4));
        sprites.add(createSprite(R.drawable.good5));
        sprites.add(createSprite(R.drawable.good6));
    }

    private Sprite createSprite(int resource) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resource);
        bmp = bmp.createScaledBitmap(bmp, 400, 533, true);
        return new Sprite(this, bmp);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for(int i = temps.size() - 1; i >= 0; i--){
            temps.get(i).onDraw(canvas);
        }
        for(Sprite sprite: sprites){
            sprite.onDraw(canvas);
        }
        if(sprites.size() == 0){
            GameView gameView = this;
            gameView = new GameView(getContext());
            createSprites();
        }
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        //bmp = BitmapFactory.decodeResource(getResources(), R.drawable.sprite_angel);
        //bmp = bmp.createScaledBitmap(bmp, 500, 656, true);
        //sprite = new Sprite(this, this.bmp);

        createSprites();

        bmpBlood = BitmapFactory.decodeResource(getResources(), R.drawable.blood1);

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

    public boolean onTouchEvent(MotionEvent event) {

        if(System.currentTimeMillis() - lastClick > 500){
            lastClick = System.currentTimeMillis();

            synchronized (sprites){
                for(int i = sprites.size() - 1; i >= 0; i--){
                    Sprite sprite = sprites.get(i);
                    if(sprite.isTouched(event.getX(), event.getY())){
                        sprites.remove(sprite);
                        temps.add(new TempSprite(temps, this, event.getX(), event.getY(), bmpBlood));
                        return false;
                    }
                }
            }
        }

        return false;
    }

}
