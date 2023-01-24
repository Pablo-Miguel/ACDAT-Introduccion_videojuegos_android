package com.example.acdat_introduccion_videojuegos_android.modelo.menu;

import android.graphics.Canvas;

public class TextMenu extends Menu{
    private String text;

    public TextMenu(int id, float pos_X, float pos_Y, int color, String text) {
        super(id, pos_X, pos_Y, color);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawText(text, getPos_X(), getPos_Y(), getPaint());
    }

    @Override
    public boolean isTouched(int coord_X, int coord_y) {
        return false;
    }

    @Override
    public String toString() {
        return "TextMenu{" + text + "}";
    }
}
