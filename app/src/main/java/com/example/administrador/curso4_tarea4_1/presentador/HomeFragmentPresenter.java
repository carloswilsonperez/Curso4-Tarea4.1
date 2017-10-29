package com.example.administrador.curso4_tarea4_1.presentador;

import android.content.Context;

import com.example.administrador.curso4_tarea4_1.bd.ConstructorMascotas;
import com.example.administrador.curso4_tarea4_1.pojo.Mascota;
import com.example.administrador.curso4_tarea4_1.vista_fragment.IHomeFragmentView;

import java.util.ArrayList;

/**
 * Created by administrador on 27/05/17.
 */

public class HomeFragmentPresenter implements IHomeFragmentPresenter {

    //Delcaro los objeto globales
    private IHomeFragmentView iHomeFragmentView;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

    public HomeFragmentPresenter(IHomeFragmentView iHomeFragmentView, Context context) {
        this.iHomeFragmentView = iHomeFragmentView;
        this.context = context;
        obtenerMascotasBaseDatos();
    }

    @Override
    public void obtenerMascotasBaseDatos() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerDatos(); //***** Aquí es donde se juntan los datos con la presentación ******
        mostrarMascotasRV();
    }

    @Override
    public void mostrarMascotasRV() {
        // Hay que inicializar el adaptador, para ello primero se debe crear el adaptador y pasarele el ArrayList contactos
        iHomeFragmentView.inicializarAdaptadorRV(iHomeFragmentView.crearAdaptador(mascotas));
        // Luego se debe indicar que genere el LinearLayoutVertical
        iHomeFragmentView.generarLinearLayoutVertical();
    }
}
