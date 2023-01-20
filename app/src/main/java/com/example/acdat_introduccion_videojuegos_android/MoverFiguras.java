package com.example.acdat_introduccion_videojuegos_android;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.example.acdat_introduccion_videojuegos_android.modelo.Circulo;
import com.example.acdat_introduccion_videojuegos_android.modelo.Figura;
import com.example.acdat_introduccion_videojuegos_android.modelo.Imagen;
import com.example.acdat_introduccion_videojuegos_android.modelo.Rectangulo;
import com.example.acdat_introduccion_videojuegos_android.threads.ThreadDraw;

import java.util.ArrayList;

public class MoverFiguras extends SurfaceView implements SurfaceHolder.Callback {

    private ThreadDraw threadDraw;
    private ArrayList<Figura> figuras;
    private int figuraActiva;
    private int iniX, iniY;
    //private Rectangulo menu;

    public MoverFiguras(Context context) {
        super(context);

        figuraActiva = -1;
        iniX = 0;
        iniY = 0;

        getHolder().addCallback(this);
        setBackgroundColor(Color.WHITE);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        int id = 0;

        //menu = new Rectangulo(-1, 0, getHeight() - 300, Color.GREEN,getWidth(), 300);
        figuras = new ArrayList<Figura>();
        figuras.add(new Circulo(id++, 200, 200, Color.BLACK,100));
        figuras.add(new Rectangulo(id++, 200, 500, Color.RED,200, 200));
        figuras.add(new Imagen(id++, 500, 500, getResources(), R.drawable.hollow_knight, 200, 200));

        threadDraw = new ThreadDraw(this);
        threadDraw.setRunning(true);
        threadDraw.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        boolean retry = true;
        threadDraw.setRunning(false);

        while (retry){
            try {
                threadDraw.join();
                retry = false;
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.WHITE);

        Circulo c = (Circulo) figuras.get(0);
        c.onDraw(canvas);
        Rectangulo r = (Rectangulo) figuras.get(1);
        r.onDraw(canvas);
        Imagen i = (Imagen) figuras.get(2);
        i.onDraw(canvas);

        //menu.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                for (Figura f : figuras) {
                    if (f.isTouched(x, y)) {
                        figuraActiva = f.getId();
                        iniX = (int) event.getX();
                        iniY = (int) event.getY();
                        Log.i("FiguraActiva", "ID: " + figuraActiva);
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(figuraActiva != -1){
                    figuras.get(figuraActiva).setPositionUpdated(x - iniX, y - iniY);
                    iniX = (int) event.getX();
                    iniY = (int) event.getY();
                }
                break;
            case MotionEvent.ACTION_UP:
                figuraActiva = -1;
        }

        return true;
    }

}
