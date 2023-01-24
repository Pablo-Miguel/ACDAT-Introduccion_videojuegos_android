package com.example.acdat_introduccion_videojuegos_android.modelo.menu;

import android.graphics.Canvas;

public class MenuBackground extends Menu{
    private int base, altura;

    public MenuBackground(int id, float pos_X, float pos_Y, int color, int base, int altura) {
        super(id, pos_X, pos_Y, color);
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
    public String toString() {
        return "MenuBackground{" +
                "base=" + base +
                ", altura=" + altura +
                '}';
    }
}
