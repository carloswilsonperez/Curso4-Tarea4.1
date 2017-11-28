package com.example.administrador.curso4_tarea4_1.vista_activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.administrador.curso4_tarea4_1.R;
import com.example.administrador.curso4_tarea4_1.adapter.MascotaAdapter;
import com.example.administrador.curso4_tarea4_1.pojo.Mascota;
import com.example.administrador.curso4_tarea4_1.presentador.MasVotadosActivityPresenter;
import com.example.administrador.curso4_tarea4_1.presentador.IMasVotadosActivityPresenter;

import java.util.ArrayList;

public class MasVotadosActivityView extends AppCompatActivity implements IMasVotadosActivityView {

    ArrayList<Mascota> mascotas;
    private RecyclerView rvMascotas;
    public MascotaAdapter adaptador;
    private IMasVotadosActivityPresenter presentador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mas_votados);

        Toolbar toolbar = (Toolbar) findViewById(R.id.miToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvMascotas = (RecyclerView) findViewById(R.id.rvMascotas);
        presentador = new MasVotadosActivityPresenter(this, getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item_menu) {
        int id = item_menu.getItemId(); // obtengo el item de cada item del menu
        //noinspection SimplifiableIfStatement
        if (id == R.id.accion_salir) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item_menu);
    }

    @Override
    public void generarLinearLayoutVertical() {
        // Instacia el linearLayoutManager que sirve para manejar la forma en que se ve la lista
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMascotas.setLayoutManager(llm);
    }

    @Override
    public MascotaAdapter crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdapter adaptador = new MascotaAdapter(mascotas, this);
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdapter adapter) {
        rvMascotas.setAdapter(adapter);
    }
}
