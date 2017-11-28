package com.example.administrador.curso4_tarea4_1.presentador;

import android.content.Context;
import com.example.administrador.curso4_tarea4_1.vista_activity.IMasVotadosActivityView;
import com.example.administrador.curso4_tarea4_1.bd.ConstructorMascotas;
import com.example.administrador.curso4_tarea4_1.pojo.Mascota;
import java.util.ArrayList;

/**
 * Created by administrador on 27/05/17.
 */

public class MasVotadosActivityPresenter implements IMasVotadosActivityPresenter {

    private IMasVotadosActivityView iMasVotadosActivityView;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

    public MasVotadosActivityPresenter(IMasVotadosActivityView iMasVotadosActivityView, Context context) {
        this.iMasVotadosActivityView = iMasVotadosActivityView;
        this.context = context;
        obtener5MascotasBaseDatos();
    }

    @Override
    public void obtener5MascotasBaseDatos() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtener5Datos(); //***** Aquí es donde se juntan los datos con la presentación ******
        mostrarMascotasRV();
    }

    @Override
    public void mostrarMascotasRV() {
        // Hay que inicializar el adaptador, para ello primero se debe crear el adaptador y pasarele el ArrayList contactos
        iMasVotadosActivityView.inicializarAdaptadorRV(iMasVotadosActivityView.crearAdaptador(mascotas));
        // Luego se debe indicar que genere el LinearLayoutVertical
        iMasVotadosActivityView.generarLinearLayoutVertical();
    }


}
