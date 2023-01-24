package com.example.acdat_introduccion_videojuegos_android.modelo.menu;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.acdat_introduccion_videojuegos_android.modelo.Figura;

public class ButtonMenu extends Menu{
    private Bitmap img;
    private int base, altura;

    public ButtonMenu(int id, float pos_X, float pos_Y, int color, Resources resources, int img_ref, int base, int altura) {
        super(id, pos_X, pos_Y, color);
        this.base = base;
        this.altura = altura;
        Bitmap bmp = BitmapFactory.decodeResource(resources, img_ref);
        img = bmp.createScaledBitmap(bmp, base, altura, true);
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
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
        canvas.drawBitmap(img, getPos_X(), getPos_Y(), null);
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
        return "Rectangulo{" +
                "base=" + base +
                ", altura=" + altura +
                '}';
    }
}
