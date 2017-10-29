package com.example.administrador.curso4_tarea4_1.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.administrador.curso4_tarea4_1.R;

public class AcercadeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acercade);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        
        if (toolbar!=null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false); // Oculta el titulo del ToolBar
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);   // boton para atras
        }
    }


}
