package com.example.acdat_introduccion_videojuegos_android.threads;

import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.acdat_introduccion_videojuegos_android.MoverFiguras;

public class ThreadDraw extends Thread {
    private SurfaceHolder sh;
    private SurfaceView view;
    private  boolean run;

    public ThreadDraw(SurfaceView view) {
        this.sh = view.getHolder();
        this.view = view;
        run = false;
    }

    public void setRunning(boolean run) {
        this.run = run;
    }

    @Override
    public void run() {
        Canvas canvas;

        while (run){
            canvas = null;
            try {
                canvas = sh.lockCanvas(null);
                if(canvas != null) {
                    synchronized (sh) {
                        view.postInvalidate();
                    }
                }
            } finally {
                if (canvas != null) sh.unlockCanvasAndPost(canvas);
            }
        }
    }
}
