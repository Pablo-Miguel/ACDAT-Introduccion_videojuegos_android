package com.example.acdat_introduccion_videojuegos_android.modelo;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class Rectangulo extends Figura{
    private int base, altura;

    public Rectangulo(int id, float pos_X, float pos_Y, int color, int base, int altura) {
        super(id, pos_X, pos_Y, color);
        this.base = base;
        this.altura = altura;
    }

    public Rectangulo(int id, float pos_X, float pos_Y, int color, Paint.Style style, int base, int altura, boolean mover) {
        super(id, pos_X, pos_Y, color, style, mover);
        this.base = base;
        this.altura = altura;
    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawRect(super.getPos_X(), super.getPos_Y(),
                super.getPos_X() + this.getBase(),
                super.getPos_Y() + this.getAltura(),
                super.getPaint());
    }

    @Override
    public boolean isTouched(int coord_X, int coord_y) {
        if(coord_X > super.getPos_X() && coord_X < super.getPos_X() + this.getBase() && coord_y > super.getPos_Y() && coord_y < super.getPos_Y() + this.getAltura()){
            return true;
        }
        return false;
    }

    @Override
    public boolean isHover(Figura figura) {
        Rectangulo r = (Rectangulo) figura;

        double centroX = (getBase() / 2) + getPos_X();
        double centroY = (getAltura() / 2) + getPos_Y();

        double centroXR = (r.getBase() / 2) + r.getPos_X();
        double centroYR = (r.getAltura() / 2) + r.getPos_Y();

        double distanciaPuntos = Math.sqrt(Math.pow(centroXR - centroX, 2) + Math.pow(centroYR - centroY, 2));

        if(distanciaPuntos < (getBase() / 2) && getBase() == r.getBase() && getAltura() == r.getAltura()){
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "Rectangulo{" +
                "base=" + base +
                ", altura=" + altura +
                '}';
    }
}
