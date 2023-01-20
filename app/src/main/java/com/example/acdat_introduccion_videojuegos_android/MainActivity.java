package com.example.acdat_introduccion_videojuegos_android;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setContentView(R.layout.activity_main);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Quitar titulo
        getSupportActionBar().hide();

        //Bloquear orientación: Vertical -> Portrait, Horizontal -> Landscape
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        super.onCreate(savedInstanceState);
        //Ejemplo 1:
        //setContentView(new Vista(MainActivity.this));

        //Ejemplo 2:
        //setContentView(new MoverFiguras(MainActivity.this));

        //Ejemplo:3
        setContentView(new RellenaFiguras(MainActivity.this));

    }
}