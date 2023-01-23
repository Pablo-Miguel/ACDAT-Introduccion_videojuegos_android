package com.example.acdat_introduccion_videojuegos_android.modelo;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.Log;

public class Circulo extends Figura {
    private int radio;

    public Circulo(int id, float pos_X, float pos_Y, int color, int radio) {
        super(id, pos_X, pos_Y, color);
        this.radio = radio;
    }

    public Circulo(int id, float pos_X, float pos_Y, int color, Paint.Style style, int radio, boolean mover) {
        super(id, pos_X, pos_Y, color, style, mover);
        this.radio = radio;
    }

    public int getRadio() {
        return radio;
    }

    public void setRadio(int radio) {
        this.radio = radio;
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawCircle(super.getPos_X(), super.getPos_Y(), this.getRadio(), super.getPaint());
    }

    @Override
    public boolean isTouched(int coord_X, int coord_y) {
        double dinstanciaX = coord_X - super.getPos_X();
        double dinstanciaY = coord_y - super.getPos_Y();
        if(Math.pow(this.getRadio(), 2) > (Math.pow(dinstanciaX, 2) + (Math.pow(dinstanciaY, 2)))){
            return true;
        }
        return false;
    }

    @Override
    public boolean isHover(Figura figura) {
        Circulo c = (Circulo) figura;

        double distanciaPuntos = Math.sqrt(Math.pow(c.getPos_X() - getPos_X(), 2) + Math.pow(c.getPos_Y() - getPos_Y(), 2));

        if(distanciaPuntos < (getRadio() / 2) && getRadio() == c.getRadio()){
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "Circulo{" +
                "radio=" + radio +
                '}';
    }
}
