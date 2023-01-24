package com.example.acdat_introduccion_videojuegos_android.modelo.menu;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.acdat_introduccion_videojuegos_android.modelo.Figura;

import java.util.Objects;

public abstract class Menu {
    private int id;
    private float pos_X, pos_Y;
    private Paint paint;

    public Menu(int id, float pos_X, float pos_Y, int color) {
        this.id = id;
        this.pos_X = pos_X;
        this.pos_Y = pos_Y;
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.paint.setColor(color);
    }

    public abstract void onDraw(Canvas canvas);

    public abstract boolean isTouched(int coord_X, int coord_y);

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPos_X() {
        return pos_X;
    }

    public void setPos_X(float pos_X) {
        this.pos_X = pos_X;
    }

    public float getPos_Y() {
        return pos_Y;
    }

    public void setPos_Y(float pos_Y) {
        this.pos_Y = pos_Y;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu)) return false;
        Menu menu = (Menu) o;
        return getId() == menu.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", pos_X=" + pos_X +
                ", pos_Y=" + pos_Y +
                '}';
    }
}
