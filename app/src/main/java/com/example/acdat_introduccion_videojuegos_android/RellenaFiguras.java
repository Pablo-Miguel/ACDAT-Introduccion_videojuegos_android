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
import com.example.acdat_introduccion_videojuegos_android.modelo.menu.ButtonMenu;
import com.example.acdat_introduccion_videojuegos_android.modelo.menu.Menu;
import com.example.acdat_introduccion_videojuegos_android.modelo.menu.MenuBackground;
import com.example.acdat_introduccion_videojuegos_android.modelo.menu.TextMenu;
import com.example.acdat_introduccion_videojuegos_android.threads.ThreadDraw;

import java.util.ArrayList;
import java.util.Random;

public class RellenaFiguras extends SurfaceView implements SurfaceHolder.Callback {
    Random rnd = new Random();
    private ThreadDraw threadDraw;
    private ArrayList<Figura> figuras;
    private ArrayList<Menu> figuras_menu;
    private int figuraActiva;
    private int iniX, iniY;
    private int id;

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
        id = 0;

        figuras = new ArrayList<Figura>();
        figuras.add(new Circulo(id++, getWidth() - 500, 300,
                Color.MAGENTA, Paint.Style.STROKE,100, false));
        figuras.add(new Rectangulo(id++, getWidth() - 600, 500,
                Color.RED, Paint.Style.STROKE,200, 200, false));
        figuras.add(new Circulo(id++, 250, 300, Color.MAGENTA, Paint.Style.FILL,100, true));
        figuras.add(new Rectangulo(id++, 200, 500, Color.RED, Paint.Style.FILL,200, 200, true));

        figuras_menu = new ArrayList<Menu>();
        figuras_menu.add(new MenuBackground(0, 0, getHeight() - 300, Color.GREEN, getWidth(), 300));
        figuras_menu.add(new ButtonMenu(0, 100, getHeight() - 250, Color.TRANSPARENT, getResources(),
                R.drawable.add_btn, 200, 200));
        figuras_menu.add(new TextMenu(0, getWidth() - 500, getHeight() - 250, Color.BLACK, "Puntuación: 0"));

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

        for (Menu m: figuras_menu) {
            m.onDraw(canvas);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (figuras_menu.get(1).isTouched(x, y)) {
                    MenuBackground menuBackground = (MenuBackground) figuras_menu.get(0);
                    if(rnd.nextBoolean()) figuras.add(
                            new Circulo(
                                    id++,
                                    rnd.nextInt(getWidth() - 200),
                                    rnd.nextInt(getHeight() - menuBackground.getAltura() - 200),
                                    Color.MAGENTA,
                                    Paint.Style.FILL,
                                    100,
                                    true));
                    else figuras.add(
                            new Rectangulo(
                                    id++,
                                    rnd.nextInt(getWidth() - 200),
                                    rnd.nextInt(getHeight() - menuBackground.getAltura() - 200),
                                    Color.RED,
                                    Paint.Style.FILL,
                                    200,
                                    200,
                                    true));
                }

                for (Figura f : figuras) {
                    if (f.isTouched(x, y) && f.isMover()) {
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
                                    figuras.get(figuraActiva).setPos_X(-100000);
                                    figuras.get(figuraActiva).setPos_Y(-100000);
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