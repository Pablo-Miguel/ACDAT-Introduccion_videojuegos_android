package com.example.acdat_introduccion_videojuegos_android;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.acdat_introduccion_videojuegos_android.modelo.Circulo;
import com.example.acdat_introduccion_videojuegos_android.modelo.Figura;
import com.example.acdat_introduccion_videojuegos_android.modelo.Imagen;
import com.example.acdat_introduccion_videojuegos_android.modelo.Rectangulo;
import com.example.acdat_introduccion_videojuegos_android.threads.ThreadDraw;

import java.util.ArrayList;

public class RellenaFiguras extends SurfaceView implements SurfaceHolder.Callback {

    private ThreadDraw threadDraw;
    private ArrayList<Figura> figuras;
    private int figuraActiva;
    private int iniX, iniY;

    public RellenaFiguras(Context context) {
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

        figuras = new ArrayList<Figura>();
        figuras.add(new Circulo(id++, 500, 300, Color.MAGENTA, Paint.Style.STROKE,100));
        figuras.add(new Rectangulo(id++, 500, 500, Color.RED, Paint.Style.STROKE,200, 200));
        figuras.add(new Circulo(id++, 250, 300, Color.MAGENTA, Paint.Style.FILL,100));
        figuras.add(new Rectangulo(id++, 200, 500, Color.RED, Paint.Style.FILL,200, 200));

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

        for (Figura figura: figuras) {
            figura.onDraw(canvas);
        }

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

                    for (Figura figura: figuras) {
                        if(figuras.get(figuraActiva).getClass().equals(figura.getClass())
                                && figuras.get(figuraActiva).getPaint().getColor() == figura.getPaint().getColor()
                                && !figuras.get(figuraActiva).equals(figura)){
                            boolean estado = figuras.get(figuraActiva).isHover(figura);
                            if(estado){
                                if(figura.getPaint().getStyle() == Paint.Style.STROKE){
                                    figura.setPos_X(100000);
                                    figura.setPos_Y(100000);
                                } else {
                                    figuras.get(figuraActiva).setPos_X(100000);
                                    figuras.get(figuraActiva).setPos_Y(100000);
                                    figuraActiva = figura.getId();
                                }
                            }
                        }
                    }

                }
                break;
            case MotionEvent.ACTION_UP:
                figuraActiva = -1;
        }

        return true;
    }

}